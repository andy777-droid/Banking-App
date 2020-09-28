package com.example.SisonkeBankApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    DatabaseConnection db;
    EditText email, fname, lname, password, mobile;
    Button register;
    RadioGroup group;
    RadioButton selectGender;
    TextView log;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle("Sisonke Bank");

        db = new DatabaseConnection(this);
        email = (EditText) findViewById(R.id.email);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        password = (EditText) findViewById(R.id.password);
        mobile = (EditText) findViewById(R.id.mobile);
        register = (Button) findViewById(R.id.create);
        group = (RadioGroup) findViewById(R.id.radioGrp);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String First = fname.getText().toString();
                String Last = lname.getText().toString();
                String Password = password.getText().toString();
                String Mobile = mobile.getText().toString();

                index = group.getCheckedRadioButtonId();
                selectGender = (RadioButton) findViewById(index);
                String Gender = selectGender.getText().toString();

                if (Email.equals("") || First.equals("") || Last.equals("") || Password.equals("") || Mobile.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please complete the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (validation(Email, Password, Mobile)) {
                        Boolean mail = db.checkEmail(Email);
                        if (mail == true) {
                            Boolean insert = db.addUser(Email, First, Last, Password, Mobile, Gender);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show();
                                Intent success = new Intent(Registration.this, Login.class);
                                startActivity(success);
                            } else {
                                Toast.makeText(getApplicationContext(), "User could not be added!", Toast.LENGTH_SHORT).show();

                            }
                            ;
                        }
                        ;
                    }

                }
            }
        });

        log = (TextView) findViewById(R.id.login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
            }
        });

    }

    public boolean validation(String e, String p, String m) {

        boolean valid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            email.setError("Enter a valid email");
            valid = false;
        }
        if (p.length() < 5) {
            password.setError("Password must be at least 5 digits");
            valid = false;
        }
        if (m.length() != 10) {
            mobile.setError("The mobile number is not 10 digits");
            valid = false;
        }
        return valid;
    }
}