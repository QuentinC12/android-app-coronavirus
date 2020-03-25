package fr.esilv2020.moneyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class medical_report extends AppCompatActivity {

    EditText name;
    EditText first_name;
    EditText age;
    EditText height;
    EditText weight;
    Button validationMedicalReport;

    public static final String nom = "NOM";
    public static final String prenom = "PRENOM";
    public static final String age2 = "AGE";
    public static final String poids = "POIDS";
    public static final String taille = "TAILLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        name = (EditText) findViewById(R.id.name);
        first_name = (EditText) findViewById(R.id.first_name);
        age = (EditText) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        validationMedicalReport = (Button) findViewById(R.id.validationMedicalReport);

        name.setText(getResources().getString(R.string.name));
        first_name.setText(getResources().getString(R.string.first_name));
        age.setText(getResources().getString(R.string.age));
        height.setText(getResources().getString(R.string.height));
        weight.setText(getResources().getString(R.string.weight));
        loadData();
        //Toast.makeText(this,"Values loa",Toast.LENGTH_LONG).show();
        //int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);



        validationMedicalReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent mainScreen = new Intent(getApplicationContext(),Index.class);
                startActivity(mainScreen);
                finish();
            }
        });

    }
    public void saveData()
    {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(nom, name.getText().toString());
        editor.putString(prenom, first_name.getText().toString());
        editor.putString(age2, age.getText().toString());
        editor.putString(poids, weight.getText().toString());
        editor.putString(taille, height.getText().toString());
        editor.commit();
        Toast.makeText(this,"data saved", Toast.LENGTH_LONG).show();
    }

    public void loadData()
    {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        name.setText(sharedPref.getString(nom, ""));
        first_name.setText(sharedPref.getString(prenom, ""));
        age.setText(sharedPref.getString(age2, ""));
        weight.setText(sharedPref.getString(poids, ""));
        height.setText(sharedPref.getString(taille, ""));



    }
}
