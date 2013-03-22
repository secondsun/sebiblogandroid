package com.example.sebiblogdroid;

import org.jboss.aerogear.android.datamanager.Store;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PostActivity extends Activity {

	Store<Post> store;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		store = ((SebiBlogDroidApplication)getApplication()).manager.get("post");
		Post post = (Post) store.read(getIntent().getLongExtra("id", 1));
		setContentView(R.layout.post);
		TextView text = (TextView) findViewById(R.id.text);
		text.setText(post.content);
	};
	
	
}
