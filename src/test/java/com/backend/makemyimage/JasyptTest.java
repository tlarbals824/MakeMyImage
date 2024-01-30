package com.backend.makemyimage;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JasyptTest {

    @Test
    @DisplayName("DB정보 암호화 테스트")
    void stringEncryptor() {
        String url = "db_url";
        String username = "user_name";
        String password = "user_password";
        String restapikey = "rest_api_key";

//        System.out.println(jasyptEncoding(url));
//        System.out.println(jasyptEncoding(username));
//        System.out.println(jasyptEncoding(password));
        System.out.println(jasyptEncoding(restapikey));


        int a = 1;
        Assertions.assertThat(a).isEqualTo(1);
    }

    public String jasyptEncoding(String value) {
        String key = "my_key";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        return pbeEnc.encrypt(value);
    }
}
