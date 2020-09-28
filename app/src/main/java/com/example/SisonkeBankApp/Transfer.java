package com.example.SisonkeBankApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Transfer extends AppCompatActivity {

    double value;
    int index;
    Spinner myspinner;
    TextView current, savings;
    Button transfer;
    EditText amount;
    final String[] arraySpinner = new String[]{
            "Current To Savings", "Savings To Current"
    };
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        getSupportActionBar().setTitle("Sisonke Bank");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myspinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(adapter);

        current = (TextView) findViewById(R.id.tCurrent);
        savings = (TextView) findViewById(R.id.tSavings);
        current.setText("R" + new DecimalFormat("##.##").format(User.getCurrent()));
        savings.setText("R" + new DecimalFormat("##.##").format(User.getSavings()));

        transfer = (Button) findViewById(R.id.btnTransfer);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = myspinner.getSelectedItemPosition();
                amount = (EditText) findViewById(R.id.edtAmount);
                String temp = amount.getText().toString();
                if (temp.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a transfer amount!", Toast.LENGTH_SHORT).show();
                } else {
                    value = Float.parseFloat(temp);
                    DatabaseConnection db = new DatabaseConnection(Transfer.this);
                    Boolean ans = db.updateBalance(User.getCurrent(), User.getSavings(), value, arraySpinner[index], User.getEmail());

                    if (ans.equals(true)) {
                        Toast.makeText(getApplicationContext(), "Transfer was successful", Toast.LENGTH_SHORT).show();
                        Intent success = new Intent(Transfer.this, MainPage.class);
                        startActivity(success);
                    } else {
                        Toast.makeText(getApplicationContext(), "Insufficient funds!", Toast.LENGTH_SHORT).show();
                        amount.setText("");
                        amount.setFocusable(true);
                    }

                }

            }
        });
    }
}