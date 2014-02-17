package com.duguang.baseanimation.app;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import cn.jpush.android.api.JPushInterface;
import android.app.Application;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

public class MainApplication extends Application {

	private static final String TAG = "MainApplication";

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
		setImageLoader();
		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志(极光推送Debug日志)
		JPushInterface.init(this); // 初始化 JPush(极光推送)
		
	}

	/**
	 * 配置ImageLoader框架
	 */
	public void setImageLoader() {
		File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
		// 可使用默认的配置, 如下:
		// ImageLoaderConfiguration
		// config=ImageLoaderConfiguration.createDefault(this);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.memoryCacheExtraOptions(480, 800)
				// default = device screen dimensions
				.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
				.threadPoolSize(3)
				// default
				.threadPriority(Thread.NORM_PRIORITY - 1)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
				// default
				.discCache(new UnlimitedDiscCache(cacheDir))
				// default
				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(100)
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(this)) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())// default
				.writeDebugLogs().build();

		ImageLoader.getInstance().init(config);
	}
}
