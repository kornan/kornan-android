package net.kornan.framework.network;

import net.kornan.framework.App;
import net.kornan.framework.utils.LruImageCache;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

public class WebImageLoader extends ImageLoader {
	private static WebImageLoader imageLoader;
	private int defaultImageResId;
	private int errorImageResId;

	public static WebImageLoader getImageLoader() {
		if (imageLoader == null) {
			imageLoader = new WebImageLoader(App.getInstance()
					.getRequestQueue(), LruImageCache.instance());
		}
		return imageLoader;
	}

	private WebImageLoader(RequestQueue queue, ImageCache imageCache) {
		super(queue, imageCache);
		defaultImageResId = App
				.getInstance()
				.getResources()
				.getIdentifier("default_image", "drawable",
						App.getInstance().getPackageName());
		errorImageResId = App
				.getInstance()
				.getResources()
				.getIdentifier("default_image", "drawable",
						App.getInstance().getPackageName());
		// TODO Auto-generated constructor stub
	}

	public ImageContainer displayImage(ImageView imageView, String url) {

		// return imageLoader.get(url, ImageLoader.getImageListener(imageView,
		// R.drawable.default_image, R.drawable.default_image));
		return imageLoader.get(url, ImageLoader.getImageListener(imageView,
				defaultImageResId, errorImageResId));
		// 指定图片允许的最大宽度和高度
		// imageLoader.get("http://developer.android.com/images/home/aw_dac.png",listener,
		// 200, 200);
	}

	/**
	 * 指定图片允许的最大宽度和高度
	 * 
	 * @param imageView
	 * @param url
	 * @param width
	 * @param height
	 * @return
	 */
	public ImageContainer displayImage(ImageView imageView, String url,
			int width, int height) {

		return imageLoader.get(url, ImageLoader.getImageListener(imageView,
				defaultImageResId, errorImageResId), width, height);

		// return
		// imageLoader.get("http://developer.android.com/images/home/aw_dac.png",ImageLoader.getImageListener(imageView,
		// R.drawable.default_image, R.drawable.default_image),width,height);
	}

	public ImageContainer displayImage(ImageView imageView, String url,
			int width, int height, ScaleType scaleType) {
		return imageLoader.get(url, ImageLoader.getImageListener(imageView,
				defaultImageResId, errorImageResId), width, height, scaleType);
	}

}
