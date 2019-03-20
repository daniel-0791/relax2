package com.androidrelax.web;

public class  SendToServer {

	

//	private static String IP = "10.128.134.84:8080";
//	 
//	 static HttpClient hc=new DefaultHttpClient();
//	 public static String executeHttpPost(String title, String content) throws JSONException, ClientProtocolException, IOException {
//	  String path = "http://" + IP + "/MyWebProject/Sendlet";  
//	  HttpPost post = new HttpPost(path);//创建Post请求对象  
//	    //----------------------------以JSON方式请求-----------------------  
//	    JSONObject jsonObj = new JSONObject();//创建一个json对象  
//	    jsonObj.put("mood_title", title);  
//	    jsonObj.put("mood_content", content);
//	    NameValuePair info = new BasicNameValuePair("sendtree", jsonObj.toString());//把json对象放入一个NameValuePair中，并指定key值，服务端通过这个key值获取我们的json对象  
//	    List<NameValuePair> parameters = new ArrayList<NameValuePair>();//创建一个集合，存NameValuePair对象的  
//	    parameters.add(info);//把NameValuePair对象添加至集合中  
//		
//	    post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));//把这个封装好需要发送数据的集合放进post请求的实体中  
//	    HttpResponse sendresponse = hc.execute(post);//执行post请求，即把数据发给服务器  
//	   
//	    if (sendresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {//通过返回的响应对象来得到状态行，从状态行中再得到状态码，判断连接服务器成功没  
//	          
//	        //-------------------服务器发来JSON数据的解析-----------------  
//	        HttpEntity entity = sendresponse.getEntity();//通过返回的响应对象得到服务器返回的消息实体，服务器返回的消息都打包在这个实体中  
//	        String responseStr = EntityUtils.toString(entity, "UTF-8");//把实体转化为String字符串，因为服务器是以json形式返回的数据，所以字符串格式是json格式的  
//	        JSONObject responseJsonObj = new JSONObject(responseStr);//通过json格式的字符串得到该返回实体的json对象  
//	        boolean result = responseJsonObj.getBoolean("json");//得到服务器返回的数据key对应的值，和上面的name和password的key一样的效果，服务器也要返回key值给客户端获取数据  
//	        
//	        
//	        hc.getConnectionManager().shutdown();//关闭连接，释放资源  
//	    }
//		return path;
//	 }
//	           
	 
}


