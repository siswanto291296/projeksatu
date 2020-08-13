package com.tugas.akhirtugas.model.longsor;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataLongsorItem implements Parcelable {

	@SerializedName("penduduk")
	private String penduduk;

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("korban")
	private String korban;

	@SerializedName("kejadian")
	private String kejadian;

	@SerializedName("id_bencana")
	private String idBencana;

	@SerializedName("jenis_bencana")
	private String jenisBencana;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("longitude")
	private String longitude;

	@SerializedName("penanggulangan")
	private String penanggulangan;

	public void setPenduduk(String penduduk){
		this.penduduk = penduduk;
	}

	public String getPenduduk(){
		return penduduk;
	}

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

	public void setKejadian(String kejadian){
		this.kejadian = kejadian;
	}

	public String getKejadian(){
		return kejadian;
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

	public void setPenanggulangan(String penanggulangan){
		this.penanggulangan = penanggulangan;
	}

	public String getPenanggulangan(){
		return penanggulangan;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.penduduk);
		dest.writeString(this.lokasi);
		dest.writeString(this.latitude);
		dest.writeString(this.waktu);
		dest.writeString(this.korban);
		dest.writeString(this.kejadian);
		dest.writeString(this.idBencana);
		dest.writeString(this.jenisBencana);
		dest.writeString(this.tanggal);
		dest.writeString(this.longitude);
		dest.writeString(this.penanggulangan);
	}

	public DataLongsorItem() {
	}

	protected DataLongsorItem(Parcel in) {
		this.penduduk = in.readString();
		this.lokasi = in.readString();
		this.latitude = in.readString();
		this.waktu = in.readString();
		this.korban = in.readString();
		this.kejadian = in.readString();
		this.idBencana = in.readString();
		this.jenisBencana = in.readString();
		this.tanggal = in.readString();
		this.longitude = in.readString();
		this.penanggulangan = in.readString();
	}

	public static final Parcelable.Creator<DataLongsorItem> CREATOR = new Parcelable.Creator<DataLongsorItem>() {
		@Override
		public DataLongsorItem createFromParcel(Parcel source) {
			return new DataLongsorItem(source);
		}

		@Override
		public DataLongsorItem[] newArray(int size) {
			return new DataLongsorItem[size];
		}
	};
}