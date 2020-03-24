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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText emailId, password;
    Button btnConnexion;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebauseAuthListener;
    Button btnAccess; //Informations
    Button btnInscription;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebauseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(MainActivity.this, "You are already logged", Toast.LENGTH_SHORT).show();
                    Intent Index = new Intent(getApplicationContext(),Index.class);
                    startActivity(Index);
                    finish();
                }

            }
        };

        emailId = (EditText) findViewById(R.id.promptId);
        password = (EditText) findViewById(R.id.promptPassword);

        btnAccess = (Button) findViewById(R.id.btnAccess);
        btnInscription = (Button) findViewById(R.id.btnInscription);
        btnConnexion = (Button) findViewById(R.id.btnConnect);

        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainScreen = new Intent(getApplicationContext(),mainScreen.class);
                startActivity(mainScreen);
                finish();
            }
        });

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext(),sign_up.class);
                startActivity(signup);
                finish();
            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(pwd.isEmpty() && pwd.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
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
