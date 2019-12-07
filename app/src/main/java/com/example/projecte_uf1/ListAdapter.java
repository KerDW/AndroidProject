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

    Context ctx;
    int plantillaLayout;
    List<User> users;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.plantillaLayout = resource;
        this.users = objects;
    }

    //Aquest mètode es llança automaticament cada vegada per element
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //parent: el carga dins de l'elemnt pare, el listView que rep de l'activity
        View v = LayoutInflater.from(ctx).inflate(plantillaLayout,parent,false);

        //Obtenim el elements de la llista
        User u = users.get(position);

        //Rescatem els elements de la IU de la plantillaLayout
        TextView pos = v.findViewById(R.id.position);
        TextView name = v.findViewById(R.id.user_name);
        TextView time = v.findViewById(R.id.user_time);

        //Fem un set de la info de l'element actual
        pos.setText(position+1+"");
        name.setText(u.getName());
        time.setText(u.getTime()/1000+"s");

        return v;

    }
}
