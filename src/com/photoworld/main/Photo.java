package com.photoworld.main;

public class Photo {
	private String username;
	private String path;
	private int longitude;
	private int latitude;
	
	public String getUstername(){
		return this.username;
	}
	public void setUsterName(String name){
		this.username=name;
	}
	
	public String getPath(){
		return this.path;
	}
	public void setPath(String path){
		this.path=path;
	}
	
	public int getLatitude(){
		return this.latitude;
	}
	public void setLatitude(int latitude){
		this.latitude=latitude;
	}
	
	public int getLongitude(){
		return this.longitude;
	}
	public void setLongitude(int longitude){
		this.longitude=longitude;
	}
	

}
