package com.tugas.akhirtugas.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class MyFucntion {
    public static View view(@NonNull ViewGroup parent, int layout){
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }
}
