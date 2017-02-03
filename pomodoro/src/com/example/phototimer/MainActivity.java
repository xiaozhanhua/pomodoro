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
	
	//����TextView����
	private TextView tv;
	//����Buttn����
	private Button btn_start;
	//��������������ֵ,�ֱ����֡���
	private int MINUTE = 24;	
	private int SECOND = 60;
	
	//���봦�����
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what==1){
				timeCount(); //��ת��timeCount()����
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		indexView(); //��ת��indexView()
	}
	
	/**
	 *��ʼ������ 
	 */
	private void indexView(){
		//��Ӷ���Ч��
		LinearLayout linearLayout =(LinearLayout) findViewById(R.id.LinearLayout1);
		Animation animation=AnimationUtils.loadAnimation(this,R.anim.weclome);
		linearLayout.startAnimation(animation);
		
		btn_start = (Button) findViewById(R.id.btn_start);  //ƥ��Button�ؼ�
		tv = (TextView) findViewById(R.id.tv); //ƥ��TextView�ؼ�
		tv.setText("25:00"); //����TextView�ı�����
		btn_start.setOnClickListener(this); //Ϊbtn_start��ťע�������
	}
	
	/**
	 * ��ʱ������
	 */
	private void calcTimer(){
		//������ʱ��
		Timer timer = new Timer(){};
		//������ʱ����
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what=1;
				myHandler.sendMessage(msg);
			}
		};
		//������ʱ��
		timer.schedule(timerTask, 1000, 1000);
	}
	
	/**
	 * ʱ�䵹�Ʒ���
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
			calcTimer(); //��ת��calcTimer()����
		}
	}
	
}
