package app.azim.techxnoss.covid_19_kenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText email = findViewById(R.id.editTextTextEmailAddress);
                EditText passcode = findViewById(R.id.editTextTextPassword);
                EditText name = findViewById(R.id.editTextTextPersonName);

                if (email.getText().toString().trim().equals("")){
                    email.setError("Type email");

                }else if(passcode.getText().toString().trim().equals("")){
                    passcode.setError("Type passcode");

                }else if(name.getText().toString().trim().equals("")){
                    name.setError("Your name!");

                }

                else{
                    Toast.makeText(getApplicationContext(), "Loading...",
                            Toast.LENGTH_SHORT).show();

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString().trim(),
                            passcode.getText().toString().trim())
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");

                                        Intent home = new Intent(SignUpActivity.this,
                                                AssesUser.class);
                                        startActivity(home);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
            }
        });



        findViewById(R.id.signInInsteadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(SignUpActivity.this,
                        LoginActivity.class);
                startActivity(home);
                finish();
            }
        });

    }
}