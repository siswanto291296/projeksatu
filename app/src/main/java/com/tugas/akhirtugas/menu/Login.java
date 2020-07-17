package com.tugas.akhirtugas.menu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.login.ResponseLogin;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;
import com.tugas.akhirtugas.session.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tugas.akhirtugas.utils.Logins.*;

public class Login extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.password)
    ShowHidePasswordEditText password;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Session sharedLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedLogin = new Session(Login.this);

        //cek apakah user sudah login
        if (sharedLogin.getSPSudahLogin()) {
            if (sharedLogin.getSPSudahLogin2()) {
                //cek login kedua
                startActivity(new Intent(Login.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
            //cache
            email.setText(sharedLogin.getSessionString(EMAIL));
            password.setText(sharedLogin.getSessionString(PASSWORD));
        }
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("email kosong");
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("password kosong");
        } else if (!TextUtils.isEmpty(password.getText().toString()) && !TextUtils.isEmpty(email.getText().toString())) {
            login(email.getText().toString(), password.getText().toString());
        }
    }

    private void login(String email, String password) {
        loading.setVisibility(View.VISIBLE);
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseLogin> loginCall = apiService.login(email, password);
        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ResponseLogin login = response.body();
                try {
                    if (login.isResponse()) {
                        //simpan cache
                        sharedLogin.saveSessionString(ID, login.getUser().get(0).getIdAdmin());
                        sharedLogin.saveSessionString(NAMA, login.getUser().get(0).getUsername());
                        sharedLogin.saveSessionString(EMAIL, login.getUser().get(0).getEmail());
                        sharedLogin.saveSessionString(PASSWORD, password.toString());
                        //flag session and login
                        sharedLogin.saveSessionBoolean(Session.SP_SUDAH_LOGIN, true);
                        sharedLogin.saveSessionBoolean(Session.SP_SUDAH_LOGIN2, true);
                        loading.setVisibility(View.GONE);

                        //buka home jika berhasil login
                        startActivity(new Intent(Login.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        //hancurkan activity
                        finish();
                    } else {
                        loading.setVisibility(View.GONE);
                        Snackbar.make(loading, "Akun Tidak ditemukan!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } catch (Exception e) {
                    loading.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "error = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Toast.makeText(Login.this, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
