package com.example.mrjava.attendanceapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnterClassName extends Fragment {
Button cancel,btsave;
    EditText clName;
    private DBhelper cdbhelper;
    //createnew cn=new createnew();

    public EnterClassName() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_enter_class_name, container, false);
        cancel=view.findViewById(R.id.cancel);
        cdbhelper=new DBhelper(getContext());
        final DBhelper dBhelper=new DBhelper(getContext());
        btsave=view.findViewById(R.id.save);
        clName=view.findViewById(R.id.clsName);
        final FragmentManager fm = getActivity().getSupportFragmentManager();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.popBackStack();

            }
        });
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result=dBhelper.classinsert(clName.getText().toString());
                if(result){
                    clName.setText(null);
                    Toast.makeText(getContext(),"saved "+clName.getText().toString(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"something wrong ",Toast.LENGTH_LONG).show();
                };

                fm.popBackStack();
            }
        });

        return view;
    }
}
