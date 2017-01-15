package com.photoworld.main;

public class Photo {
	private int id;
	private String username;
	private String path;
	private float longitude;
	private float latitude;
	
	public String getUstername(){
		return this.username;
	}
	public void setUsterName(String name){
		this.username=name;
	}
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	
	
	public String getPath(){
		return this.path;
	}
	public void setPath(String path){
		this.path=path;
	}
	
	public float getLatitude(){
		return this.latitude;
	}
	public void setLatitude(float latitude){
		this.latitude=latitude;
	}
	
	public float getLongitude(){
		return this.longitude;
	}
	public void setLongitude(float longitude){
		this.longitude=longitude;
	}
	

}
