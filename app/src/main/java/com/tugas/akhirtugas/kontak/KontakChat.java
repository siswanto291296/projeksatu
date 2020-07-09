package com.tugas.akhirtugas.kontak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tugas.akhirtugas.R;

public class KontakChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak_chat);
    }

    public void map(View view) {
        Intent i = new Intent(KontakChat.this, MapsActivity.class);
        startActivity(i);
    }
}
