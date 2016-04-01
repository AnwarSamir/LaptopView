package com.asi.laptopview;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private String jsonResponse;
    private String json_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addPro(View view) {

        startActivity(new Intent(MainActivity.this,AddPro.class));
    }

    public void getall(View view) {



        DoTask task=new DoTask();
        task.execute("http://proapi.890m.com/App/getJson.php");
        if (json_string==null)
        {
            Toast.makeText(getApplicationContext(),"Test Your Internet connection ",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent=new Intent(MainActivity.this,ViewLaptop.class);
            intent.putExtra("data",json_string);
            startActivity(intent);

        }


    }

    public class DoTask extends AsyncTask<String,Void,String>
    {



        @Override
        protected String doInBackground(String... params) {

            try {
                URL url1=new URL(params[0]);
                HttpURLConnection urlConnection= (HttpURLConnection) url1.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                //buffer reader
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while ((jsonResponse=bufferedReader.readLine())!=null)
                {

                    stringBuilder.append(jsonResponse+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                urlConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //  txtResponse.setText(s);
            json_string=s;
        }
    }
}
