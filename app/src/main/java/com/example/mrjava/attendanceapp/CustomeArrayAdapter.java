package com.example.mrjava.attendanceapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mr. JAVA on 2/2/2018.
 */

public class CustomeArrayAdapter extends ArrayAdapter<String> {
    private String[] no;
    private String[] names;
    private Activity context;



    public CustomeArrayAdapter(Activity context,String[] no,String[] names) {
        super(context, R.layout.single_row,names);
        this.context= context;
        this.no=no;
        this.names=names;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;

        ViewHolder viewHolder=null;
        if(r==null){

            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.single_row,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) r.getTag();
        }

        viewHolder.txtno.setText(no[position]);
        viewHolder.txtname.setText(names[position]);
        viewHolder.cb.setTag(position);

        if(viewHolder.cb.isChecked()){
            viewHolder.cb.setChecked(true);
        }else{
            viewHolder.cb.setChecked(false);
         }

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalViewHolder.cb.isChecked()){
                    finalViewHolder.cb.setChecked(true);
                }
                else{
                    finalViewHolder.cb.setChecked(false);
                }
            }
        });

        return r;

    }

    class ViewHolder{
        TextView txtno;
        TextView txtname;
        CheckBox cb;
        ViewHolder(View v){
            txtno=v.findViewById(R.id.rno);
            txtname=v.findViewById(R.id.rname);
            cb=v.findViewById(R.id.ch1);
        }
    }
}
