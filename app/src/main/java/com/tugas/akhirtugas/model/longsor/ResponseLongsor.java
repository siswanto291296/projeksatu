package com.tugas.akhirtugas.model.longsor;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLongsor{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private boolean response;

	@SerializedName("data_longsor")
	private List<DataLongsorItem> dataLongsor;

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

	public void setDataLongsor(List<DataLongsorItem> dataLongsor){
		this.dataLongsor = dataLongsor;
	}

	public List<DataLongsorItem> getDataLongsor(){
		return dataLongsor;
	}
}