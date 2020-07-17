package com.tugas.akhirtugas.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    //key
    public static final String SP_KEY = "Logins";
    public static final String SP_SUDAH_LOGIN = "sudah_login";
    public static final String SP_SUDAH_LOGIN2 = "sudah_login2";

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    //buat sharefpref dari class lain
    public Session(Context context) {
        //dengan key sampahku
        sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        this.context = context;
        spEditor = sp.edit();
    }

    public void saveSessionString(String key, String value) {
        spEditor.putString(key, value);
        spEditor.commit();
    }

    public String getSessionString(String key) {
        return sp.getString(key, "");
    }

    public void saveSessionBoolean(String key, boolean value) {
        spEditor.putBoolean(key, value);
        spEditor.commit();
    }

    public boolean getSessionBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Boolean getSPSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public Boolean getSPSudahLogin2() {
        return sp.getBoolean(SP_SUDAH_LOGIN2, false);
    }

}