package com.iliasen.lab3.Part1;

public class Lab extends Auditorium{
    public int howManyComputers;

    public Lab(int howManyComputers) {
        System.out.println("Конструктор класса лабораторная");
        this.howManyComputers = howManyComputers;
    }

    public Lab(int number, int floor, int square, int howManyComputers) {
        super(number, floor, square);
        this.howManyComputers = howManyComputers;
        System.out.println("Конструктор класса лабораторная с параметрами");
    }

    @Override
    public int calculate() {
        return howManyComputers - super.calculate();
    }
}
