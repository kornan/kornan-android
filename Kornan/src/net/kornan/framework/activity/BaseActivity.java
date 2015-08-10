package net.kornan.framework.activity;

import net.kornan.framework.utils.Status;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public abstract class BaseActivity extends Activity{
	/**
	 * 处理网络异步数据
	 */
	protected Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Status.SUCCESS:
				setData(msg.obj);
				break;
			}
			
		}
		
	};
	/**
	 * 所有接收到的数据在此处理
	 */
	public abstract void setData(Object obj);
}
