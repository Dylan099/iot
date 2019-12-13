package com.example.mirke.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Switch lunes;
    Switch martes;
    Switch miercoles;
    Switch jueves;
    Switch domingo;
    private DatabaseReference dref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dref = FirebaseDatabase.getInstance().getReference();
        lunes = (Switch) findViewById(R.id.lunes);
        martes = (Switch) findViewById(R.id.martes);
        miercoles = (Switch) findViewById(R.id.miercoles);
        jueves = (Switch) findViewById(R.id.jueves);
        domingo = (Switch) findViewById(R.id.viernes);
        lunes.setChecked(false);
        martes.setChecked(false);
        miercoles.setChecked(false);
        jueves.setChecked(false);
        domingo.setChecked(false);

        dref.child("pastillero").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String mode = ds.getValue(String.class);
                    Log.i("data","value = "+mode);
                    String []code=mode.split(";");
                    if (code[0].equals("LunN")){
                        lunes.setChecked(true);
                    }else if(code[0].equals("MarN")){
                        martes.setChecked(true);
                    }else if(code[0].equals("MieN")){
                        miercoles.setChecked(true);
                    }else if(code[0].equals("JueN")){
                        jueves.setChecked(true);
                    }else if(code[0].equals("DomN")){
                        domingo.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("data","Error en la base de datos.......");
            }
        });

    }

}
