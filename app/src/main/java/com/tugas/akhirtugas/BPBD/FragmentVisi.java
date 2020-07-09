package com.tugas.akhirtugas.BPBD;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tugas.akhirtugas.R;

public class FragmentVisi extends Fragment {
    public FragmentVisi() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.visi_misi, container, false);

        return view ;
    }
}
