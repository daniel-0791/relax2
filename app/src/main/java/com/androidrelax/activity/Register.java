package com.androidrelax.activity;


import android.text.TextUtils;
import android.util.Log;

import java.util.jar.Attributes.Name;

import org.apache.http.protocol.ResponseDate;

import com.androidrelax.relax.R;
import com.androidrelax.web.RegisterToServer;
import com.mob.MobSDK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import android.widget.FrameLayout.LayoutParams;
@SuppressLint("NewApi")
public class Register extends Activity implements OnClickListener{
    ImageView f_reg;
    Button register1;
    EditText name;
    EditText psd;
    EditText repsd;
    private EditText inputCodeEt; // ??????????           
    private Button requestCodeBtn;// ????????? 
    String APPKEY = "296e8d6e30690";    
    String APPSECRETE = "8809867c4c03e94fc937f8ea93da6d05";
    //    
    int i = 30;    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		f_reg=(ImageView) findViewById(R.id.fanhui_reg);
		register1=(Button) findViewById(R.id.btn_register1);
		psd=(EditText) findViewById(R.id.password1);
		repsd=(EditText) findViewById(R.id.repassword1);
		name=(EditText) findViewById(R.id.telnumber);
		inputCodeEt=(EditText) findViewById(R.id.login_input_code_et);
		requestCodeBtn=(Button) findViewById(R.id.login_request_code_btn);
		 // ????????????
        f_reg.setOnClickListener(this);
        register1.setOnClickListener(this);
		requestCodeBtn.setOnClickListener(this);
		
