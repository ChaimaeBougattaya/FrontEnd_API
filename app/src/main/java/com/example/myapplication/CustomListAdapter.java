package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends ArrayAdapter<Student> {
    ArrayList<Student> ListData;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Student> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.ListData = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);
        }
        Student student = getItem(position);

        TextView textid = (TextView) convertView.findViewById(R.id.txtid);//id
        textid.setText(String.valueOf(student.getId()));

        TextView textname = (TextView) convertView.findViewById(R.id.txtname);//nom
        textname.setText(student.getNom());

        TextView textprenom = (TextView) convertView.findViewById(R.id.txtprenom);//prenom
        textprenom.setText(student.getPrenom());

        TextView textusername = (TextView) convertView.findViewById(R.id.txtusername);//username
        textusername.setText(student.getUsername());

        return convertView;
    }
}