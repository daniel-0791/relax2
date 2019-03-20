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
 * �����㳡���ͽ���
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
			Toast.makeText(getApplicationContext(), "���ͳɹ�", 0).show();
			sendcontent=etsendtitle.getText().toString();
			sendtitle= etsendtitle.getText().toString();
			if (sendcontent == null || sendcontent.length() <= 0||sendtitle == null || sendtitle.length() <= 0) {
				Toast.makeText(getApplicationContext(), "�������ݲ���Ϊ��",
						Toast.LENGTH_SHORT).show();
				}else {
//					final SimpleDateFormat df = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
					new Thread(new Runnable() {
						

						@Override
						public void run() {
							 JSONObject jsonObj = new JSONObject();//����һ��json����  
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
							 
							 NameValuePair info = new BasicNameValuePair("sendtree", jsonObj.toString());//��json�������һ��NameValuePair�У���ָ��keyֵ�������ͨ�����keyֵ��ȡ���ǵ�json����  
							 List<NameValuePair> parameters = new ArrayList<NameValuePair>();//����һ�����ϣ���NameValuePair�����  
							 parameters.add(info);//��NameValuePair���������������  
							 HttpEntity entity;
								try {
									
									entity = new UrlEncodedFormEntity(parameters,
											HTTP.UTF_8);
									HttpPost httpPost = new HttpPost(path);
									
									HttpParams params = new BasicHttpParams();//����һ���������������洢���õĸ�������  
								    HttpProtocolParams.setContentCharset(params, "UTF-8");// ������Ϣ���õ��ַ���  
								    HttpProtocolParams.setVersion(params,HttpVersion.HTTP_1_1);//����httpЭ��汾  
								    ConnManagerParams.setTimeout(params, 1000);//�����ӳ���ȡ���ӵĳ�ʱʱ��  
								    
								    HttpConnectionParams.setConnectionTimeout(params, 5000);// �������ӳ�ʱʱ��  
								    
								    HttpConnectionParams.setSoTimeout(params, 5000);// ���÷�������Ӧ��ʱʱ��  
								    //����HttpClient֧��HTTp��HTTPS����Э��    
								        SchemeRegistry schReg = new SchemeRegistry();    
								        schReg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(), 80));//httpЭ��Ĭ�϶˿���80    
								       // schReg.register(new Scheme("https",SSLSocketFactory.getSocketFactory(), 443));//httpsЭ��Ĭ�϶˿���443    
								        //ʹ���̰߳�ȫ�����ӹ���������HttpClient    
								    ClientConnectionManager conman = new ThreadSafeClientConnManager(params, schReg);  
								    HttpClient client = new DefaultHttpClient(conman, params);//����һ����������Ŀͻ���  	
									httpPost.setEntity(entity);
//									HttpClient client = new DefaultHttpClient();
									HttpResponse httpResponse = client
											.execute(httpPost);//��������,�������
									Log.i("httpResponse",httpResponse.toString());
									
									
									
									if (httpResponse.getStatusLine()
											.getStatusCode() == HttpStatus.SC_OK) {//����ɹ�
										
										result = EntityUtils.toString(
												httpResponse.getEntity(), "utf-8");
										System.out.println(result);
//									JSONObject responseJsonObj = new JSONObject(result);//ͨ��json��ʽ���ַ����õ��÷���ʵ���json����  
//							        boolean results = responseJsonObj.getBoolean("json");//�õ����������ص�����key��Ӧ��ֵ
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
				                fragment1.setArguments(bundle);*///���ݴ��ݵ�fragment��
			
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
					
					//����activity����������
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
				Toast.makeText(getApplicationContext(), "���鷢��ɹ�",
						Toast.LENGTH_SHORT).show();
			}
//			else {
//				Toast.makeText(getApplicationContext(), "����δ֪���������鷢��ʧ��",
//						Toast.LENGTH_SHORT).show();
//			}
		};
	};
	
}
