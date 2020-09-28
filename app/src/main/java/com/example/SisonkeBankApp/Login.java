package com.example.SisonkeBankApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseConnection db;
    TextView reg;
    EditText user, pass;
    String first, second;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseConnection(this);

        user = (EditText) findViewById(R.id.loginEmail);
        pass = (EditText) findViewById(R.id.loginPass);

        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                first = user.getText().toString();
                second = pass.getText().toString();

                if (first.equals("") || second.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please complete the fields above", Toast.LENGTH_SHORT).show();
                } else {
                    if (validation(first, second)) {
                        String result = db.getUserDetails(first);
                        if (second.equals(result)) {
                            Intent i = new Intent(Login.this, MainPage.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Logged In!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Username and Password does not match!", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "The email or password are not valid", Toast.LENGTH_SHORT).show();

                    }
                }

            }

        });

        reg = (TextView) findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });
    }

    public boolean validation(String e, String p) {

        boolean valid = true;

        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            user.setError("Enter a valid email");
            valid = false;
        }
        if (p.length() < 5) {
            pass.setError("Password must be at least 5 digits");
            valid = false;
        }

        return valid;
    }
}