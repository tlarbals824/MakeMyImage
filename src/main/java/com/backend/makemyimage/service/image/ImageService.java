package com.backend.makemyimage.service.image;

import com.backend.makemyimage.domain.image.Image;
import com.backend.makemyimage.dto.request.image.ImageRequest;
import com.backend.makemyimage.dto.response.image.ImageListResponse;
import com.backend.makemyimage.dto.response.image.ImageResponse;
import com.backend.makemyimage.repository.image.ImageRepository;
import com.backend.makemyimage.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;

    @Value("${rest-api-key}")
    private String restApiKey;

    public ImageResponse create(ImageRequest imageRequest) throws Exception {
        //kakao api 사용해서 이미지 생성
        URL url = new URL("https://api.kakaobrain.com/v2/inference/karlo/t2i");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("Authorization", "KakaoAK " + restApiKey);
        httpConn.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("{\n" +
                "        \"prompt\": \"" + imageRequest.getKeyword() +"\",\n" +
                "        \"negative_prompt\": \"" + "low quality, low contrast, draft, amateur, cut off, cropped, frame" + "\"\n" +
                "    }");
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();

        Scanner scanner = new Scanner(responseStream).useDelimiter("\\A");
        String response = scanner.hasNext() ? scanner.next() : "";

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(response);
        JSONArray jsonArray = (JSONArray) jsonObject.get("images");
        JSONObject imageObject = (JSONObject) jsonArray.get(0);

        String imageUrl = (String) imageObject.get("image");

        Image newImage = Image.builder()
                .url(imageUrl)
                .keyword(imageRequest.getKeyword())
                .loginId(imageRequest.getLoginId())
                .build();

        if(memberRepository.findByLoginId(imageRequest.getLoginId()).isEmpty())
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        imageRepository.save(newImage);

        return ImageResponse.builder()
                .image(newImage)
                .build();
    }

    public ImageListResponse getAllByLoginId(String loginId) {
        //회원의 모든 이미지 조회
        List<ImageResponse> imageResponses = imageRepository.findAllByLoginId(loginId)
                .stream()
                .map(ImageResponse::new)
                .toList();

        return ImageListResponse.builder()
                .imageList(imageResponses)
                .build();
    }

    public ImageResponse getImageById(Long id){
        //이미지 단건 조회
        Image findImage = imageRepository.findByImageId(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이미지입니다."));

        return ImageResponse.builder()
                .image(findImage)
                .build();
    }

    //이미지 삭제
    public void delete(Long id) {
        imageRepository.deleteImage(id);
    }
}
