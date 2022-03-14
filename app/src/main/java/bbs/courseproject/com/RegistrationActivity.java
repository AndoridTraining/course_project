package bbs.courseproject.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    EditText name_et, password_et, phone_et, email_et, organization_et, position_et;
    Button reg_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initVariables();

        reg_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initVariables() {
        name_et = findViewById(R.id.name_et);
        password_et = findViewById(R.id.password_et);
        phone_et = findViewById(R.id.phone_et);
        email_et = findViewById(R.id.email_et);
        organization_et = findViewById(R.id.organization_et);
        position_et = findViewById(R.id.position_et);
        reg_bt = findViewById(R.id.reg_bt);
    }
}