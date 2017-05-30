package com.hsc.mobile.homesecurityclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText mail;
    private EditText password;
    private EditText confirm_password;
    private EditText phone;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password_register);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        phone = (EditText) findViewById(R.id.tel_number);
    }

    public void OnRegister(View view) {
        String type = "Register";
        if (!password.getText().toString().equals(confirm_password.getText().toString())) {
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Password error");
            alertDialog.setMessage("The two password doesn't match !");
            alertDialog.show();
        }
        else {
            BackGroundWorker bw = new BackGroundWorker(this);
            bw.execute(type, name.getText().toString(), mail.getText().toString(), password.getText().toString(), phone.getText().toString());
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(intent);
        }
    }
}

