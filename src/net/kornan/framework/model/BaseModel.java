package net.kornan.framework.model;

import net.kornan.framework.App;
import net.kornan.framework.network.VolleyErrorHelper;
import net.kornan.framework.utils.Status;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public abstract class BaseModel<T> implements Listener<T>,ErrorListener{
	protected Context context;
	protected Handler handler;
	protected RequestQueue rq;
	protected ProgressDialog pd;
	
	public BaseModel(Context context, Handler handler) {
		this.handler = handler;
		this.context = context;
		rq = App.getInstance().getRequestQueue();

	}
	/**
	 * 设置访问网络时的进度
	 * @param progressDialog
	 */
	public void setProgressDialog(ProgressDialog progressDialog){
		this.pd=progressDialog;
	}
	
	/**
	 * 添加网络请求
	 * @param req
	 */
	public void addRequest(Request<T> req){
		if(pd!=null&&!pd.isShowing()){
			pd.show();
		}
		req.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
		App.getInstance().addToRequestQueue(req);
	}
	
	/**
	 * 添加网络请求
	 * @param req
	 * @param tag
	 */
	public void addRequest(Request<T> req,String tag){
		if(pd!=null&&!pd.isShowing()){
			pd.show();
		}
		req.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
		App.getInstance().addToRequestQueue(req,tag);
	}
	
	/**
	 * 取消所有tag网络请求
	 * @param tag
	 */
	public void cancelRequest(String tag){
		if(pd!=null&&pd.isShowing()){
			pd.dismiss();
		}
		App.getInstance().cancelPendingRequests(tag);
	}
	
	/**
	 * 取消所有网络请求
	 */
	public void cancelRequest(){
		if(pd!=null&&pd.isShowing()){
			pd.dismiss();
		}
		App.getInstance().cancelPendingRequests();
	}
	
	/**
	 * 访问网络的异常处理
	 */
	@Override
	public void onErrorResponse(VolleyError volleyError) {
		// TODO Auto-generated method stub
//		volleyError.
		if(pd!=null&&pd.isShowing()){
			pd.dismiss();
		}
		Toast.makeText(context, VolleyErrorHelper.getMessage(volleyError, context), Toast.LENGTH_LONG).show();

	}

	@Override
	public void onResponse(T arg0) {
		// TODO Auto-generated method stub
		if(pd!=null&&pd.isShowing()){
			pd.dismiss();
		}
		
		callback(arg0);
		
	}
	/**
	 * 响应activity的setData
	 * @param obj
	 */
	public void responseMessage(Object obj){
		Message msg=new Message();
		msg.what=Status.SUCCESS;
		msg.obj=obj;
		handler.sendMessage(msg);
	}
	
	/**
	 * 网络访问后的回调方法
	 * @param result
	 */
	public abstract void callback(T result);
	
}
