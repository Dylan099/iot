package com.example.mirke.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button;
    ListView lista;
    ArrayAdapter<String> adaptador;
    TextView tv;
    private DatabaseReference dref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dref = FirebaseDatabase.getInstance().getReference();
        button = (Button) findViewById(R.id.boton);
        lista = (ListView)findViewById(R.id.idLV);
        tv = (TextView) findViewById(R.id.tv);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dref.child("pastillero").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adaptador.clear();
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            String mode = ds.getValue(String.class);
                            Log.i("data","value = "+mode);
                            //adaptador.add("Mensaje = "+mode);
                            tv.setText("Mensaje = "+mode);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("data","Error en la base de datos.......");
                    }
                });
            }
        });

    }

}
