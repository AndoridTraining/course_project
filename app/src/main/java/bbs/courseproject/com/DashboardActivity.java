package bbs.courseproject.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initVariables();
    }

    private void initVariables() {

        sharedPreferences = getApplicationContext().getSharedPreferences("bbs", getApplicationContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onBackPressed() {
        //Stop user to go back from the current page
        //super.onBackPressed();
        showDialogToLogout();
    }

    // This is our dialog for user to choose where to log out from the system or not.
    private void showDialogToLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to logout from the system")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DashboardActivity.this, "Logging Out", Toast.LENGTH_SHORT).show();
                        editor.putBoolean("isRemembered", false);
                        editor.apply();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DashboardActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}