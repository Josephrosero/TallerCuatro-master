package com.example.i043114.tallercuatro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.i043114.tallercuatro.MainActivity;
import com.example.i043114.tallercuatro.Models.ModelComment;
import com.example.i043114.tallercuatro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 12/10/2017.
 */

     public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder>{
 List<ModelComment> UserList = new ArrayList<>();
    Context context;



    public  AdapterComment(List<ModelComment>UserList,Context context){
        this.UserList = UserList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {

        // Encargado de trabajar con el item.xml y sus componentes

      //  holder.textVPostid.setText(Integer.toString(UserList.get(position).getPostid()));
        holder.textVId.setText(Integer.toString(UserList.get(position).getId()));
        holder.textVEmail.setText(UserList.get(position).getEmail());
        holder.textVBody.setText(UserList.get(position).getBody());
        //holder.textVCompany.setText(UserList.get(position).getCompany1());
        //  holder.textVadress.setText(UserList.get(position).getAdress());
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView textVId;
        TextView textVEmail;
        TextView textVBody;


        public ViewHolder(View item) {
            super(item);

              item.setOnClickListener(this);

           // textVPostid = (TextView) item.findViewById(R.id.id_post_comment);
            textVId = (TextView) item.findViewById(R.id.id_comment);
            textVEmail =(TextView)item.findViewById(R.id.emai_post_comment);
            textVBody =(TextView ) item.findViewById(R.id.body_post);
        }

           @Override
        public void onClick(View view) {
        Context contextItem = view.getContext();
          Intent intent = new Intent(context, MainActivity.class);
            contextItem.startActivity(intent);
          }
    }

}
