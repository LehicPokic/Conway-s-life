package com.example.conwayslife;

public class button_cell {
    boolean condition;
    int id_x;
    int id_y;
    public button_cell() {
        this.condition = false;
        this.id_x = 0;
        this.id_y = 0;
    }
    public button_cell(boolean condition, int x, int y) {
        this.condition = condition;
        this.id_x = x;
        this.id_y = y;
    }

    public boolean get_condition() {
        return condition;
    }

    public void set_condition(boolean condition) {
        this.condition = condition;
    }

    public int[] get_id() {
        this.id_y = id_x;
        this.id_y = id_y;
        int [] id = {id_x, id_y};
        return id;
    }

    public void set_id(int id_x, int id_y) {
        this.id_x = id_x;
        this.id_y = id_y;
    }
}
