package com.example.SisonkeBankApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ViewAccount extends AppCompatActivity {

    TextView name, surname, current, savings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        getSupportActionBar().setTitle("Sisonke Bank");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.vaName);
        surname = (TextView) findViewById(R.id.vaSurname);
        current = (TextView) findViewById(R.id.vaCurrent);
        savings = (TextView) findViewById(R.id.vaSavings);

        name.setText(User.getFname());
        surname.setText(User.getLname());
        current.setText("R" + new DecimalFormat("##.##").format(User.getCurrent()));
        savings.setText("R" + new DecimalFormat("##.##").format(User.getSavings()));
    }
}