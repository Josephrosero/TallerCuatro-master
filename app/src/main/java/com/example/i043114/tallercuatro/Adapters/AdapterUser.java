package com.example.i043114.tallercuatro.Adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.i043114.tallercuatro.MainActivity;
import com.example.i043114.tallercuatro.Models.ModelUser;
import com.example.i043114.tallercuatro.R;
import com.example.i043114.tallercuatro.Views.ActivityPost;

import com.squareup.picasso.Picasso;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by aula7 on 12/10/17.
 */

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {

    List<ModelUser> usersList = new ArrayList<>();
    Context context;

    public AdapterUser(List<ModelUser> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Encargado de trabajar con el item.xml y sus componentes

        holder.id.setText(Integer.toString(usersList.get(position).getId()));
        holder.name.setText(usersList.get(position).getName());
        holder.username.setText(usersList.get(position).getUsername());
        holder.address.setText(usersList.get(position).getAdress());
        holder.company.setText(usersList.get(position).getCompany1());



    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id;
        TextView name;
        TextView username;
        TextView address;
        TextView company;



        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            id = (TextView) item.findViewById(R.id.id_item_user);
            name = (TextView) item.findViewById(R.id.name_user);
            username = (TextView) item.findViewById(R.id.username_user);
            address = (TextView) item.findViewById(R.id.city_user);
            company = (TextView) item.findViewById(R.id.name_company_user);

        }

        @Override
        public void onClick(View view) {

            Context contextItem = view.getContext();
            Intent intent = new Intent(context, ActivityPost.class);
            intent.putExtra("idUser", usersList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);
        }
    }
}

