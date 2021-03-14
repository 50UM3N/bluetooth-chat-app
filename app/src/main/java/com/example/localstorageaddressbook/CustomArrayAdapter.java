package com.example.localstorageaddressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomArrayAdapter extends RecyclerView.Adapter<CustomArrayAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id,name,address,email,contact;

    CustomArrayAdapter(Context context,
                       ArrayList id,
                       ArrayList name,
                       ArrayList address,
                       ArrayList email,
                       ArrayList contact){
        this.context = context;
        this.id=id;
        this.name=name;
        this.address=address;
        this.email=email;
        this.contact=contact;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.custom_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.adr_id.setText(String.valueOf(id.get(position)));
        holder.adr_name.setText(String.valueOf(name.get(position)));
        holder.adr_email.setText(String.valueOf(email.get(position)));
        holder.adr_contact.setText(String.valueOf(contact.get(position)));
        holder.adr_address.setText(String.valueOf(address.get(position)));


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder {
        TextView adr_id, adr_name, adr_email,adr_contact,adr_address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adr_id = itemView.findViewById(R.id.adr_id);
            adr_name = itemView.findViewById(R.id.adr_name);
            adr_email = itemView.findViewById(R.id.adr_email);
            adr_contact = itemView.findViewById(R.id.adr_contact);
            adr_address = itemView.findViewById(R.id.adr_address);

        }
    }
}
