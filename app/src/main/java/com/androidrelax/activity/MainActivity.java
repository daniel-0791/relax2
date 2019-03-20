package com.androidrelax.activity;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidrelax.fragment.ListenFragment;
import com.androidrelax.fragment.MailFragment;
import com.androidrelax.fragment.MeFragment;
import com.androidrelax.fragment.TreeFragment;
import com.androidrelax.relax.R;
public class MainActivity extends Activity implements OnClickListener{

    /**
     * UI
     */
    private RelativeLayout listenView;
    private RelativeLayout mailView;
    private RelativeLayout treeView;
    private RelativeLayout meView;
    
    private ImageView listenImage;
    private ImageView mailImage;
    private ImageView treeImage;
    private ImageView meImage;


    private android.app.FragmentManager fragmentManager;
   /* protected Fragment mainFragment;
    protected MineFragment mineFragment;
    protected FindFragment findFragment;*/
    
    private ListenFragment listenFragment;
    private MailFragment mailFragment;
    private MeFragment meFragment;
    private TreeFragment treeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        
        	 listenFragment = new ListenFragment();
           
             fragmentManager = getFragmentManager();
             FragmentTransaction transaction =fragmentManager.beginTransaction();
            
             transaction.replace(R.id.vpager,listenFragment).commit();
             initView();
             listenImage.setBackgroundResource(R.drawable.mainlisten1);
        
       
    }
    
   
    /*protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
//    	 Intent intent =getIntent();
//        intent.getExtras().getBoolean("isfromtreehole"))
         	treeFragment=new TreeFragment();
         	fragmentManager = getFragmentManager();
             FragmentTransaction transaction =fragmentManager.beginTransaction();
           
             transaction.replace(R.id.vpager,listenFragment).commit();
             initView();
             listenImage.setBackgroundResource(R.drawable.maintree1);
         
    }*/

    private void initView() {
        listenView = (RelativeLayout) findViewById(R.id.rl_listen);
        mailView = (RelativeLayout) findViewById(R.id.rl_mail);
        treeView= (RelativeLayout) findViewById(R.id.rl_treehole);
        meView= (RelativeLayout) findViewById(R.id.rl_me);
        
        listenImage = (ImageView) findViewById(R.id.listen_tab);
        mailImage = (ImageView) findViewById(R.id.mail_tab);
        treeImage = (ImageView) findViewById(R.id.treehole_tab);
        meImage = (ImageView) findViewById(R.id.me_tab);


        listenView.setOnClickListener(this);
        mailView.setOnClickListener(this);
        treeView.setOnClickListener(this);
        meView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        
        hideFragment(transaction);
        switch (v.getId()){
        
            case R.id.rl_listen:
                mailImage.setBackgroundResource(R.drawable.mainmail);
                treeImage.setBackgroundResource(R.drawable.maintree);
                meImage.setBackgroundResource(R.drawable.mainme);
                listenImage.setBackgroundResource(R.drawable.mainlisten1);
                
                
//                //????????????fragment
//                hideFragment(mailFragment,transaction);
//                hideFragment(treeFragment,transaction);
                
                
              //???????fragment???????¦Ë??
                if(listenFragment == null){
                    listenFragment = new ListenFragment();
                    transaction.add(R.id.vpager,listenFragment);
                }else{
                    transaction.show(listenFragment);
                }
                break;
            case R.id.rl_mail:
            	 mailImage.setBackgroundResource(R.drawable.mainmail1);
                 treeImage.setBackgroundResource(R.drawable.maintree);
                 meImage.setBackgroundResource(R.drawable.mainme);
                 listenImage.setBackgroundResource(R.drawable.mainlisten);
                

//                 System.out.println("111111111111111111"+mailFragment);
                if(mailFragment == null){
                	mailFragment = new MailFragment();
//                	 System.out.println("111111111111111111"+mailFragment);
                	 
                    transaction.add(R.id.vpager,mailFragment);
//                    System.out.println("111111111111111111");
                }else{
                    transaction.show(mailFragment);
//                    System.out.println("22222222222222222222");
                }
                break;
            case R.id.rl_me:
            	 mailImage.setBackgroundResource(R.drawable.mainmail);
                 treeImage.setBackgroundResource(R.drawable.maintree);
                 meImage.setBackgroundResource(R.drawable.mainme1);
                 listenImage.setBackgroundResource(R.drawable.mainlisten);
               
    
                if(meFragment == null){
                	meFragment = new MeFragment();
                    transaction.add(R.id.vpager,meFragment);
                }else{
                    transaction.show(meFragment);
                }
                break;
            case R.id.rl_treehole:
           	    mailImage.setBackgroundResource(R.drawable.mainmail);
                treeImage.setBackgroundResource(R.drawable.maintree1);
                meImage.setBackgroundResource(R.drawable.mainme);
                listenImage.setBackgroundResource(R.drawable.mainlisten);
              
   
               if(treeFragment == null){
                   treeFragment = new TreeFragment();
                   transaction.add(R.id.vpager,treeFragment);
               }else{
                   transaction.show(treeFragment);
               }
               break;
                
        }
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //?????fragment?????????????????
        if (listenFragment != null) {
            fragmentTransaction.hide(listenFragment);
        }
        if (mailFragment != null) {
            fragmentTransaction.hide(mailFragment);
        }
        if (treeFragment != null) {
            fragmentTransaction.hide(treeFragment);
        }
        if (meFragment != null) {
            fragmentTransaction.hide(meFragment);
        }
    }


	

    
}

