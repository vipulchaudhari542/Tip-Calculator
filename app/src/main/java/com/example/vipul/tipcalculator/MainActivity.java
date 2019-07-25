package com.example.vipul.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int tipPercentage;
    final  int INITIAL_TIP_PERCENTAGE = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipPercentage = INITIAL_TIP_PERCENTAGE;

        //setup text change listener
        EditText edtMeal =(EditText) findViewById(R.id.edtMeal);
        edtMeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                displayTotal();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //setup seekBar

        SeekBar slider = (SeekBar) findViewById(R.id.seekBar);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage = progress;
                displayTipPercentage();
                displayTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //setup reset button
        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset the meal pricing
                EditText edtMeal =(EditText) findViewById(R.id.edtMeal);
                edtMeal.setText(null);
                edtMeal.dispatchDisplayHint(View.VISIBLE);

                //reset tip percentage, slider , percentage text view
                tipPercentage = INITIAL_TIP_PERCENTAGE;
                SeekBar slider = (SeekBar) findViewById(R.id.seekBar);
                slider.setProgress(tipPercentage);
                displayTipPercentage();

                //reset reslt text view
                TextView txtResult = (TextView) findViewById(R.id.txtResult);
                txtResult.setText("TOTAL");
            }
        });
    }

    public void displayTipPercentage(){
        TextView display =(TextView) findViewById(R.id.txtPercentage);
        display.setText(tipPercentage + "%");

    }

    public void displayTotal(){
        double mealCoastNumber = 0;
        EditText edtMeal =(EditText) findViewById(R.id.edtMeal);
        String mealCoastText = edtMeal.getText().toString();

        if(!mealCoastText.isEmpty()) {
            mealCoastNumber = Double.parseDouble(mealCoastText);
        }

        double totalCoast = mealCoastNumber * (tipPercentage / 100.0 + 1);

        TextView txtResult =(TextView) findViewById(R.id.txtResult);
        String messageTotal = String.format(Locale.getDefault(), "$ %.2f", totalCoast);
        txtResult.setText(messageTotal);

    }
}
