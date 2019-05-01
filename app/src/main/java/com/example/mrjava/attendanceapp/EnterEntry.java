package com.example.mrjava.attendanceapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnterEntry extends Fragment {
 Button rst,savedata;
    ListView listPresent;
    EditText ename,ern,emn;
    TextView eaddr;
  private DBhelper helper;
    String strpos;
    public EnterEntry() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_enter_entry, container, false);
        rst=view.findViewById(R.id.rst);
        helper=new DBhelper(getContext());
        listPresent = view.findViewById(R.id.listPresent);
        ename=view.findViewById(R.id.edname);
        ern=view.findViewById(R.id.rollno);
        emn=view.findViewById(R.id.mno);
        eaddr=view.findViewById(R.id.addr);
        strpos=getArguments().getString("kyy").toString();
        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext()," "+strpos,Toast.LENGTH_LONG).show();
                emn.setText(null);
                ename.setText(null);
                ern.setText(null);
                //eaddr.setText(null);
            }
        });
        eaddr.setText(strpos);

        savedata=view.findViewById(R.id.stdsave);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result=helper.insertStudentData(ename.getText().toString(),ern.getText().toString(),emn.getText().toString(),eaddr.getText().toString());
                //boolean result=helper.insertStudentData("C","1","23","32");
                if(result){
                    emn.setText(null);
                    ename.setText(null);
                    ern.setText(null);
                    Toast.makeText(getContext(),"saved ",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"something wrong ",Toast.LENGTH_LONG).show();
                };

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        return view;
    }

}
