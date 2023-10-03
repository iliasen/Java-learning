package com.iliasen.lab3.Part2;

public abstract class Laboratory implements Auditorium,Obj {

    private int number;
    private int floor;
    private int square;
    private int howManyComputers;

    public Laboratory(int number, int floor,int square, int howManyComputers) {
        System.out.println("Конструктор класса лабораторная p2");
        this.number = number;
        this.floor = floor;
        this.square = square;
        this.howManyComputers = howManyComputers;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getFloor() {
        return floor;
    }

    @Override
    public int getSquare() {
        return square;
    }

    public int getHowManyComputers() {
        return howManyComputers;
    }

    @Override
    public void print() {
        // Реализация отсутствует
        throw new UnsupportedOperationException("Должно быть реализовано в подклассе");
    }
}
