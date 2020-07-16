package com.tugas.akhirtugas.Berita.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.berita.BeritaItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tugas.akhirtugas.network.RetroClient.BASE_URL_IMAGE;
import static com.tugas.akhirtugas.utils.Contans.DATA;

public class DetailBerita extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolBarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_content)
    WebView tvContent;
    @BindView(R.id.loading)
    ProgressBar loading;
    BeritaItem data;
    @BindView(R.id.img_poster)
    ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UI();

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void UI() {
        data = getIntent().getParcelableExtra(DATA);
        if (data.getFoto() != null) {
            Glide.with(DetailBerita.this)
                    .load(BASE_URL_IMAGE + data.getFoto())
                    .into(imgPoster);
        }
        getSupportActionBar().setTitle(data.getJudulBerita());
        getSupportActionBar().setSubtitle("Oleh : Badan Penanganan Bencana");
        tvContent.setWebViewClient(new myWebclient());
        tvContent.getSettings().setJavaScriptEnabled(true);
        tvContent.loadData(data.getDeskripsi(), "text/html; charset=utf-8", "UTF-8");
        //scrool
        tvContent.setVerticalScrollBarEnabled(true);
        tvContent.setHorizontalScrollBarEnabled(true);
    }

    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loading.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            //super.onReceivedError(view, request, error);
            String htmlData = "<html><body><div align=\"center\" > Berita Kosong! </div></body>";

            tvContent.loadUrl("about:blank");
            tvContent.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
            tvContent.invalidate();
        }
    }
}