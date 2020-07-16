package com.tugas.akhirtugas.model.longsor;

import com.google.gson.annotations.SerializedName;

public class DataLongsorItem{

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("korban")
	private String korban;

	@SerializedName("id_bencana")
	private String idBencana;

	@SerializedName("jenis_bencana")
	private String jenisBencana;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("longitude")
	private String longitude;

	public void setLokasi(String lokasi){
		this.lokasi = lokasi;
	}

	public String getLokasi(){
		return lokasi;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
	}

	public void setKorban(String korban){
		this.korban = korban;
	}

	public String getKorban(){
		return korban;
	}

	public void setIdBencana(String idBencana){
		this.idBencana = idBencana;
	}

	public String getIdBencana(){
		return idBencana;
	}

	public void setJenisBencana(String jenisBencana){
		this.jenisBencana = jenisBencana;
	}

	public String getJenisBencana(){
		return jenisBencana;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}
}