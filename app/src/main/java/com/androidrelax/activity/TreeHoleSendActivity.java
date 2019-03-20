package com.androidrelax.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidrelax.fragment.TreeFragment;
import com.androidrelax.relax.R;
import com.androidrelax.view.tree;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity.Header;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * 树洞广场发送界面
 * @author lmc
 *
 */
public class TreeHoleSendActivity extends Activity implements OnClickListener {
	
	EditText etsendtitle;
	EditText etsendcontent;
	
	Button btnsend;
	Button btnrecord;
	Intent intent;
	
	private String sendcontent;
	private String sendtitle;
	
/*	private static String IP = "192.168.43.209:8080";*/
	private static String IP = "192.168.1.101:8080";

	String path = "http://" + IP + "/MyWebProject/SendLet"; 
	String result="";
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_treeholesend);
		etsendtitle=(EditText) findViewById(R.id.sendtitle);
		etsendcontent=(EditText) findViewById(R.id.sendcontent);
		btnsend=(Button) findViewById(R.id.sendbtn);
		btnrecord=(Button) findViewById(R.id.recordbtn);
		btnsend.setOnClickListener(this);
		btnrecord.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sendbtn:
			Toast.makeText(getApplicationContext(), "发送成功", 0).show();
			sendcontent=etsendtitle.getText().toString();
			sendtitle= etsendtitle.getText().toString();
			if (sendcontent == null || sendcontent.length() <= 0||sendtitle == null || sendtitle.length() <= 0) {
				Toast.makeText(getApplicationContext(), "心情内容不能为空",
						Toast.LENGTH_SHORT).show();
				}else {
//					final SimpleDateFormat df = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
					new Thread(new Runnable() {
						

						@Override
						public void run() {
							 JSONObject jsonObj = new JSONObject();//创建一个json对象  
							 try {
								jsonObj.put("mood_title",sendcontent);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}  
							 try {
								jsonObj.put("mood_content", sendtitle);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							 
							 NameValuePair info = new BasicNameValuePair("sendtree", jsonObj.toString());//把json对象放入一个NameValuePair中，并指定key值，服务端通过这个key值获取我们的json对象  
							 List<NameValuePair> parameters = new ArrayList<NameValuePair>();//创建一个集合，存NameValuePair对象的  
							 parameters.add(info);//把NameValuePair对象添加至集合中  
							 HttpEntity entity;
								try {
									
									entity = new UrlEncodedFormEntity(parameters,
											HTTP.UTF_8);
									HttpPost httpPost = new HttpPost(path);
									
									HttpParams params = new BasicHttpParams();//创建一个参数对象，用来存储设置的各个参数  
								    HttpProtocolParams.setContentCharset(params, "UTF-8");// 设置消息所用的字符集  
								    HttpProtocolParams.setVersion(params,HttpVersion.HTTP_1_1);//设置http协议版本  
								    ConnManagerParams.setTimeout(params, 1000);//从连接池中取连接的超时时间  
								    
								    HttpConnectionParams.setConnectionTimeout(params, 5000);// 设置连接超时时间  
								    
								    HttpConnectionParams.setSoTimeout(params, 5000);// 设置服务器响应超时时间  
								    //设置HttpClient支持HTTp和HTTPS两种协议    
								        SchemeRegistry schReg = new SchemeRegistry();    
								        schReg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(), 80));//http协议默认端口是80    
								       // schReg.register(new Scheme("https",SSLSocketFactory.getSocketFactory(), 443));//https协议默认端口是443    
								        //使用线程安全的连接管理来创建HttpClient    
								    ClientConnectionManager conman = new ThreadSafeClientConnManager(params, schReg);  
								    HttpClient client = new DefaultHttpClient(conman, params);//创建一个发送请求的客户端  	
									httpPost.setEntity(entity);
//									HttpClient client = new DefaultHttpClient();
									HttpResponse httpResponse = client
											.execute(httpPost);//发送请求,获得数据
									Log.i("httpResponse",httpResponse.toString());
									
									
									
									if (httpResponse.getStatusLine()
											.getStatusCode() == HttpStatus.SC_OK) {//请求成功
										
										result = EntityUtils.toString(
												httpResponse.getEntity(), "utf-8");
										System.out.println(result);
//									JSONObject responseJsonObj = new JSONObject(result);//通过json格式的字符串得到该返回实体的json对象  
//							        boolean results = responseJsonObj.getBoolean("json");//得到服务器返回的数据key对应的值
//							        String  treeresults=String.valueOf(results);
									} else {
										result="fail";
									}
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ClientProtocolException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
								/*TreeFragment fragment1 = new TreeFragment();
				                Bundle bundle = new Bundle();
				                bundle.putString("111", result);
				                System.out.println(bundle);
				                fragment1.setArguments(bundle);*///数据传递到fragment中
			
								/*
								ShowMessage.title=result;
								System.out.println(result);
								ShowMessage.content=result;*/
								Message msg = new Message();
								msg.obj = result;
								handler.sendMessage(msg);
						}
						

					}).start();
					
					setResult(10);
					
					//结束activity的生命周期
					finish();
					
					
					
				}
			
			
			break;
        case R.id.recordbtn:
        	intent=new Intent(getApplicationContext(),TreeHoleRecordActivity.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}
		
	}
	  
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			 
//			data = (List<tree>) msg.obj;
//			adapter = new TreeAdapter(getActivity(), R.layout.item, data);
//			listview.setAdapter(adapter);
//			adapter.notifyDataSetChanged();
			if (true) {
				Toast.makeText(getApplicationContext(), "心情发表成功",
						Toast.LENGTH_SHORT).show();
			}
//			else {
//				Toast.makeText(getApplicationContext(), "发生未知错误导致心情发表失败",
//						Toast.LENGTH_SHORT).show();
//			}
		};
	};
	
}
