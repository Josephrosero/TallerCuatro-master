package com.example.i043114.tallercuatro.Views;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.i043114.tallercuatro.Adapters.AdapterComment;
import com.example.i043114.tallercuatro.Connection.HttpManager;
import com.example.i043114.tallercuatro.Models.ModelComment;
import com.example.i043114.tallercuatro.Parsers.JsonComment;
import com.example.i043114.tallercuatro.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 15/10/2017.
 */

public class ActivityComent extends AppCompatActivity {


    ProgressBar progressBarPhoto;
    RecyclerView recyclerViewPhoto;
    List<ModelComment> commentListt;
    AdapterComment adapterComment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        toolbar = (Toolbar) findViewById(R.id.id_tb_toolbar);


        showToolbar(getResources().getString(R.string.List_Coments), true);


        progressBarPhoto = (ProgressBar) findViewById(R.id.id_PantallaPosts);
        recyclerViewPhoto = (RecyclerView) findViewById(R.id.id_PantallaPosts1);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPhoto.setLayoutManager(linearLayoutManager);

        Bundle a = getIntent().getExtras();
        loadData(Integer.toString(getIntent().getExtras().getInt("iduser2")));
    }


    public void Showpantallatres() {
        Intent a = new Intent(getApplicationContext(), ActivityPost.class);
        startActivity(a);
    }


    public void showToolbar(String title, boolean upButton) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    //  public void Actualizar_post(){
    //    loadData(Integer.toString(getIntent().getExtras().getInt("idPost")));
    // }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:
                finish();
                break;

            case (R.id.id_menu_p3):

                loadData(Integer.toString(getIntent().getExtras().getInt("iduser2")));

                break;

            default:

                return super.onOptionsItemSelected(item);

        }

        //Showpantallatres();
        return super.onOptionsItemSelected(item);
    }


    public Boolean isOnLine() {
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    public void loadData(String idPost) {
        if (isOnLine()) {
            TaskPhoto taskPhoto = new TaskPhoto();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/comments?postId=" + idPost);
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData() {
        adapterComment = new AdapterComment(commentListt, getApplicationContext());
        recyclerViewPhoto.setAdapter(adapterComment);
    }

    public class TaskPhoto extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarPhoto.setVisibility(View.VISIBLE);
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
                commentListt = JsonComment.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarPhoto.setVisibility(View.GONE);
        }
    }
}
