package com.iliasen.lab3.Part2;

public class ChemicalLaboratory extends Laboratory{
    public ChemicalLaboratory(int number, int floor, int square, int howManyComputers) {
        super(number, floor, square, howManyComputers);
    }

    @Override
    public void print() {
        System.out.println("сработал метод print в классе 1");
    }
}
