package com.example.SisonkeBankApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainPage extends AppCompatActivity {

    TextView user;
    Button btnTransfer, btnBalance, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        user = (TextView) findViewById(R.id.idUser);
        user.setText("Welcome " + User.getFname());

        btnTransfer = (Button) findViewById(R.id.btnTransfer);
        btnBalance = (Button) findViewById(R.id.btnBalance);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        getSupportActionBar().setTitle("Sisonke Bank");

        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent balance = new Intent(MainPage.this, ViewAccount.class);
                startActivity(balance);

            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transfer = new Intent(MainPage.this, Transfer.class);
                startActivity(transfer);

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "User logged out successfully!", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(MainPage.this, Login.class);
                startActivity(logout);
                finish();
            }
        });


    }
}