package com.iliasen.lab3.Part1;

public class Auditorium {
    private int number;
    private int floor;
    private int square;

    public  Auditorium(){
        System.out.println("Конструктор класса аудитория");
        number = 1;
        floor = 1;
        square = 20;
    }

    public Auditorium(int number, int floor, int square) {
        System.out.println("Конструктор класса аудитория с параметрами");
        this.number = number;
        this.floor = floor;
        this.square = square;
    }

    public int getNumber() {
        return number;
    }

    public int getFloor() {
        return floor;
    }

    public int getSquare() {
        return square;
    }

    public int calculate(){
        int result = (int) (square/1.5 - 3);
        return result;
    }
}