		 // ???????????sdk    
        MobSDK.init(this, APPKEY, APPSECRETE);    
        EventHandler eventHandler = new EventHandler(){    
           @Override    
           public void afterEvent(int event, int result, Object data) {    
                Message msg=new Message();
                msg.arg1 = event;    
                msg.arg2 = result;    
                msg.obj = data;    
                handler.sendMessage(msg);    
            }    
        }; 
      //????????????    
        SMSSDK.registerEventHandler(eventHandler);  
		
	}
	
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String phoneNums = name.getText().toString(); 
			if(v.getId()==R.id.login_request_code_btn) {
				
				   // 1. ????????§Ø??????    
	            if (!judgePhoneNums(phoneNums)) {    
	                return;    
	            } // 2. ???sdk??????????    
	            SMSSDK.getVerificationCode("86", phoneNums);    
	    
      // 3. ????????????????????????????????????    
	            requestCodeBtn.setClickable(false);    
	            requestCodeBtn.setText("???¡¤???(" + i + ")");    
	            new Thread(new Runnable() {    
	                @Override    
	                public void run() {    
	                    for (; i > 0; i--) {    
	                        handler.sendEmptyMessage(-9);    
	                        if (i <= 0) {    
	                            break;    
	                        }    
	                        try {    
	                            Thread.sleep(1000);    
	                        } catch (InterruptedException e) {    
	                            e.printStackTrace();    
	                        }    
	                    }    
	                    handler.sendEmptyMessage(-8);    
	                }    
	            }).start();  
			}
			if(v.getId()==R.id.fanhui_reg) {
				Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
	            startActivity(intent);
			}
			if(v.getId()==R.id.btn_register1) {
				String checkResult=checkInfo();//???????????????
				if(checkResult!=null) {
					Builder builder=new Builder(Register.this);
					builder.setTitle("???????");//???????????
					builder.setMessage(checkResult);//?????????
					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							psd.setText("");   //????????????
							repsd.setText("");  //???????????????
						}
					});
					builder.create().show(); //????????????
				}else {
					System.out.println("????else????????");
//					//??????????????????????¦Ê??    
//		         SMSSDK.submitVerificationCode("86", phoneNums, inputCodeEt    
//		                    .getText().toString());
					final Handler myHandler1=new Handler() {
							public void handleMessage(Message msg) {
							String result=(String) msg.obj;//????????????
							Log.i("???",result);
							if("true".equals(result)) {
								
								Intent intent=new Intent(Register.this,LoginActivity.class);
								startActivity(intent);
								 Toast.makeText(Register.this, "?????", 1000).show();
							}else {
								 Toast.makeText(Register.this, "??????", 1000).show();
							}
						}
					};
						
					 new Thread() {
		        		 public void run() {
		        			 RegisterToServer registerToServer=new RegisterToServer();
		        			 String response=registerToServer.executeHttpGet(name.getText().toString(),psd.getText().toString());
		        			 System.out.print("Thread....."+response);
		        			 Message message=new Message();
		        			 message.obj=response;
		        			 myHandler1.sendMessage(message);
		        		 }
		        	 }.start();
				}
			}
			
		}
		
		
		
		/**  
	     *   
	     */    
	    Handler handler = new Handler() {    
	        public void handleMessage(Message msg) {    
	            if (msg.what == -9) {    
	                requestCodeBtn.setText("???¡¤???(" + i + ")");    
	            } else if (msg.what == -8) {    
	                requestCodeBtn.setText("????????");    
	                requestCodeBtn.setClickable(true);    
	                i = 30;    
	            } else {    
	                int event = msg.arg1;    
	                int result = msg.arg2;    
	                Object data = msg.obj;    
	                Log.e("event", "event=" + event);    
	                if (result == SMSSDK.RESULT_COMPLETE) {    
	                    // ??????????????MainActivity,??????    
	                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// ?????????    
                            Toast.makeText(getApplicationContext(), "?????????",    
	                                Toast.LENGTH_SHORT).show();    
	                        Intent intent = new Intent(Register.this,    
	                              LoginActivity.class);    
	                        startActivity(intent);    
	                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {    
	                        Toast.makeText(getApplicationContext(), "???????????",    
	                                Toast.LENGTH_SHORT).show();    
	                    } else {    
	                        ((Throwable) data).printStackTrace();    
	                    }    
	                }    
	            }    
	        }    
	    };    
		
	    
	    /**  
	     * ?§Ø??????????????  
	     *   
	     * @param phoneNums  
	     */    
	    private boolean judgePhoneNums(String phoneNums) {    
	        if (isMatchLength(phoneNums, 11)    
	                && isMobileNO(phoneNums)) {    
	            return true;    
	        }    
	        Toast.makeText(this, "???????????????",Toast.LENGTH_SHORT).show();    
	        return false;    
	    }    
	    
	    /**  
	     * ?§Ø???????????¦Ë??  
	     * @param str  
	     * @param length  
	     * @return  
	     */    
	    public static boolean isMatchLength(String str, int length) {    
	        if (str.isEmpty()) {    
	            return false;    
	        } else {    
	            return (str.length() == length) ?true:false; 
	         }
	    }    
	    
	    /**  
	     * ?????????  
	     */    
	    public static boolean isMobileNO(String mobileNums) {    
	        /*  
	         * ?????134??135??136??137??138??139??150??151??157(TD)??158??159??187??188  
	         * ?????130??131??132??152??155??156??185??186 ?????133??153??180??189????1349?????  
	         * ?????????????¦Ë????1?????¦Ë????3??5??8??????¦Ë???????0-9  
	         */    
	        String telRegex = "[1][358]\\d{9}";// "[1]"?????1¦Ë?????1??"[358]"??????¦Ë?????3??5??8?§Ö??????"\\d{9}"??????????????0??9?????????9¦Ë??    
	        if (TextUtils.isEmpty(mobileNums))    
	            return false;    
	        else    
	            return mobileNums.matches(telRegex);    
	    }    
	    
	    /**  
	     * progressbar  
	     */    
	    private void createProgressBar() {    
	        FrameLayout layout = (FrameLayout) findViewById(android.R.id.content);    
	        LayoutParams layoutParams = new LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);    
	        layoutParams.gravity = Gravity.CENTER;    
	        ProgressBar mProBar = new ProgressBar(this);    
	        mProBar.setLayoutParams(layoutParams);    
	        mProBar.setVisibility(View.VISIBLE);    
	        layout.addView(mProBar);    
	    }    
	    
	    @Override    
	    protected void onDestroy() {    
	        SMSSDK.unregisterAllEventHandler();    
	        super.onDestroy();   
	    }    
	    
		
	public String checkInfo() {
		if(name.getText().toString()==null||name.getText().toString().equals("")) {
			return "????????????";
		}
		if(psd.getText().toString().trim().length()<3||psd.getText().toString().trim().length()>15) {
			return "???????";
		}if(!psd.getText().toString().equals(repsd.getText().toString())) {
			return "???????????????";
		}
		return null;
	}
	

	


}
