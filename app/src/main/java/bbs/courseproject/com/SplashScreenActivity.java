package bbs.courseproject.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

    }

    private void moveToAnotherActivity() {
        if (preferences.getBoolean("isRemembered", false)) {
            startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
        } else {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        }
    }

    private void initData() {
        preferences = getApplicationContext().getSharedPreferences(
                "bbs",
                getApplicationContext().MODE_PRIVATE);
        editor = preferences.edit();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getUserLocation();

        moveToAnotherActivity();
    }

    private void getUserLocation() {

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "App permission Denied", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("permission", "App permission granted");

            fusedLocationClient.getLastLocation().addOnSuccessListener(
                    this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            editor.putFloat("userLat", (float) location.getLatitude());
                            editor.putFloat("userLon", (float) location.getLongitude());
                            editor.apply();
                        }
                    }
            );
        }
    }
}