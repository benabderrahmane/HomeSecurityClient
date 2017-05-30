package com.hsc.mobile.homesecurityclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ParamModifyActivity extends AppCompatActivity {

    EditText url;
    EditText name;
    String checked = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_modify);

        url = (EditText) findViewById(R.id.camera_url);
        name = (EditText) findViewById(R.id.camera_name);
    }

    public void OnSave(View view) {
        String type = "AddCamera";
        BackGroundWorker bw = new BackGroundWorker(this);
        bw.execute(type, url.getText().toString(), name.getText().toString(), "1", this.checked);

        Intent intent = new Intent(getApplicationContext(), FirstUserActivity.class);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view) {
        boolean pub = ((CheckBox) view).isChecked();
        if (pub)
               this.checked = "1";
        else
            this.checked = "0";
    }
}
