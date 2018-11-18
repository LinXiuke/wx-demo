package pers.hogwarts.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        return responseEntity.getBody();
    }

    public static JSONObject doGetReturnJSON(String url) {
        return JSONObject.parseObject(doGet(url));
    }

    public static String doPost(String url, MultiValueMap<String, Object> params, HttpHeaders httpHeaders) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity,  String.class);
        return responseEntity.getBody();
    }

    public static JSONObject doPostReturnJSON(String url, MultiValueMap<String, Object> params, HttpHeaders httpHeaders) {
        return JSONObject.parseObject(doPost(url, params, httpHeaders));
    }

    /**
     * post方法json传参
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, JSONObject params) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(params.toJSONString(), httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity,  String.class);
        return responseEntity.getBody();
    }

    public static JSONObject doPostReturnJSON(String url, JSONObject params) {
        return JSONObject.parseObject(doPost(url, params));
    }
}
