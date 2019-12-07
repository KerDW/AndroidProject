package com.example.projecte_uf1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class ListAdapter extends ArrayAdapter <User>{

    Context c;
    int layout;
    List<User> users;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);

        this.c = context;
        this.layout = resource;
        this.users = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = LayoutInflater.from(c).inflate(layout,parent,false);

        User u = users.get(position);

        TextView pos = v.findViewById(R.id.position);
        TextView name = v.findViewById(R.id.user_name);
        TextView time = v.findViewById(R.id.user_time);

        pos.setText(position+1+"");
        name.setText(u.getName());
        time.setText(String.format("%.3f",u.getTime()/1000)+"s");

        return v;

    }
}
