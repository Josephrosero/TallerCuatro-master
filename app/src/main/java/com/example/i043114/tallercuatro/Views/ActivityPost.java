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

import com.example.i043114.tallercuatro.Adapters.AdapterPost;
import com.example.i043114.tallercuatro.Connection.HttpManager;
import com.example.i043114.tallercuatro.MainActivity;
import com.example.i043114.tallercuatro.Models.ModelPost;
import com.example.i043114.tallercuatro.Parsers.JsonPost;
import com.example.i043114.tallercuatro.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 14/10/2017.
 */

public class ActivityPost extends AppCompatActivity {
    ProgressBar progressBarPhoto;
    RecyclerView recyclerViewPhoto;
    List<ModelPost> postList;
    AdapterPost adaptersPost;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = (Toolbar) findViewById(R.id.id_tb_toolbar);

        showToolbar(getResources().getString(R.string.List_Posts), true);


        progressBarPhoto = (ProgressBar) findViewById(R.id.id_PantallaPosts);
        recyclerViewPhoto = (RecyclerView) findViewById(R.id.id_PantallaPosts1);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPhoto.setLayoutManager(linearLayoutManager);


        Bundle a = getIntent().getExtras();
        loadData(Integer.toString(getIntent().getExtras().getInt("idUser")));
    }





    // toolbar
    public void showToolbar(String title, boolean upButton){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Actualizar_post();
        switch (item.getItemId()) {

            case R.id.home:
                finish();
                break;

            case (R.id.id_menu_p2):

                loadData(Integer.toString(getIntent().getExtras().getInt("idUser")));

                break;

            default:

                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }

    public void Showpantallatres(){
        Intent a = new Intent(getApplicationContext(), ActivityPost.class);
        startActivity(a);
    }


    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(String idUser){
        if (isOnLine()){
            TaskPhoto taskPhoto = new TaskPhoto();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/posts?userId="+idUser);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        adaptersPost = new AdapterPost(postList, getApplicationContext());
        recyclerViewPhoto.setAdapter(adaptersPost);
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
                postList = JsonPost.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarPhoto.setVisibility(View.GONE);
        }
    }
}
