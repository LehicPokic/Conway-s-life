package com.example.conwayslife;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int valueSize = 5;

    button_cell[][] first_generation;

    button_cell[][] next_generation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreate = (Button) findViewById(R.id.button_create);

        TextView size = (TextView)findViewById(R.id.size);

        size.setText(String.valueOf(5));

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
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawField(valueSize);
            }
        });



    }

    private void start() {
        int n = first_generation.length;
        int m = first_generation[0].length;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (first_generation[i][j].get_condition() == true){
                    next_generation[i][j].set_condition(true);
                }
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int aliveNeighbors = countAliveNeighbors(first_generation, i, j);

                 if (next_generation[i][j].get_condition() == true) {
                    if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                        next_generation[i][j].set_condition(false); // клетка умирает
                    } else {
                        next_generation[i][j].set_condition(true); // клетка остается живой
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        next_generation[i][j].set_condition(true); // клетка оживает
                    }
                }
            }
        }
        drawField(next_generation);

    }

    @SuppressLint("ResourceType")
    public void drawField(int size) {

        LinearLayout linearLayoutMain = findViewById(R.id.mainlayout);
        linearLayoutMain.removeAllViews();
        first_generation = new button_cell[size][size];
        next_generation = new button_cell[size][size];

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
                button.setTag(x + " " + y);
                button.setBackgroundResource(R.drawable.button_false);
                first_generation[x][y] = new button_cell();
                next_generation[x][y] = new button_cell();
                button.setOnClickListener(this);
                linearLayout.addView(button);
            }
        }
    }

    @Override
    public void onClick(View button) {
        String xy = button.getTag().toString();
        String[] XY = xy.split(" ");
        int x = Integer.parseInt(XY[0]);

        int y = Integer.parseInt(XY[1]);
        
        first_generation[x][y].reverse_condition();
        if (first_generation[x][y].get_condition() == true) {
            button.setBackgroundResource(R.drawable.button_true);
        }
        else {
            button.setBackgroundResource(R.drawable.button_false);
        }

    }

    public int countAliveNeighbors(button_cell[][] button_cell, int x, int y) {
        int count = 0;
        int index = 0;

        if (button_cell[x][y].get_condition() == true){
            index = 1;
        }
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // Проверяем, не выходит ли индекс за пределы массива
                if (i >= 0 && i < button_cell.length && j >= 0 && j < button_cell[i].length) {
                    // Если текущая ячейка содержит цифру "1"
                    if (button_cell[i][j].get_condition() == true) {
                        count++;
                    }
                }
            }
        }
        return count - index;
    }

    public void drawField(button_cell[][] button_cell) {
        LinearLayout linearLayoutMain = findViewById(R.id.mainlayout);
        linearLayoutMain.removeAllViews();
        for (int x = 0; x < button_cell.length; x++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            linearLayout.setLayoutParams(layoutParams);
            linearLayoutMain.addView(linearLayout);
            for (int y = 0; y < button_cell.length; y++){
                Button button = new Button(this);
                LinearLayout.LayoutParams layoutParamsButton = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                button.setLayoutParams(layoutParamsButton);
                if (button_cell[x][y].get_condition() == true){
                    button.setBackgroundResource(R.drawable.button_true);
                }
                else {
                    button.setBackgroundResource(R.drawable.button_false);
                }

                linearLayout.addView(button);
            }
        }

        for (int i = 0; i < button_cell.length; i++){
            for (int j = 0; j < button_cell.length; j++){
                if (next_generation[i][j].get_condition() == true){
                    first_generation[i][j].set_condition(true);
                }
                else {
                    first_generation[i][j].set_condition(false);
                }
            }
        }
    }
}