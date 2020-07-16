package com.tugas.akhirtugas.model.berita;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BeritaItem implements Parcelable {

	@SerializedName("foto")
	private String foto;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("id_berita")
	private String idBerita;

	@SerializedName("judul_berita")
	private String judulBerita;

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setIdBerita(String idBerita){
		this.idBerita = idBerita;
	}

	public String getIdBerita(){
		return idBerita;
	}

	public void setJudulBerita(String judulBerita){
		this.judulBerita = judulBerita;
	}

	public String getJudulBerita(){
		return judulBerita;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.foto);
		dest.writeString(this.deskripsi);
		dest.writeString(this.tanggal);
		dest.writeString(this.idBerita);
		dest.writeString(this.judulBerita);
	}

	public BeritaItem() {
	}

	protected BeritaItem(Parcel in) {
		this.foto = in.readString();
		this.deskripsi = in.readString();
		this.tanggal = in.readString();
		this.idBerita = in.readString();
		this.judulBerita = in.readString();
	}

	public static final Parcelable.Creator<BeritaItem> CREATOR = new Parcelable.Creator<BeritaItem>() {
		@Override
		public BeritaItem createFromParcel(Parcel source) {
			return new BeritaItem(source);
		}

		@Override
		public BeritaItem[] newArray(int size) {
			return new BeritaItem[size];
		}
	};
}