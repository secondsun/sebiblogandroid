package com.example.sebiblogdroid;

import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.aerogear.android.DataManager;
import org.jboss.aerogear.android.Pipeline;
import org.jboss.aerogear.android.authentication.impl.Authenticator;
import org.jboss.aerogear.android.datamanager.Store;
import org.jboss.aerogear.android.impl.datamanager.StoreConfig;
import org.jboss.aerogear.android.impl.datamanager.StoreTypes;
import org.jboss.aerogear.android.impl.pipeline.PipeConfig;

import android.app.Application;

public class SebiBlogDroidApplication extends Application {

	Pipeline pipeline;
	Authenticator authenticator;
	DataManager manager;
	private static final URL baseURL;
	static {
		try {
			baseURL = new URL("https://sebiblog-sblanc.rhcloud.com");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		pipeline = new Pipeline(baseURL);
		
		PipeConfig config = new PipeConfig(baseURL, Post.class);
		config.setEndpoint("posts");
		
		pipeline.pipe(Post.class, config);
		
		manager = new DataManager();
		StoreConfig sqlStoreConfig = new StoreConfig();
		manager.store("post");
		
	}
}
