package com.iliasen.lab3.Part1;

public class LectureRoom extends  Auditorium{
    private int numberOfRows;
    private int  numberOfSeatsInARow;

    private boolean rector;

    public LectureRoom() {
        System.out.println("Конструктор класса лекторная");
        this.numberOfRows = 4;
        this.numberOfSeatsInARow = 9;
        this.rector = false;
    }

    public LectureRoom(int numberOfRows, int numberOfSeatsInARow, boolean rector) {
        System.out.println("Конструктор класса лектоная с параметрами");
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsInARow = numberOfSeatsInARow;
        this.rector = rector;
    }

    public LectureRoom(int number, int floor, int square, int numberOfRows, int numberOfSeatsInARow, boolean rector) {
        super(number, floor, square);
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsInARow = numberOfSeatsInARow;
        this.rector = rector;
    }

    @Override
    public int calculate() {
        return (numberOfRows * numberOfSeatsInARow - super.calculate() > 0) ? 1 : 0 ;
    }
}
