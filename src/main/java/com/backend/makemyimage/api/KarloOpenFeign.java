package com.backend.makemyimage.api;

import com.backend.makemyimage.DTO.request.user.JoinRequestDTO;
import com.backend.makemyimage.DTO.response.image.KarloResponseDTO;
import com.backend.makemyimage.DTO.response.user.JoinResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "KusitmsOpenFeign", url = "https://api.kakaobrain.com/v2/inference/karlo")
public interface KarloOpenFeign {
    @PostMapping("/user/join")
    JoinResponseDTO joinUser(@RequestBody JoinRequestDTO joinRequestDTO);

    @GetMapping("/test")
    String test1();

    @PostMapping("/t2i")
    KarloResponseDTO createImageKarlo(@RequestBody Map<String, String> requestBody,
                                      @RequestHeader(value="Accept") String contentType,
                                      @RequestHeader(value="Authorization") String authorizationHeader);

}
