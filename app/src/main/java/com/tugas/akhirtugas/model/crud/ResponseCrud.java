package com.tugas.akhirtugas.model.crud;

import com.google.gson.annotations.SerializedName;

public class ResponseCrud{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private boolean response;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setResponse(boolean response){
		this.response = response;
	}

	public boolean isResponse(){
		return response;
	}
}