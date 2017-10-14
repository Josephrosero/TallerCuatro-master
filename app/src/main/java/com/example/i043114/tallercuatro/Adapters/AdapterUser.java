package com.example.i043114.tallercuatro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.i043114.tallercuatro.MainActivity;
import com.example.i043114.tallercuatro.MainActivity2;
import com.example.i043114.tallercuatro.Models.ModelUser;
import com.example.i043114.tallercuatro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aula7 on 12/10/17.
 */

public class AdapterUser extends  RecyclerView.Adapter<AdapterUser.ViewHolder>{
    List<ModelUser> UserList = new ArrayList<>();
    Context context;

    public  AdapterUser(List<ModelUser>UserList,Context context){
        this.UserList = UserList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {

        // Encargado de trabajar con el item.xml y sus componentes

        holder.textVid.setText(Integer.toString(UserList.get(position).getId()));
        holder.textViewName.setText(UserList.get(position).getName());
        holder.textVUsername.setText(UserList.get(position).getUsername());
        holder.textVCompany.setText(UserList.get(position).getCompany1());
      holder.textVadress.setText(UserList.get(position).getAdress());
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        TextView textVid,textVUsername,textVadress,textVCompany;

        public ViewHolder(View item)  {
            super(item);
           item.setOnClickListener(this);
            textVid = (TextView) item.findViewById(R.id.id_item_user);
            textViewName = (TextView) item.findViewById(R.id.name_user);
            textVUsername =(TextView)item.findViewById(R.id.username_user);
            textVadress =(TextView ) item.findViewById(R.id.city_user);
            textVCompany = (TextView) item.findViewById(R.id.name_company_user);
        }

      @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
           Intent intent = new Intent(context, MainActivity2.class);

         intent.putExtra("userId", UserList.get(getLayoutPosition()).getId());
          contextItem.startActivity(intent);
        }
    }

}
