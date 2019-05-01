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
import android.widget.TextView;

/**
 * Created by Mr. JAVA on 2/2/2018.
 */

public class SeeArrayAdapter extends ArrayAdapter<String> {
    private String[] rno;
    private String[] att;
    private String[] totatt;
    private Activity context;
    public SeeArrayAdapter(Activity context,String[] no,String[] satt,String[] total) {
        super(context,R.layout.seeattendance,no);
        this.context= context;
        this.rno=no;
        this.att=satt;
        this.totatt=total;
    }



    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.seeattendance,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) r.getTag();
        }
        viewHolder.txtno.setText(rno[position]);
        viewHolder.txtatt.setText(att[position]);
        viewHolder.txttot.setText(totatt[position]);
        return r;
    }
    class ViewHolder{
        TextView txtno;
        TextView txtatt;
        TextView txttot;
        ViewHolder(View v){
            txtno=v.findViewById(R.id.t1);
            txtatt=v.findViewById(R.id.t2);
            txttot=v.findViewById(R.id.t3);
        }
    }
}
