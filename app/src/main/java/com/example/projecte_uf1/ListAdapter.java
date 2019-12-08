package com.example.projecte_uf1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        final User u = users.get(position);

        TextView pos = v.findViewById(R.id.position);
        TextView name = v.findViewById(R.id.user_name);
        TextView time = v.findViewById(R.id.user_time);
        ImageView share = v.findViewById(R.id.shareScore);

        pos.setText(position+1+"");
        name.setText(u.getName());
        time.setText(String.format("%.3f",u.getTime()/1000)+"s");

        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                PackageManager pm = c.getPackageManager();
                try {

                    // any type of share needs to use action_send
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "Check out my time in this try: "+u.getTime()/1000+"s!";

                    // get whatsapp package info to determine whether whatsapp is installed on the phone
                    // this allows us to catch a specific exception on the catch block
                    PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    waIntent.setPackage("com.whatsapp");

                    // put the text I need in the intent then share
                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    c.startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(c, "WhatsApp not found, unable to share score.", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

        return v;

    }
}
