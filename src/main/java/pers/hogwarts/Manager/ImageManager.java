package pers.hogwarts.Manager;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import pers.hogwarts.Common.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/31
 */
public class ImageManager {

    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=image";

    public static JSONObject upload(String filePath, String accessToken) {

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", "image");

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        try {
            params.add("media", new FileSystemResource(ResourceUtils.getFile(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Connection", "Keep-Alive");
        httpHeaders.set("Charset", "UTF-8");
        httpHeaders.set("Content-Type", "multipart/form-data;boundary=" + System.currentTimeMillis());
        JSONObject result = HttpClient.doPostJSON(url, params, httpHeaders);

        return result;
    }




    //不实用restTemplate上传
    private static String uploadFile(String filePath, String accessToken, String type) throws Exception{
        File file = ResourceUtils.getFile(filePath);
        if(!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在！");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
        URL urlObj = new URL(url);

        //连接
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        //请求头
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        //conn.setRequestProperty("Content-Type","multipart/form-data;");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"file\";filename=\""+file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //输出流
        OutputStream out = new DataOutputStream(conn.getOutputStream());

        out.write(head);

        //文件正文部分
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while((bytes = in.read(bufferOut))!=-1) {
            out.write(bufferOut,0,bytes);
        }
        in.close();

        //结尾
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
        out.write(foot);
        out.flush();
        out.close();

        //获取响应
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;

        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        while((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        if(result == null) {
            result = buffer.toString();
        }
        reader.close();

        return result;
    }
}
