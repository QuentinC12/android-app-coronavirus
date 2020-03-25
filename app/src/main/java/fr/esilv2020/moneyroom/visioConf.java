package fr.esilv2020.moneyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class visioConf extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    Button askRdv;
    Button btnLogoutVisio;
    Switch switchAttach;
    EditText insuranceNumber;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visio_conf);

        askRdv = (Button) findViewById(R.id.askRdv);
        btnLogoutVisio = (Button) findViewById(R.id.btnLogoutVisio);
        switchAttach = (Switch) findViewById(R.id.switchAttach);
        insuranceNumber =(EditText)  findViewById(R.id.insuranceNumber);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Visio");

        askRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.setValue(insuranceNumber.getText().toString());


                Toast.makeText(visioConf.this,"You'll receive a confirmation", Toast.LENGTH_LONG).show();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent index = new Intent(getApplicationContext(),Index.class);
                        startActivity(index);
                        finish();
                    }
                },3000);

            }
        });

        btnLogoutVisio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent index = new Intent(getApplicationContext(),Index.class);
                startActivity(index);
                finish();
            }
        });




    }
}
