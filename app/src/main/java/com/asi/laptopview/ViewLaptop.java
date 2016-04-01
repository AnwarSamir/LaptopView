package com.asi.laptopview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import Adapter.RecyclerViewForListOFApis;
import Models.ApiModel;

public class ViewLaptop extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ApiModel> laptopList = new ArrayList<ApiModel>();
    String json_data;
    JSONArray jsonArray;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_laptop);
        //***************************************
        json_data = getIntent().getExtras().getString("data");
        try {
            jsonObject = new JSONObject(json_data);
            jsonArray = jsonObject.getJSONArray("products");
            int count = 0;

            String name, price, hd, ram;

            while (count < jsonArray.length()) {

                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                price = jo.getString("price");
                hd = jo.getString("hd");
                ram = jo.getString("ram");

                laptopList.add(new ApiModel(name, price, hd, ram));
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewForListOFApis(ViewLaptop.this, laptopList);
        mRecyclerView.setAdapter(mAdapter);


    }
}
