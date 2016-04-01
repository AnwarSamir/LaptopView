package com.asi.laptopview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddPro extends AppCompatActivity {

    EditText inputname,inputPrice,inputhd,inputram;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pro);
        inputname= (EditText) findViewById(R.id.nameed);
        inputPrice= (EditText) findViewById(R.id.priceed);
        inputhd= (EditText) findViewById(R.id.hded);
        inputram= (EditText) findViewById(R.id.ramed);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);



    }

    public void postLaptop(View view) {
        if(   inputname.getText().toString().isEmpty()&&inputPrice.getText().toString().isEmpty()&&
                inputhd.getText().toString().isEmpty()&&inputram.getText().toString().isEmpty())
        {
            Toast.makeText(AddPro.this,"You Should Enter data",Toast.LENGTH_LONG).show();
        }else
        {



            progressDialog.setMessage("sending "+ inputname.getText().toString()+" data to server");
            progressDialog.show();
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    "http://proapi.890m.com/App/senddata.php", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "new product added", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting params to register url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", inputname.getText().toString());
                    params.put("price", inputPrice.getText().toString());
                    params.put("hd", inputhd.getText().toString());
                    params.put("ram", inputram.getText().toString());
                    return params;
                }

            };
            // Adding request to request queue
            Controller.getInstance().addToRequestQueue(strReq,"re");

        }
    }
}
