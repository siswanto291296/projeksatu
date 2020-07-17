package com.tugas.akhirtugas.model.longsor;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLongsor implements Parcelable {

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.pesan);
		dest.writeByte(this.response ? (byte) 1 : (byte) 0);
		dest.writeTypedList(this.dataLongsor);
	}

	public ResponseLongsor() {
	}

	protected ResponseLongsor(Parcel in) {
		this.pesan = in.readString();
		this.response = in.readByte() != 0;
		this.dataLongsor = in.createTypedArrayList(DataLongsorItem.CREATOR);
	}

	public static final Parcelable.Creator<ResponseLongsor> CREATOR = new Parcelable.Creator<ResponseLongsor>() {
		@Override
		public ResponseLongsor createFromParcel(Parcel source) {
			return new ResponseLongsor(source);
		}

		@Override
		public ResponseLongsor[] newArray(int size) {
			return new ResponseLongsor[size];
		}
	};
}