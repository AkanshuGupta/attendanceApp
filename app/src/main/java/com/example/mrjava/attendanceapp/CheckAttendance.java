package com.example.mrjava.attendanceapp;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
/**
 * A simple {@link Fragment} subclass.
 */
public class CheckAttendance extends Fragment {
    ListView listSee;
    private DBhelper dBhelper;
    String st6;
    TextView oktext;
    ImageView imgshare;
    EditText classname;
    StringBuilder builder=new StringBuilder();
    StringBuilder build=new StringBuilder();
    StringBuilder buil=new StringBuilder();
    public CheckAttendance() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_attendance, container, false);
        listSee = view.findViewById(R.id.listsee);
        classname=view.findViewById(R.id.ecname);
        oktext=view.findViewById(R.id.ok);
        imgshare=view.findViewById(R.id.share);
        dBhelper = new DBhelper(getContext());
        final SQLiteDatabase newDB = dBhelper.getReadableDatabase();
        oktext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgshare.setVisibility(view.VISIBLE);
                st6=classname.getText().toString();
                final Cursor c = newDB.rawQuery("select sname,att,totatt,smob from student where saddr='" + st6 + "'", null);
                c.moveToFirst();
                int countcustom = c.getCount();
                if (countcustom < 1) {
                    Toast.makeText(getContext(), "Enter student details", Toast.LENGTH_LONG).show();
                } else {
                    String[] it = new String[c.getCount()];
                    String[] it1 = new String[c.getCount()];
                    String[] it2 = new String[c.getCount()];
                    String[] it3 = new String[c.getCount()];
                    final HashMap<String, Integer> mpp = new HashMap<String, Integer>();
                    final HashMap<String, Integer> mppp = new HashMap<String, Integer>();
                    int n = 0;
                    //loop for populate data in Student newTakeAttendance
                    do {
                        it[n] = c.getString(0);
                        it1[n] = String.valueOf(c.getInt(1));
                        it2[n] = String.valueOf(c.getInt(2));
                        it3[n] = c.getString(3);
                        n = n + 1;
                    } while (c.moveToNext());
                    SeeArrayAdapter arAapter = new SeeArrayAdapter((Activity) getContext(), it, it1, it2);
                    listSee.setAdapter(arAapter);

                    for(String i: it1){
                        builder.append(""+i+",");
                    }
                    for(String j: it){
                        build.append(""+j+",");
                    }
                    for(String m: it3){
                        buil.append(""+m+",");
                    }
                    //Toast.makeText(getContext(),""+builder+",build"+build,Toast.LENGTH_LONG).show();
                }
            }
        });
        imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mblNumVar = ""+buil;
                Intent smsMsgAppVar = new Intent(Intent.ACTION_VIEW);
                smsMsgAppVar.setData(Uri.parse("sms:" +  mblNumVar));
                smsMsgAppVar.putExtra("sms_body","roll number"+build+"attendance"+builder);
                startActivity(smsMsgAppVar);
                //Toast.makeText(getContext(),""+builder,Toast.LENGTH_LONG).show();
            }
        });
        return view;
        }
    }


