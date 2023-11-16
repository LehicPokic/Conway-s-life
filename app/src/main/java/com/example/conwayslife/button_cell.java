package com.example.conwayslife;

public class button_cell {
    boolean condition;

    public button_cell() {
        this.condition = false;
    }
    public button_cell(boolean condition) {
        this.condition = condition;
    }

    public boolean get_condition() {
        return condition;
    }

    public void set_condition(boolean condition) {
        this.condition = condition;
    }
}
