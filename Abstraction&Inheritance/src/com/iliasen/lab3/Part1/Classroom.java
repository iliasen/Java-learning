package com.iliasen.lab3.Part1;

public class Classroom extends  Auditorium{
    private int numberOfDesks;
    private boolean computer;

    public Classroom(int numberOfDesks, boolean computer) {
        System.out.println("Конструктор класса учебная аудитория");
        this.numberOfDesks = numberOfDesks;
        this.computer = computer;
    }

    public Classroom(int number, int floor, int square, int numberOfDesks, boolean computer) {
        super(number, floor, square);
        this.numberOfDesks = numberOfDesks;
        this.computer = computer;
        System.out.println("Конструктор класса учебная аудитория с параметрами");

    }

    @Override
    public int calculate() {
        return numberOfDesks - super.calculate();
    }
}
