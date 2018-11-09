package pers.hogwarts.Manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hogwarts.Common.HttpClient;
import pers.hogwarts.model.result.UserInfoResult;

/**
 * @Description: 获取用户信息
 * @Author: Hogwarts
 * @Date: 2018/11/1
 */


public class UserManager {

    private static final String USER_INFO_URL = "https://api.weixin.qq" +
            ".com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static UserInfoResult getUserInfo(String accessToken, String openId) {

        String url =  USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        String result = HttpClient.doGet(url);

        return JSON.parseObject(result, UserInfoResult.class);
    }
}
