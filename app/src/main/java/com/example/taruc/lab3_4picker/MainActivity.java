package com.example.taruc.lab3_4picker;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity  {
    Button buttonDate;
    int tempYear;
    EditText amountInput;
    TextView textViewSaving;
    TextView textViewAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDate = findViewById(R.id.buttonDOB);
         amountInput =  findViewById(R.id.editTextAccountBalance);
         textViewSaving =  findViewById(R.id.textViewEligibleAmount);
         textViewAge = findViewById(R.id.textViewAge);
    }

    public void showDatePicker(View view)
    {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),getString(R.string.datepicker));
    }
    public void processDatePickerResult(int year, int month, int day)
    {
        String month_string =Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);
        tempYear=year;
        buttonDate.setText(dateMessage);
    }
    public void calculateEverything(View view)
    {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear-tempYear;
        double minSaving = 0;
        String displayAge =  Integer.toString(age);
        textViewAge.setText("Age : "+ displayAge);
        if(age>=16&&age<=20)minSaving=5000;
        else if(age>=21&&age<=25)
            minSaving=14000;
        else if(age>=26&&age<=30)
            minSaving=29000;
        else if(age>=31&&age<=35)
            minSaving=50000;
        else if(age>=36&&age<=40)
            minSaving=78000;
        else if(age>=41&&age<=45)
            minSaving=116000;
        else if(age>=46&&age<=50)
            minSaving=165000;
        else if(age>=51&&age<=55)
            minSaving=228000;


        double amount =  Double.parseDouble(amountInput.getText().toString());
        double totalAmount =  amount - minSaving;
        if(totalAmount>=0)
        {
            totalAmount = totalAmount*(30.00/100.00);
            textViewSaving.setText("Eligible Amount : " + totalAmount);
        }
        else
        {
            textViewSaving.setText("Eligible Amount : You Not Eligible");
        }

    }

    public void reset(View view)
    {
        amountInput.setText("");
        amountInput.setHint("Account 1 Balance");
        textViewSaving.setText("Eligible Amount :");
        textViewAge.setText("Age : ");
        buttonDate.setText("Select Date of Birth");
    }
}
