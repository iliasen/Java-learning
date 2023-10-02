import Part1.Stock;
import Part2.Conductor;
import Part2.strings.Gitar;
import Part2.strings.Violin;
import Part2.brass.Clarinet;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        global:while (true){
            System.out.print("Choose:\n 1. Part1;\n 2. Part2;\n 3. Exit.\nInput a number: ");
            Scanner in = new Scanner(System.in);
            int part = in.nextInt();
            switch (part){
                case 1:
                    System.out.println("Start Part1 ");
                    Part1();
                    break;
                case 2:
                    System.out.println("Start Part2 ");
                    Part2();
                    break;
                default:
                    break global;
            }
        }
    }

    public static void Part1(){
        ArrayList <Stock> mass = new ArrayList<>();

        System.out.println("Hello !");
        out:while(true){
            System.out.print("Choose:\n 1. Add product;\n 2. Print all data;\n 3. Calculation of the cost and quantity of goods;\n 4. Exit.\nInput a number: ");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();

            switch (num){
                case 1:
                    System.out.print("Input name: ");
                    Scanner string = new Scanner(System.in);
                    String name = string.next();
                    System.out.print("Input price: ");
                    Scanner integer = new Scanner(System.in);
                    int price = integer.nextInt();
                    System.out.print("Input availability: ");
                    integer = new Scanner(System.in);
                    int availability = integer.nextInt();
                    Stock add = new Stock(name, price, availability);
                    mass.add(add);
                    break;
                case 2:
                    if(mass.size() == 0){
                        System.out.println("Mass is empty !");
                        break;
                    }
                    int number = 1;
                    for(Stock s : mass) {
                        System.out.printf("№ %d ", number);
                        s.getAll();
                        number++;
                    }
                    break;
                case 3:
                    if(mass.size() == 0){
                        System.out.println("Mass is empty !");
                        break;
                    }
                    Stock.calculate(mass);
                    break;
                case 4:
                    System.out.println("Goodbye");
                    break out;
                default:
                    System.out.println("Error");
            }

        }
    }

    public static void Part2(){
        Gitar gitar = new Gitar(2007, "Wood", "Indian", 6, true);
        gitar.Play();
        System.out.println(gitar.toString());

        System.out.println("Strings in gitar: " + gitar.getNumberOfStrings());
        Violin violin = new Violin(2015,"Wood", "Беларусь", 4, 'L');
        violin.Play();
        System.out.println(violin.toString());


        Clarinet clarinet = new Clarinet(1999,"Metal", "Yamaha", 54, 2);
        System.out.println(clarinet.toString());

        Conductor conductor = new Conductor();
        conductor.addInstruments(gitar);
        conductor.addInstruments(violin);
        conductor.addInstruments(clarinet);
        conductor.printInstruments();
    }
}