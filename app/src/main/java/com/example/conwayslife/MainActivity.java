package com.example.conwayslife;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int valueSize = 5;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreate = (Button) findViewById(R.id.button_create);

        TextView size = (TextView)findViewById(R.id.size);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        Button buttonStart = (Button) findViewById(R.id.button_start);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                size.setText(String.valueOf(progress));
                valueSize = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawField(valueSize);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

    }

    private void start() {


    }

    public void drawField(int size) {

        LinearLayout linearLayoutMain = findViewById(R.id.mainlayout);
        linearLayoutMain.removeAllViews();

        button_cell[][] button_cell = new button_cell[size][size];


        for (int x = 0; x < size; x++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            linearLayout.setLayoutParams(layoutParams);
            linearLayoutMain.addView(linearLayout);
            for (int y = 0; y < size; y++){
                Button button = new Button(this);
                LinearLayout.LayoutParams layoutParamsButton = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                button.setLayoutParams(layoutParamsButton);
                button.setTag(x + "*" + y);
                button_cell[x][y] = new button_cell();
                button_cell[x][y].set_id(x, y);
                button.setOnClickListener(this);
                linearLayout.addView(button);
            }
        }
    }

    @Override
    public void onClick(View button) {
        TextView size = (TextView)findViewById(R.id.size);
        size.setText(button.getTag().toString());

    }
}