package com.androidrelax.web;

public class  SendToServer {

	

//	private static String IP = "10.128.134.84:8080";
//	 
//	 static HttpClient hc=new DefaultHttpClient();
//	 public static String executeHttpPost(String title, String content) throws JSONException, ClientProtocolException, IOException {
//	  String path = "http://" + IP + "/MyWebProject/Sendlet";  
//	  HttpPost post = new HttpPost(path);//����Post�������  
//	    //----------------------------��JSON��ʽ����-----------------------  
//	    JSONObject jsonObj = new JSONObject();//����һ��json����  
//	    jsonObj.put("mood_title", title);  
//	    jsonObj.put("mood_content", content);
//	    NameValuePair info = new BasicNameValuePair("sendtree", jsonObj.toString());//��json�������һ��NameValuePair�У���ָ��keyֵ�������ͨ�����keyֵ��ȡ���ǵ�json����  
//	    List<NameValuePair> parameters = new ArrayList<NameValuePair>();//����һ�����ϣ���NameValuePair�����  
//	    parameters.add(info);//��NameValuePair���������������  
//		
//	    post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));//�������װ����Ҫ�������ݵļ��ϷŽ�post�����ʵ����  
//	    HttpResponse sendresponse = hc.execute(post);//ִ��post���󣬼������ݷ���������  
//	   
//	    if (sendresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {//ͨ�����ص���Ӧ�������õ�״̬�У���״̬�����ٵõ�״̬�룬�ж����ӷ������ɹ�û  
//	          
//	        //-------------------����������JSON���ݵĽ���-----------------  
//	        HttpEntity entity = sendresponse.getEntity();//ͨ�����ص���Ӧ����õ����������ص���Ϣʵ�壬���������ص���Ϣ����������ʵ����  
//	        String responseStr = EntityUtils.toString(entity, "UTF-8");//��ʵ��ת��ΪString�ַ�������Ϊ����������json��ʽ���ص����ݣ������ַ�����ʽ��json��ʽ��  
//	        JSONObject responseJsonObj = new JSONObject(responseStr);//ͨ��json��ʽ���ַ����õ��÷���ʵ���json����  
//	        boolean result = responseJsonObj.getBoolean("json");//�õ����������ص�����key��Ӧ��ֵ���������name��password��keyһ����Ч����������ҲҪ����keyֵ���ͻ��˻�ȡ����  
//	        
//	        
//	        hc.getConnectionManager().shutdown();//�ر����ӣ��ͷ���Դ  
//	    }
//		return path;
//	 }
//	           
	 
}


