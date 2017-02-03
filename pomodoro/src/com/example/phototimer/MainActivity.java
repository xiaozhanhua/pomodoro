package com.example.phototimer;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	
	//定义TextView对象
	private TextView tv;
	//定义Buttn对象
	private Button btn_start;
	//声明两个整型数值,分别代表分、秒
	private int MINUTE = 24;	
	private int SECOND = 60;
	
	//引入处理机制
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what==1){
				timeCount(); //跳转到timeCount()方法
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		indexView(); //跳转到indexView()
	}
	
	/**
	 *初始化方法 
	 */
	private void indexView(){
		//添加动画效果
		LinearLayout linearLayout =(LinearLayout) findViewById(R.id.LinearLayout1);
		Animation animation=AnimationUtils.loadAnimation(this,R.anim.weclome);
		linearLayout.startAnimation(animation);
		
		btn_start = (Button) findViewById(R.id.btn_start);  //匹配Button控件
		tv = (TextView) findViewById(R.id.tv); //匹配TextView控件
		tv.setText("25:00"); //设置TextView文本内容
		btn_start.setOnClickListener(this); //为btn_start按钮注册监听器
	}
	
	/**
	 * 计时器方法
	 */
	private void calcTimer(){
		//声明定时器
		Timer timer = new Timer(){};
		//声明定时任务
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what=1;
				myHandler.sendMessage(msg);
			}
		};
		//启动定时器
		timer.schedule(timerTask, 1000, 1000);
	}
	
	/**
	 * 时间倒计方法
	 */
	private void timeCount(){
		if(MINUTE>=0){
			SECOND--;
			String minute = String.valueOf(MINUTE);
			String second = String.valueOf(SECOND);
			
			if(SECOND>=0){
				if(SECOND<10){
					second = "0"+second;
				}
				if(MINUTE<10){
					minute = "0"+minute;
				}
				tv.setText(minute+":"+second);
			}else{
				SECOND = 60;
				MINUTE--;
			}
			
		}else{
			tv.setText("GAME OVER");
		}
	} //timeCount()

	@Override
	public void onClick(View v) { 
		if(v.getId()==R.id.btn_start){
			calcTimer(); //跳转到calcTimer()方法
		}
	}
	
}
