package com.androidrelax.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.string;

public class LogToServer {

    private static String IP = "192.168.43.209:8080";
    static HttpClient hc=new DefaultHttpClient();
    // 通过Get方式获取HTTP服务器数据
    public static String executeHttpGet(String username, String password) {

//        HttpURLConnection conn = null;
//        InputStream is = null;
        String response="";
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/MyWebProject/LogLet";
            path = path + "?user_id=" + username + "&user_password=" + password;
            HttpGet httpGet=new HttpGet(path);
//            conn = (HttpURLConnection) new URL(path).openConnection();
//            conn.setConnectTimeout(3000); // 设置超时时间
//            conn.setReadTimeout(3000);
//            conn.setDoInput(true);
//            conn.setRequestMethod("GET"); // 设置获取信息方式
//            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
           
            HttpResponse hp=hc.execute(httpGet);
            
//            OutputStream os = conn.getOutputStream();
//            byte[] data = sb.toString().getBytes();// 将字符串转换成字节数组，因为前面的字符串存储在StringBuilder里，所以要转换成字符串通过toString
//            os.write(data);// 数据上传是输出流，是write写数据
//            os.flush();
            
            
            if (hp.getStatusLine().getStatusCode() == 200) {
            	HttpEntity he=hp.getEntity();
            	InputStream is=he.getContent();
            	BufferedReader br=new BufferedReader(new InputStreamReader(is,"GBK"));
            	String readline=null;
            	while((readline=br.readLine())!=null) {
            		response=response+readline;
            	}
               // is = conn.getInputStream();
            	is.close();
                
             }
            return response;

        }catch (Exception e) {
            e.printStackTrace();
            return "exception";
        } 
        
    } 
        
        
        
//        finally {
//            // 意外退出时进行连接关闭保护
//            if (conn != null) {
//                conn.disconnect();
//            }
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return false;
//    }
   

    
    // 将输入流转化为 String 型 
    private static String parseInfo(InputStream inStream) throws Exception {
        byte[] data = read(inStream);
        // 转化为字符串
        return new String(data, "UTF-8");
    }

    // 将输入流转化为byte型 
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }
}