package com.tugas.akhirtugas.model.cuaca;

import com.google.gson.annotations.SerializedName;

public class Sys{

	@SerializedName("country")
	private String country;

	@SerializedName("sunrise")
	private long sunrise;

	@SerializedName("sunset")
	private long sunset;

	@SerializedName("message")
	private double message;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setSunrise(long sunrise){
		this.sunrise = sunrise;
	}

	public long getSunrise(){
		return sunrise;
	}

	public void setSunset(long sunset){
		this.sunset = sunset;
	}

	public long getSunset(){
		return sunset;
	}

	public void setMessage(double message){
		this.message = message;
	}

	public double getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Sys{" + 
			"country = '" + country + '\'' + 
			",sunrise = '" + sunrise + '\'' + 
			",sunset = '" + sunset + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}