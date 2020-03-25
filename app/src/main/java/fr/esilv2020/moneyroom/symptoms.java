package fr.esilv2020.moneyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class symptoms extends AppCompatActivity {

    Switch nose;
    Switch throat;
    Switch cough;
    Switch respire;
    Button btnValid;
    public static final String SWITCHNOSE = "nose";
    public static final String SWITCHTHROAT = "throat";
    public static final String SWITCHCOUGH = "cough";
    public static final String SWITCHRESPIRE = "respire";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        nose = (Switch) findViewById(R.id.switchNose);
        throat = (Switch) findViewById(R.id.switchGorge);
        cough = (Switch) findViewById(R.id.switchToux);
        respire = (Switch) findViewById(R.id.switchRespire);
        btnValid = (Button) findViewById(R.id.valider);
        loadData();
        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent index = new Intent(getApplicationContext(),Index.class);
                startActivity(index);
                finish();
            }
        });
    }

    public void saveData()
    {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(SWITCHNOSE, nose.isChecked());
        editor.putBoolean(SWITCHCOUGH, cough.isChecked());
        editor.putBoolean(SWITCHTHROAT, throat.isChecked());
        editor.putBoolean(SWITCHRESPIRE, respire.isChecked());
        editor.commit();
        Toast.makeText(this,"data saved", Toast.LENGTH_LONG).show();
    }

    public void loadData()
    {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        nose.setChecked(sharedPref.getBoolean(SWITCHNOSE, false));
        cough.setChecked(sharedPref.getBoolean(SWITCHCOUGH, false));
        throat.setChecked(sharedPref.getBoolean(SWITCHTHROAT, false));
        respire.setChecked(sharedPref.getBoolean(SWITCHRESPIRE, false));



    }
}
