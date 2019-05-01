package com.example.mrjava.attendanceapp;


import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class createnew extends Fragment {
    Button bnew, bcheck;
    ImageView brefresh;
    private DBhelper cdbhelper,rhelper;
    //String stu[]={"aa","bb","cc","dd","ee","ff","gg"};
    EnterEntry ee = new EnterEntry();
    newTkAtt ntatt = new newTkAtt();
    CheckAttendance cat = new CheckAttendance();
    EnterClassName ecn = new EnterClassName();
    DBhelper ddhelper;

    public createnew() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_createnew, container, false);
        bnew = view.findViewById(R.id.btncreate);
        final ListView list = view.findViewById(R.id.listview);
        brefresh = view.findViewById(R.id.refresh2);
        bcheck = view.findViewById(R.id.btncheck);
        ddhelper = new DBhelper(getContext());
        bnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.createnew, ecn);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        cdbhelper = new DBhelper(getContext());
        String projection[] = {"cname"};
        SQLiteDatabase cdb = cdbhelper.getReadableDatabase();
        final Cursor cc = cdb.query("className", projection, null, null, null, null, null);
        cc.moveToFirst();
        int count = cc.getCount();
        String[] citem = new String[cc.getCount()];
        if (count < 1) {
            Toast.makeText(getContext(), "create a class", Toast.LENGTH_SHORT).show();
        } else {
            int i = 0;
            do {
                citem[i] = cc.getString(0).toString();
                i = i + 1;
            } while (cc.moveToNext());
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, citem);
            //ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,stu);

            list.setAdapter(adapter);
        }
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String tbName = list.getItemAtPosition(i).toString();

                    Bundle args = new Bundle();
                    args.putString("ky", tbName);
                    ntatt.setArguments(args);
                    Toast.makeText(getContext(), "positin " + i + " " + tbName, Toast.LENGTH_SHORT).show();
                    FragmentManager fM = getFragmentManager();
                    FragmentTransaction fT = fM.beginTransaction();
                    fT.replace(R.id.createnew, ntatt);
                    fT.addToBackStack(null);
                    fT.commit();
                }
            });
            //delete class name
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    final String str = (String) list.getItemAtPosition(i);
                    builder.setTitle("delete class " + str);
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // class deleted succesfully
                            ddhelper.DeleteData(str);

                            Toast.makeText(getContext(), "deleted succesfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // cancel
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
            });
            bcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fM = getFragmentManager();
                    FragmentTransaction fT = fM.beginTransaction();
                    fT.replace(R.id.createnew, cat);
                    fT.addToBackStack(null);
                    fT.commit();
                }
            });
            brefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    rhelper = new DBhelper(getContext());
                    String projection[] = {"cname"};
                    SQLiteDatabase cdd = rhelper.getReadableDatabase();
                    final Cursor ccc = cdd.query("className", projection, null, null, null, null, null);
                    ccc.moveToFirst();
                    int count = ccc.getCount();
                    String[] citem = new String[ccc.getCount()];
                    if (count < 1) {
                        Toast.makeText(getContext(), "create a class", Toast.LENGTH_SHORT).show();
                    } else {
                        int i = 0;
                        do {
                            citem[i] = ccc.getString(0).toString();
                            i = i + 1;
                        } while (ccc.moveToNext());
                        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, citem);
                        //ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,stu);
                        list.setAdapter(adapter);
                    }

                    Toast.makeText(getContext(),"Refresh",Toast.LENGTH_SHORT).show();
                }
            });
            return view;

    }
}
