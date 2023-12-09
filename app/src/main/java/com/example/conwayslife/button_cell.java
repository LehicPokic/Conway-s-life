package com.example.conwayslife;

public class button_cell {
    boolean condition;



    public button_cell() {
        this.condition = false;

    }
    public button_cell(boolean condition, int x, int y) {
        this.condition = condition;

    }

    public boolean get_condition() {
        return condition;
    }

    public void set_condition(boolean condition) {
        this.condition = condition;
    }


    public void reverse_condition() {
        if (this.condition == true) {
            this.condition = false;
        }
        else {
            this.condition = true;
        }
    }
}
