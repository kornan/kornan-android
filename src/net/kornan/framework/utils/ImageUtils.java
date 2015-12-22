package net.kornan.framework.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;
import android.util.Base64;

public class ImageUtils {

	// 压缩图片
	public static File[] compressImage(String path, File... files) {
		Bitmap bitmap = null;
		if (path == null)
			return files;
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		for (int i = 0; i < files.length; i++) {
			try {
				bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath());
				String file_name = files[i].getName();
				String sDStateString = android.os.Environment
						.getExternalStorageState();
				if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {
					File SDFile = android.os.Environment
							.getExternalStorageDirectory();
					File myFile = new File(SDFile.getAbsolutePath()
							+ File.separator + file_name);

					if (!myFile.exists()) {
						myFile.createNewFile();
					}
					writePath(
							bitmap = ThumbnailUtils.extractThumbnail(bitmap,
									480, 800), myFile.getAbsolutePath());
					files[i] = myFile;
				}
				// writePath(ThumbnailUtils.extractThumbnail(bitmap, 480,
				// 800),);
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Error err) {
				err.printStackTrace();
			}
		}
		return files;
	}

	// 保存图片
	public static void writePath(Bitmap bitmap, String path) {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(path));
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
			bos.flush();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