/*public class MainActivity extends Activity {
//	public Gallery showimage;
	
	private ImageView iv;
	private ImageView lvtreeicon;
	
	private RadioGroup  rg_tabs;
    private RadioButton tab_listen;
    private RadioButton tab_mail;
    private RadioButton tab_me;
    private RadioButton tab_tree;
    
    private ListenFragment listenFragment;
    private MailFragment mailFragment;
    private MeFragment meFragment;
    private TreeFragment treeFragment;
	  

//
//    private List<Fragment> mFragments;
//    private FragmentPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		rg_tabs=(RadioGroup) findViewById(R.id.tabs_rg);
		tab_listen=(RadioButton) findViewById(R.id.listen_tab);
		tab_mail=(RadioButton) findViewById(R.id.mail_tab);
		tab_me=(RadioButton) findViewById(R.id.me_tab);
		tab_tree=(RadioButton) findViewById(R.id.treehole_tab);
		
		listenFragment=new ListenFragment();
		
		getFragmentManager().beginTransaction().add(R.id.vpager,listenFragment).commit();	
		Setclicklistener();
		
	}
		
		public void Setclicklistener() {
			OnClick onClick=new OnClick();
			tab_listen.setOnClickListener(onClick);
			tab_mail.setOnClickListener(onClick);
			tab_me.setOnClickListener(onClick);
			tab_tree.setOnClickListener(onClick);
			
			
		}
		
		
		
		
		
//		
////		showimage=(Gallery) findViewById(R.id.G);
////		MusicAdapter musicAdapter=new MusicAdapter(this);
////		lvtreeicon=(ImageView) findViewById(R.id.tree_icon);
//		iv=(ImageView) findViewById(R.id.mainmusic);
//		iv.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(getApplicationContext(),MusicMainActivity.class);
//				startActivity(intent);
//			}
//		});
//		lvtreeicon.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(getApplicationContext(),TreeHoleShowActivity.class);
//				startActivity(intent);
//			}
//		});
		
	
	
	public class OnClick implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.listen_tab:
			
			
			break;
        case R.id.mail_tab:
			
			break;
        case R.id.me_tab:
			
			break;
        case R.id.treehole_tab:
			
			break;
			

		default:
			break;
		}
		
	}
		
	}
	
	
}*/
	
	



	
	    
	


