/**
 * @author iliasen
 */

import java.math.MathContext;

public class Main {
    public static void main(String[] args) {
        String s = "Hello World !";
        String nothing = null;
        System.out.println(s);
        System.out.println(nothing);
        System.out.println("___________________");


        int key = 4;
        boolean test = (key % 4 == 0 && key % 100 != 0) || key % 400 == 0;
        System.out.println(test);
        System.out.println("\nResult: "+leapYearCount(100));

        System.out.println("___________________");
        flipBit(0, 1);

        System.out.println("___________________");
        charExpression(32);

        System.out.println("\n___________________");
        isPowerOfTwo(-8);
    }

    public static int leapYearCount(int year) {
        int result = 0;
        for(int i = 1; i <= year; i++){
            if((i % 4 == 0 && i % 100 != 0) || i % 400 == 0){
                result++;
            }
        }
        return result;
    }

    public static int flipBit(int value, int bitIndex) {
        System.out.println(Integer.toBinaryString(value));
        int z = value ^(1<< (bitIndex - 1));
        System.out.println(z);
        return z;
    }

    public static char charExpression(int a) {
        char value = '\\';
        char answer = (char) (value + a );
    System.out.print(answer);
        return answer;
}


    public static boolean isPowerOfTwo(int value) {

        for(int i = 0; i<= Math.abs(value)/2; i++){
            if (Math.abs(value) == Math.pow(2, i)) {
                System.out.println("Yes");
                return true;
            }
        }

        return false; // you implementation here
    }
}