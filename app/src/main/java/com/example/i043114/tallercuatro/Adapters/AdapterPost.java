package com.example.i043114.tallercuatro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.i043114.tallercuatro.MainActivity;
import com.example.i043114.tallercuatro.Models.ModelPost;
import com.example.i043114.tallercuatro.R;
import com.example.i043114.tallercuatro.Views.ActivityComent;
import com.example.i043114.tallercuatro.Views.ActivityPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 12/10/2017.
 */

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {
    List<ModelPost> UserList = new ArrayList<>();
    Context context;


    public AdapterPost(List<ModelPost> ModelPost, Context context) {
        this.UserList = ModelPost;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {

        // Encargado de trabajar con el item.xml y sus componentes


        holder.textVId.setText(Integer.toString(UserList.get(position).getId()));
        holder.textVTitle.setText(UserList.get(position).getTitle());
        holder.textVBody.setText(UserList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textVId;
        TextView textVTitle;
        TextView textVBody;



        public ViewHolder(View item) {
            super(item);

        item.setOnClickListener(this);

            textVId = (TextView) item.findViewById(R.id.id_post);
            textVTitle = (TextView) item.findViewById(R.id.title_post);
            textVBody = (TextView) item.findViewById(R.id.body_post);

        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, ActivityComent.class);
            intent.putExtra("iduser2",UserList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);
        }
    }
}