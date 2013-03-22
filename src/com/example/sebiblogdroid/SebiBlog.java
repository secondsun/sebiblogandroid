package com.example.sebiblogdroid;

import java.util.List;

import org.jboss.aerogear.android.datamanager.Store;
import org.jboss.aerogear.android.pipeline.AbstractActivityCallback;
import org.jboss.aerogear.android.pipeline.Pipe;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SebiBlog extends ListActivity {

	Pipe<Post> posts;
	List<Post> data;
	Store<Post> store;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		store = ((SebiBlogDroidApplication)getApplication()).manager.get("post");
		posts = ((SebiBlogDroidApplication)getApplication()).pipeline.get("post", this);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Post post = data.get(position);
		Intent i = new Intent(this, PostActivity.class);
		i.putExtra("id", post.id);
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sebi_blog, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		posts.read(new PostCallback());
	}
	
	public static class PostCallback extends AbstractActivityCallback<List<Post>> {

		@Override
		public void onSuccess(List<Post> data) {
			SebiBlog activity = (SebiBlog)getActivity();
			
			for (Post item : data) {
				activity.store.save(item);
			}
			
			activity.data = data;
			
			(activity).setListAdapter(new ArrayAdapter<Post>(getActivity(),
					android.R.layout.simple_list_item_activated_1,
					android.R.id.text1, data));
			
		}

		@Override
		public void onFailure(Exception e) {
			Activity ctx = getActivity();
			Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG);
		}
		
	}
	
}
