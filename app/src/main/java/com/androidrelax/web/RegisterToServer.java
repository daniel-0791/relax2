package com.androidrelax.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

public class RegisterToServer {

	 private static String IP = "192.168.43.209:8080";
	    static HttpClient hc=new DefaultHttpClient();// HttpClient ��һ���ӿڣ��޷�ʵ��������������ͨ���ᴴ��һ��DefaultHttpClientʵ��
	    // ͨ��Get��ʽ��ȡHTTP����������
	    public static String executeHttpGet(String username, String password) {

	        HttpURLConnection conn = null;
//	        InputStream is = null;
	        String response="";
	        try {
	            // �û��� ����
	            // URL ��ַ
	            String path = "http://" + IP + "/MyWebProject/RegLet";
	            path = path + "?user_id=" + username + "&user_password=" + password;
	            HttpGet httpGet=new HttpGet(path);//����GET����
//	            conn = (HttpURLConnection) new URL(path).openConnection();
//	            conn.setConnectTimeout(3000); // ���ó�ʱʱ��
//	            conn.setReadTimeout(3000);
//	            conn.setDoInput(true);
//	            conn.setRequestMethod("GET"); // ���û�ȡ��Ϣ��ʽ
//	            conn.setRequestProperty("Charset", "UTF-8"); // ���ý������ݱ����ʽ
	           
	            HttpResponse hp=hc.execute(httpGet);// ����HttpClient�����execute()����
	            
//	            OutputStream os = conn.getOutputStream();
//	            byte[] data = sb.toString().getBytes();// ���ַ���ת�����ֽ����飬��Ϊǰ����ַ����洢��StringBuilder�����Ҫת�����ַ���ͨ��toString
//	            os.write(data);// �����ϴ������������writeд����
//	            os.flush();
	            
	         // ״̬��200˵����Ӧ�ɹ�
	            if (hp.getStatusLine().getStatusCode()== 200) {
	            	HttpEntity he=hp.getEntity(); // ȡ�����ĵľ�������
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
	        
	        
	        
//	        finally {
//	            // �����˳�ʱ�������ӹرձ���
//	            if (conn != null) {
//	                conn.disconnect();
//	            }
//	            if (is != null) {
//	                try {
//	                    is.close();
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//	                }
//	            }
	//
//	        }
//	        return false;
//	    }
	   

	    
}