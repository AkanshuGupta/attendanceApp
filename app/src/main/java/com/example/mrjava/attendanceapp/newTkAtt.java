package com.example.mrjava.attendanceapp;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class newTkAtt extends Fragment {
    TextView textbt,btsave;
    ImageView btnrefresh;
    EnterEntry ee = new EnterEntry();
    CheckBox cb;
    String saddr="saddr";
    String cl="1st";
    RadioButton rb;
    ListView listPresent;
    DBhelper dddbhelper;
    private DBhelper dBhelper;

    public newTkAtt() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_tk_att, container, false);
        textbt = view.findViewById(R.id.addnew);
        //rg=view.findViewById(R.id.rbtn);
        final String st1=getArguments().getString("ky").toString();
        listPresent = view.findViewById(R.id.listPresent);
        btsave=view.findViewById(R.id.bsave);
        btnrefresh=view.findViewById(R.id.refresh);
        dddbhelper=new DBhelper(getContext());
        dBhelper = new DBhelper(getContext());
        String projectio[]={"srno","sname"};
        SQLiteDatabase newDB = dBhelper.getReadableDatabase();
        //final Cursor c = newDB.query("student",projectio,null,null,null,null,null);
        final Cursor c = newDB.rawQuery("select srno,sname from student where saddr='"+st1+"'",null);
        c.moveToFirst();
        int countcustom=c.getCount();
        if(countcustom<1){
            Toast.makeText(getContext(),"Enter student details",Toast.LENGTH_LONG).show();
        }else {
            String[] item = new String[c.getCount()];
            final HashMap<String, String> map = new HashMap<String, String>();
            final String[] item2 = new String[c.getCount()];
            final HashMap<String, String> mapp = new HashMap<String, String>();
            int n = 0;
            //loop for populate data in Student newTakeAttendance
            do {
                mapp.put(c.getString(0), c.getString(1));
                map.put(c.getString(0), c.getString(1));
                item2[n] = c.getString(1).toString();
                item[n] = c.getString(0).toString();
                n = n + 1;
            } while (c.moveToNext());
            final CustomeArrayAdapter customer = new CustomeArrayAdapter((Activity) getContext(), item2, item);
            listPresent.setAdapter(customer);
            listPresent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    cb = view.findViewById(R.id.ch1);
                    TextView textview1 = (TextView) view.findViewById(R.id.rno);
                    final String str5= textview1.getText().toString();
                    if (cb.isChecked()) {
                        cb.setChecked(false);
                        dddbhelper.updatDecrement(st1,str5);

                    } else {
                        cb.setChecked(true);
                        dddbhelper.updat(st1,str5);

                    }
                }
            });
            listPresent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, final View view, int i, long l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    final TextView textview1 = (TextView) view.findViewById(R.id.rno);
                    final String st2 = textview1.getText().toString();
                    builder.setTitle("delete class " + textview1.getText().toString());
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // student deleted succesfully
                            dddbhelper.StudentDelete(st2);
                            Toast.makeText(getContext(), st2 + " deleted succesfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
            });
        }
            textbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext()," "+st1,Toast.LENGTH_SHORT).show();
                    Bundle arg=new Bundle();
                    arg.putString("kyy",st1);
                    ee.setArguments(arg);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.newtkatt, ee);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
        final FragmentManager fmm = getActivity().getSupportFragmentManager();
            btsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dddbhelper.updattotal(st1);
                    Toast.makeText(getContext(),"saved",Toast.LENGTH_SHORT).show();
                    fmm.popBackStack();
                }
            });
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dddbhelper = new DBhelper(getContext());
                dBhelper = new DBhelper(getContext());
                String projectio[] = {"srno", "sname"};
                SQLiteDatabase newDB = dBhelper.getReadableDatabase();
                //final Cursor c = newDB.query("student",projectio,null,null,null,null,null);
                final Cursor c = newDB.rawQuery("select srno,sname from student where saddr='" + st1 + "'", null);
                c.moveToFirst();
                int countcustom = c.getCount();
                if (countcustom < 1) {
                    Toast.makeText(getContext(), "Enter student details", Toast.LENGTH_SHORT).show();
                } else {
                    String[] item = new String[c.getCount()];
                    final HashMap<String, String> map = new HashMap<String, String>();
                    String[] item2 = new String[c.getCount()];
                    final HashMap<String, String> mapp = new HashMap<String, String>();
                    int n = 0;
                    //loop for populate data in Student newTakeAttendance
                    do {
                        mapp.put(c.getString(0), c.getString(1));
                        map.put(c.getString(0), c.getString(1));
                        item2[n] = c.getString(1).toString();
                        item[n] = c.getString(0).toString();
                        n = n + 1;
                    } while (c.moveToNext());
                    final CustomeArrayAdapter customer = new CustomeArrayAdapter((Activity) getContext(), item2, item);
                    listPresent.setAdapter(customer);
                }
            }
        });
        return view;
    }
}