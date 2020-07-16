package com.tugas.akhirtugas.model.berita;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBerita implements Parcelable {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private boolean response;

	@SerializedName("berita")
	private List<BeritaItem> berita;

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

	public void setBerita(List<BeritaItem> berita){
		this.berita = berita;
	}

	public List<BeritaItem> getBerita(){
		return berita;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.pesan);
		dest.writeByte(this.response ? (byte) 1 : (byte) 0);
		dest.writeTypedList(this.berita);
	}

	public ResponseBerita() {
	}

	protected ResponseBerita(Parcel in) {
		this.pesan = in.readString();
		this.response = in.readByte() != 0;
		this.berita = in.createTypedArrayList(BeritaItem.CREATOR);
	}

	public static final Parcelable.Creator<ResponseBerita> CREATOR = new Parcelable.Creator<ResponseBerita>() {
		@Override
		public ResponseBerita createFromParcel(Parcel source) {
			return new ResponseBerita(source);
		}

		@Override
		public ResponseBerita[] newArray(int size) {
			return new ResponseBerita[size];
		}
	};
}