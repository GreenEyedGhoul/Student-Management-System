package com.example.assignment2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList Id,name,Roll_number,Phone,Address;

    CustomAdapter(Activity activity, Context context,ArrayList Id,ArrayList name, ArrayList Roll_number, ArrayList Phone, ArrayList Address)
    {
        this.activity = activity;
        this.context = context;
        this.Id = Id;
        this.name = name;
        this.Roll_number = Roll_number;
        this.Phone = Phone;
        this.Address = Address;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.view_Id.setText(String.valueOf(Id.get(position)));
        holder.view_name.setText(String.valueOf(name.get(position)));
        holder.view_roll.setText(String.valueOf(Roll_number.get(position)));
        holder.view_phone.setText(String.valueOf(Phone.get(position)));
        holder.view_address.setText(String.valueOf(Address.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("Id", String.valueOf(Id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("Roll_number", String.valueOf(Roll_number.get(position)));
                intent.putExtra("Phone", String.valueOf(Phone.get(position)));
                intent.putExtra("Address", String.valueOf(Address.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView view_Id,view_name,view_roll,view_phone,view_address;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            view_Id = itemView.findViewById(R.id.view_id);
            view_name = itemView.findViewById(R.id.view_name);
            view_roll = itemView.findViewById(R.id.view_roll);
            view_phone = itemView.findViewById(R.id.view_phone);
            view_address = itemView.findViewById(R.id.view_address);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
