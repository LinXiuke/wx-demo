package pers.hogwarts.Common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/31
 */

public class HttpClient {

    public static String doGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,  String.class);
        return JSON.parseObject(responseEntity.getBody());
    }

    public static JSONObject doPost(String url, MultiValueMap<String, Object> params, HttpHeaders httpHeaders) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity,  String.class);
        return JSON.parseObject(responseEntity.getBody());
    }

}
