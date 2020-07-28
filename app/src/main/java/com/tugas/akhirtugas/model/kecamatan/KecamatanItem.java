package com.tugas.akhirtugas.model.kecamatan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class KecamatanItem implements Parcelable {

	@SerializedName("nama_kec")
	private String namaKec;

	@SerializedName("id_kec")
	private String idKec;

	public void setNamaKec(String namaKec){
		this.namaKec = namaKec;
	}

	public String getNamaKec(){
		return namaKec;
	}

	public void setIdKec(String idKec){
		this.idKec = idKec;
	}

	public String getIdKec(){
		return idKec;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.namaKec);
		dest.writeString(this.idKec);
	}

	public KecamatanItem() {
	}

	protected KecamatanItem(Parcel in) {
		this.namaKec = in.readString();
		this.idKec = in.readString();
	}

	public static final Parcelable.Creator<KecamatanItem> CREATOR = new Parcelable.Creator<KecamatanItem>() {
		@Override
		public KecamatanItem createFromParcel(Parcel source) {
			return new KecamatanItem(source);
		}

		@Override
		public KecamatanItem[] newArray(int size) {
			return new KecamatanItem[size];
		}
	};
}