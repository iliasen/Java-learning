package com.iliasen.lab3.Part2;

public class LectureHall implements Obj {
    private int numberOfRows;
    private int  numberOfSeatsInARow;

    private boolean rector;

    public LectureHall() {
        System.out.println("Конструктор класса лекторная p2");
        this.numberOfRows = 4;
        this.numberOfSeatsInARow = 9;
        this.rector = false;
    }

    public LectureHall(int numberOfRows, int numberOfSeatsInARow, boolean rector) {
        System.out.println("Конструктор класса лектоная p2 с параметрами");
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsInARow = numberOfSeatsInARow;
        this.rector = rector;
    }

    @Override
    public void print() {
        System.out.println("сработал метод print в классе 2");
    }
}
