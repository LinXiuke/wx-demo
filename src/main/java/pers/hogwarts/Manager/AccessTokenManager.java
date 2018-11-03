package pers.hogwarts.Manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.hogwarts.Common.HttpClient;
import pers.hogwarts.model.AccessTokenResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/31
 */
@Service
public class AccessTokenManager {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.secret}")
    private String secret;

    public AccessTokenResult getAccessToken() {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", appId);
        params.put("secret", secret);
        return JSON.parseObject(HttpClient.doGet(String.format("https://api.weixin.qq" +
                ".com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, secret)), AccessTokenResult.class);
    }
}
