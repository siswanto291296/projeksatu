package com.tugas.akhirtugas.model.login;

import com.google.gson.annotations.SerializedName;

public class UserItem{

	@SerializedName("id_admin")
	private String idAdmin;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setIdAdmin(String idAdmin){
		this.idAdmin = idAdmin;
	}

	public String getIdAdmin(){
		return idAdmin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}