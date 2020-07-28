package com.tugas.akhirtugas.model.kecamatan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKecamatan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private boolean response;

	@SerializedName("kecamatan")
	private List<KecamatanItem> kecamatan;

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

	public void setKecamatan(List<KecamatanItem> kecamatan){
		this.kecamatan = kecamatan;
	}

	public List<KecamatanItem> getKecamatan(){
		return kecamatan;
	}
}