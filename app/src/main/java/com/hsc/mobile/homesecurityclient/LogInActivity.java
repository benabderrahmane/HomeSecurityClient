package com.hsc.mobile.homesecurityclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private EditText information;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        information = (EditText) findViewById(R.id.mail_phone);
        password = (EditText) findViewById(R.id.password_connect);
    }

    public void OnLogin(View view) {
        String type = "Login";
        //Log.d("TAG", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + information.getText().toString());
        BackGroundWorker bw = new BackGroundWorker(this);
        bw.execute(type, information.getText().toString(), password.getText().toString());

        Intent intent = new Intent(getApplicationContext(), AddCameraActivity.class);
        startActivity(intent);
    }
}
