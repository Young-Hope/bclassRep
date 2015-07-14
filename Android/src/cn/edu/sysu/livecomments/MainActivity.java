package cn.edu.sysu.livecomments;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.edu.sysu.livecomments.R.color;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText ip = (EditText) findViewById(R.id.IpAdd);
		
		findViewById(R.id.BtnConnect).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String inputIP = ip.getText().toString();
//				try {  
//					Socket socket;
		            // ����socket����ָ���������˵�ַ�Ͷ˿ں�  
//		            socket = new Socket(inputIP, 10000); 
//		            Toast toast = Toast.makeText(getApplicationContext(), "���ӳɹ���", Toast.LENGTH_SHORT); 
//		        	toast.show();
		            Intent intent1 = new Intent(); 
		            intent1.setComponent(new ComponentName("cn.edu.sysu.livecomments","cn.edu.sysu.livecomments.InputIpAty"));
		            startActivity(intent1);
		  
//		        } catch (UnknownHostException e1) {
//		        	Toast toast = Toast.makeText(getApplicationContext(), "����ʧ�ܣ�����δ����", Toast.LENGTH_SHORT); 
//		        	toast.show();
//		            e1.printStackTrace();  
//		        } catch (IOException e1) { 
//		        	Toast toast = Toast.makeText(getApplicationContext(), "����ʧ�ܣ�����������", Toast.LENGTH_SHORT); 
//		        	toast.show();
//		            e1.printStackTrace();  
//		        } finally {  
//		            ip.setText("");
//		        }  
				
			}
		});
	}

}