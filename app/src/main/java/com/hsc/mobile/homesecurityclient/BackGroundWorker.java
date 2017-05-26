package com.hsc.mobile.homesecurityclient;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.ConsoleMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Yassine on 25/05/2017.
 */

public class BackGroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    String finaleResult = "";

    public BackGroundWorker(Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if (type.equals("Login")) {
            String login_url = "http://192.168.56.1/android_app/login.php";
            try {
                String information = params[1];
                String password = params[2];
                URL url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_url = URLEncoder.encode("information", "UTF-8") + "=" + URLEncoder.encode(information, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data_url);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("Register")) {
            try {
                String login_url = "http://192.168.56.1/android_app/insert.php";
                String name_register = params[1];
                String mail_register = params[2];
                String password_register = params[3];
                String phone_register = params[4];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_url = URLEncoder.encode("name_register", "UTF-8") + "=" + URLEncoder.encode(name_register, "UTF-8") + "&" +
                        URLEncoder.encode("mail_register", "UTF-8") + "=" + URLEncoder.encode(mail_register, "UTF-8")  + "&" +
                        URLEncoder.encode("password_register", "UTF-8") + "=" + URLEncoder.encode(password_register, "UTF-8") + "&" +
                        URLEncoder.encode("phone_register", "UTF-8") + "=" + URLEncoder.encode(phone_register, "UTF-8");
                bufferedWriter.write(data_url);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Operation Status");
    }

    @Override
    protected void onPostExecute(String result) {
        finaleResult = result;
        if (result.equals("loginOn"))
            alertDialog.setMessage("Connection success");
        else if (result.equals("loginOff"))
            alertDialog.setMessage("Connection error !");
        else if (result.equals("registerOn"))
            alertDialog.setMessage("Register success !");
        else if (result.equals("registerOff"))
            alertDialog.setMessage("Register error !");
        else
            alertDialog.setMessage("Errors occurred while saving data" + result);
        alertDialog.show();
        SystemClock.sleep(3000);
    }

    public String returnedResult() {
        return finaleResult;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
