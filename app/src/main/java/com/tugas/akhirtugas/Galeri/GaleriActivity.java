package com.tugas.akhirtugas.Galeri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.tugas.akhirtugas.Adapter.RecyclerViewAdapter;
import com.tugas.akhirtugas.R;

import java.util.ArrayList;
import java.util.List;

public class GaleriActivity extends AppCompatActivity {

    List<Galeri> lstBook ;

    Toolbar toolbar;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri);

        lstBook = new ArrayList<>();
        lstBook.add(new Galeri("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        lstBook.add(new Galeri("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        lstBook.add(new Galeri("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        lstBook.add(new Galeri("The Martian","Categorie Book","Description book",R.drawable.themartian));
        lstBook.add(new Galeri("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));
        lstBook.add(new Galeri("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        lstBook.add(new Galeri("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        lstBook.add(new Galeri("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        lstBook.add(new Galeri("The Martian","Categorie Book","Description book",R.drawable.themartian));
        lstBook.add(new Galeri("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));
        lstBook.add(new Galeri("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        lstBook.add(new Galeri("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        lstBook.add(new Galeri("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        lstBook.add(new Galeri("The Martian","Categorie Book","Description book",R.drawable.themartian));
        lstBook.add(new Galeri("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
