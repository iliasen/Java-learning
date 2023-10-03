/*
Создайте класс «Аудитория», содержащий следующую информацию: номер, этаж, площадь. Предусмотреть get методы и метод класса «расчет вместимости».
Этот метод должны переопределять производные классы.

Создайте класс «Лекционная аудитория» производный от «Аудитория» содержит дополнительную информацию: количество рядов, количество посадочных мест в ряду, наличие проектора.
Создайте класс «Учебный класс» производный от «Аудитория» содержит дополнительную информацию: количество парт, наличие компьютера.
Создайте класс «Лаборатория» производный от «Аудитория» содержит дополнительную информацию: количество компьютеров.
*/

package com.iliasen.lab3;

import com.iliasen.lab3.Part1.Auditorium;
import com.iliasen.lab3.Part1.Classroom;
import com.iliasen.lab3.Part1.Lab;
import com.iliasen.lab3.Part1.LectureRoom;
import com.iliasen.lab3.Part2.ChemicalLaboratory;
import com.iliasen.lab3.Part2.LectureHall;

public class Main {
    public static void main(String[] args) {
        System.out.println("Part1:");
        Auditorium auditorium = new Auditorium(202, 2, 38);
        System.out.printf("Информация о аудитории:\n 1. Номер аудитории: %d;\n 2. Этаж: %d;\n 3. Площадь аудитории: %d.\n" , auditorium.getNumber(), auditorium.getFloor(), auditorium.getSquare());
        System.out.println(auditorium.calculate() + " студента может вместить аудитория ");

        System.out.println();
        System.out.println();

        LectureRoom lectureRoom = new LectureRoom(303, 3 , 42, 5, 6, true);
        System.out.printf("Информация о аудитории:\n 1. Номер аудитории: %d;\n 2. Этаж: %d;\n 3. Площадь аудитории: %d.\n" , lectureRoom.getNumber(), lectureRoom.getFloor(), lectureRoom.getSquare());
        System.out.println((lectureRoom.calculate() == 1) ? "Лекторная аудитория вмещает": "Не вмещает данный наплыв");

        System.out.println();
        System.out.println();

        Classroom classroom = new Classroom(404, 4, 28, 14, true);
        System.out.printf("Информация о аудитории:\n 1. Номер аудитории: %d;\n 2. Этаж: %d;\n 3. Площадь аудитории: %d.\n" , classroom.getNumber(), classroom.getFloor(), classroom.getSquare());
        System.out.println((classroom.calculate() > 0) ? "Учебная аудитория вмещает студентов" : "Учебная аудитория невмещает студентов");

        System.out.println();
        System.out.println();

        Lab lab = new Lab(505,5, 20, 8);
        System.out.printf("Информация о аудитории:\n 1. Номер аудитории: %d;\n 2. Этаж: %d;\n 3. Площадь аудитории: %d.\nz" , lab.getNumber(), lab.getFloor(), lab.getSquare());
        System.out.println(lab.calculate()>0 ? "Всем студентам хватет компьютеров" : "Компьютеров не хватает взваем могз");

        System.out.println();
        System.out.println();

        System.out.println("Part2");
        ChemicalLaboratory chemicalLaboratory = new ChemicalLaboratory(205, 2, 32, 14);
        chemicalLaboratory.print();

        System.out.println();
        System.out.println();

        LectureHall lectureHall = new LectureHall();
        lectureHall.print();
    }
}