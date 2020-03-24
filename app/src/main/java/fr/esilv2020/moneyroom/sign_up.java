package fr.esilv2020.moneyroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {

    EditText emailId, password;
    Button btnConnexion;
    FirebaseAuth firebaseAuth;
    Button btnHome;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        emailId = (EditText) findViewById(R.id.idSignup);
        password = (EditText) findViewById(R.id.mdp);
        btnHome = (Button) findViewById(R.id.home);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acceuil = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(acceuil);
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email =emailId.getText().toString();
               String pwd = password.getText().toString();
               if(email.isEmpty() )
               {
                   emailId.setError("Remplir email");
                   emailId.requestFocus();
               }
               else if (pwd.isEmpty())
               {
                   password.setError("Remplir password");
                   password.requestFocus();
               }
               else if (pwd.isEmpty() && pwd.isEmpty())
               {
                    Toast.makeText(sign_up.this, "Fields are empty", Toast.LENGTH_SHORT).show();
               }
               else if(!(pwd.isEmpty() && pwd.isEmpty())){
                   firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(sign_up.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful()){
                               Toast.makeText(sign_up.this, "Failed", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               Intent Index = new Intent(getApplicationContext(),Index.class);
                               startActivity(Index);
                               finish();
                           }
                       }
                   });
                }
            }
        });
    }


}
