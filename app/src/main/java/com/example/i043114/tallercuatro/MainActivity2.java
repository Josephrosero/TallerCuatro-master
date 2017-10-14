package com.example.i043114.tallercuatro;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.i043114.tallercuatro.Adapters.AdapterPost;
import com.example.i043114.tallercuatro.Connection.HttpManager;
import com.example.i043114.tallercuatro.Models.ModelPost;
import com.example.i043114.tallercuatro.Parsers.JsonPost;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 12/10/2017.
 */

public class MainActivity2 extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<ModelPost> modelpostList;
    AdapterPost adapterPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_item3);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_item3);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        loadData(Integer.toString(getIntent().getExtras().getInt("userId")));
    }

    public Boolean isOnLine() {
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    public void loadData(String userId) {
        if (isOnLine()) {
            TaskCountry taskCountry = new TaskCountry();
            taskCountry.execute("https://jsonplaceholder.typicode.com/posts?userId="+userId);
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData() {
        //adapterCountry = new AdapterCountry(countryList, getApplicationContext());
        //recyclerView.setAdapter(adapterCountry);
        adapterPost = new AdapterPost(modelpostList, getApplicationContext());
        recyclerView.setAdapter(adapterPost);
    }

    public class TaskCountry extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                modelpostList = JsonPost.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);
        }
    }





}
