package com.androidrelax.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidrelax.relax.R;
import com.androidrelax.web.LogToServer;

public class LoginActivity extends Activity implements OnClickListener {
	private static final String Intent = null;
	// ??????
    private Button logbtn,regbtn;
    // ???????????????
    private TextView infotv, regtv;
    // ??????????????
    EditText username, password;
    // ?????????
    private ProgressDialog dialog;
    // ?????????
    String info;
    // ????????????????
    private static Handler handler = new Handler();
    Intent  intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// ??????
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        logbtn = (Button) findViewById(R.id.btn_login);
        regbtn = (Button) findViewById(R.id.btn_register);
        infotv = (TextView) findViewById(R.id.infotv);
        
        // ????????????
        logbtn.setOnClickListener(this);
        regbtn.setOnClickListener(this);
        
	}
	
	
	
    public boolean login(String username,String password) {
    	LogToServer httpClientToServer=new LogToServer();
    	String response=httpClientToServer.executeHttpGet(username, password);
    	Log.i("?????", response.toString());
    	if("true".equals(response)) {
    		return true;
    	}else {
    		return false;
    	}
    }
	
        
    // ???????
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }    
	
//    // ?????????????????????????
//    public class MyThread implements Runnable {
//        @Override
//        public void run() {
//            info = Webservice.executeHttpGet(username.getText().toString(), password.getText().toString());
//            //info = WebservicePost.executeHttpPost(username.getText().toString(), password.getText().toString());
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    infotv.setText(info);
//                    dialog.dismiss();
//                }
//            });
//        }
//    }
    
	@Override
	  public void onClick(View v) {
	        switch (v.getId()) {
	        case R.id.btn_login:
	            // ?????????????wifi
//	        	 if (!checkNetwork()) {
//		                Toast toast = Toast.makeText(LoginActivity.this,"?????????", Toast.LENGTH_SHORT);
//		                toast.setGravity(Gravity.CENTER, 0, 0);
//		                toast.show();
//		                break;
//		            }  
	        	 //Log.i("jianting", "???????");
	        	 final Handler myHandler=new Handler() {        		
	        		 public void handleMessage(Message msg) {
	        			 boolean loged=(Boolean)msg.obj;
	        			 System.out.print("msg.obj"+loged);
	        			 if(loged) {
	        				 intent=new Intent(getApplicationContext(), MainActivity.class);
	        	             startActivity(intent);
	        	             finish();
	        	             Toast.makeText(LoginActivity.this, "??????", 1000).show();
	        			 }else {
	        				 Toast.makeText(LoginActivity.this, "??????", 1000).show();
	        			 }
	        		 }
	        	 };
	        	 
	        	 new Thread() {
	        		 public void run() {
	        			 boolean loged=login(username.getText().toString(), password.getText().toString());
	        			 System.out.print("????????????");
	        			 Log.i("jianting", "????????????");
	        			 Message message=new Message();
	        			 message.obj=loged;
	        			 myHandler.sendMessage(message);
	        		 }
	        	 }.start();
	        	 
	        	 
	        	 
	            // ?????
//	            dialog = new ProgressDialog(this);
//	            dialog.setTitle("???");
//	            dialog.setMessage("?????????????...");
//	            dialog.setCancelable(false);
//	            dialog.show();
	            // ????????????????Get??Post????
	           // new Thread(new MyThread()).start();
//	            Handler handler=new Handler(){
//	                
//	                public void handleMessage(android.os.Message msg) {
//	                    if (msg.what==12) {
//	                        String sss=(String)msg.obj;
//	                        Toast.makeText(LoginActivity.this, sss, Toast.LENGTH_LONG).show();
//	                    }
//	                };
//	            };
           
	            break;
	        case R.id.btn_register:
	            Intent regItn = new Intent(LoginActivity.this, Register.class);
	            // overridePendingTransition(anim_enter);
	            startActivity(regItn);
	            break;
	        }
	    }

	
	



	
        
	


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
