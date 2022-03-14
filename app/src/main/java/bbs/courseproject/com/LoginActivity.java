package bbs.courseproject.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import bbs.courseproject.com.model.UserInfoModel;
import bbs.courseproject.com.network.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText phone_et, password_et;
    CheckBox isRemembered;
    Button login_bt, registration_bt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initVariables();

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserAuthorised()){
                    if (isRemembered.isChecked()) {
                        editor.putBoolean("isRemembered", true);
                        editor.apply();
                    }
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finishAffinity();
                }
            }
        });

        registration_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private boolean isUserAuthorised() {

        UserInfoModel userInfoModel = new UserInfoModel(
          phone_et.getText().toString().trim(),
          password_et.getText().toString().trim()
        );

        RetrofitClient.getRetrofit().checkUserInfo(userInfoModel).enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) { }
        });

        /*RetrofitClient.getRetrofit().checkUserInfo(userInfoModel).enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                UserInfoModel res = response.body();

                Toast.makeText(LoginActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {

            }
        });*/

        return true;
    }

    private void initVariables() {
        phone_et = findViewById(R.id.phone_et);
        password_et = findViewById(R.id.password_et);
        isRemembered = findViewById(R.id.is_remembered);
        login_bt = findViewById(R.id.login);
        registration_bt = findViewById(R.id.registration);

        sharedPreferences = getApplicationContext().getSharedPreferences("bbs", getApplicationContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //App permission checker for location service. if not then it will request for location.
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "App permission Denied", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
        }
    }
}