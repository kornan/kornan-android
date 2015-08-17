package net.kornan.framework.activity;

import net.kornan.framework.utils.Status;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {
	protected Toolbar toolbar;
	/**
	 * 处理网络异步数据
	 */
	protected Handler handler = new Handler() {

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
	 * toolbar
	 */
	public void initToolbar(String title) {
		int id = getResources()
				.getIdentifier("toolbar", "id", getPackageName());
		toolbar = (Toolbar) findViewById(id);
		toolbar.setTitle(title);// 标题的文字需在setSupportActionBar之前，不然会无效
		// toolbar.setLayoutMode(layoutMode);
		setSupportActionBar(toolbar);
		/* 这些通过ActionBar来设置也是一样的，注意要在setSupportActionBar(toolbar);之后，不然就报错了 */
		// getSupportActionBar().setTitle("标题");
		// getSupportActionBar().setSubtitle("副标题");
		// getSupportActionBar().setLogo(R.drawable.ic_launcher);
		/*
		 * 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，
		 * 通过Activity的onOptionsItemSelected回调方法来处理
		 */
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(getBaseContext(), "" + item.getItemId(),
						Toast.LENGTH_LONG).show();
				return true;
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// private boolean initCustomActionBar() {
	// ActionBar mActionbar = getSupportActionBar();
	// if (mActionbar == null) {
	// return false;
	// }
	// mActionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
	// mActionbar.setDisplayShowCustomEnabled(true);
	// mActionbar.setSubtitle("");
	// mActionbar.setCustomView(R.layout.top_back_center_bar);
	// tvTitle = (TextView) mActionbar.getCustomView().findViewById(
	// R.id.tv_tbb_title);
	// tvTitle.setText(originalTitle);
	// mActionbar.getCustomView().findViewById(R.id.iv_tbb_back)
	// .setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// finish();
	// }
	// });
	// return true;
	// }

	/**
	 * 所有接收到的数据在此处理
	 */
	public abstract void setData(Object obj);
}
