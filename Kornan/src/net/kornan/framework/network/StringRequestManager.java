package net.kornan.framework.network;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.kornan.framework.model.BaseModel;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

public class StringRequestManager extends StringRequest {
	private Map<String, String> map;

	public StringRequestManager(String url, BaseModel<String> model) {
		super(url, model, model);
	}

	public StringRequestManager(int method, String url, BaseModel<String> model) {
		super(method, url, model, model);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return map;
	}

	public void setParams(Map<String, String> map) {
		this.map = map;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse arg0) {
		// TODO Auto-generated method stub

		try {
			String str = new String(arg0.data, "UTF-8");
			return Response.success(str,
					HttpHeaderParser.parseCacheHeaders(arg0));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (Exception je) {
			return Response.error(new ParseError(je));
		}
		// return super.parseNetworkResponse(arg0);
	}

}
