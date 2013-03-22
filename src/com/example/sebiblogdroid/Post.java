package com.example.sebiblogdroid;

import org.jboss.aerogear.android.RecordId;

public class Post {
	@RecordId
	Long id;
	int version;
	String title;
	String content;
	String postDate;
	
	@Override
	public String toString() {
		return title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
