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
    // ͨ��Get��ʽ��ȡHTTP����������
    public static String executeHttpGet(String username, String password) {

//        HttpURLConnection conn = null;
//        InputStream is = null;
        String response="";
        try {
            // �û��� ����
            // URL ��ַ
            String path = "http://" + IP + "/MyWebProject/LogLet";
            path = path + "?user_id=" + username + "&user_password=" + password;
            HttpGet httpGet=new HttpGet(path);
//            conn = (HttpURLConnection) new URL(path).openConnection();
//            conn.setConnectTimeout(3000); // ���ó�ʱʱ��
//            conn.setReadTimeout(3000);
//            conn.setDoInput(true);
//            conn.setRequestMethod("GET"); // ���û�ȡ��Ϣ��ʽ
//            conn.setRequestProperty("Charset", "UTF-8"); // ���ý������ݱ����ʽ
           
            HttpResponse hp=hc.execute(httpGet);
            
//            OutputStream os = conn.getOutputStream();
//            byte[] data = sb.toString().getBytes();// ���ַ���ת�����ֽ����飬��Ϊǰ����ַ����洢��StringBuilder�����Ҫת�����ַ���ͨ��toString
//            os.write(data);// �����ϴ������������writeд����
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
//            // �����˳�ʱ�������ӹرձ���
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
   

    
    // ��������ת��Ϊ String �� 
    private static String parseInfo(InputStream inStream) throws Exception {
        byte[] data = read(inStream);
        // ת��Ϊ�ַ���
        return new String(data, "UTF-8");
    }

    // ��������ת��Ϊbyte�� 
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