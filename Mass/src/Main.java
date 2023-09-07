import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        System.out.println("Hello and welcome!");
        mass(5);

        isPalindrome("Madam, I'm Adam!");

        factorial(6);
*/
        int[] mass1 = {0,2,2,5,7,10,22,74,75,77,78,90};
        int[] mass2 = {1,3,12,14,110};

        System.out.println("_______________");
        mergeArrays(mass1, mass2);
    }


    public static int mass(int x) {
        int[] mass = new int[0];
        System.out.println(mass);
        int[] numbers = new int[x];//{int[] {1,2,3..}}
        int ArrayLength = numbers.length;
        int lastElem = numbers[numbers.length - 1];
        System.out.println(lastElem);
        return 0;
    }

    public static boolean isPalindrome(String text){
        String newText = text.replaceAll("[^A-Za-z1-9]+", "");
        newText = newText.toLowerCase();
        System.out.println(newText);

        for(int i=0, j = newText.length()-1;i<newText.length() && j > 0;i++,j--){
            if(newText.charAt(i) != newText.charAt(j)){
                System.out.println("bad");
                return false;
            }
        }
        System.out.println("Ok");
        return true;
    }

    public static BigInteger factorial(int value) {
        BigInteger result = BigInteger.valueOf(1);
        for(int i = 1; i <= value; i++){
            BigInteger x = BigInteger.valueOf(i);
            result = result.multiply(x);
            System.out.println(result);
        }
        return result; // your implementation here
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] arr = new int[a1.length + a2.length];

        int i=0,j=0,k=0;
        while(i < a1.length && j < a2.length){
            if(a1[i]<=a2[j]){
                arr[k]=a1[i];
                i++;
            }else{
                arr[k]=a2[j];
                j++;
            }
            k++;
        }
        if(i == a1.length-1){
            arr[k]=a1[i];
            k++;
            while (j < a2.length){
                arr[k]=a2[j];
                j++;
                k++;
            }
        }else{
            arr[k]=a2[j];
            k++;
            while (i < a1.length){
                arr[k]=a1[i];
                i++;
                k++;
            }
        }
        //System.out.println(Arrays.toString(arr));
        return arr;

        /*int [] result = new int[a1.length + a2.length];
        int pos1 = 0;
        int pos2 = 0;
        while(pos1<a1.length || pos2<a2.length) {
            result[pos1+pos2] = (pos1<a1.length && (pos2 == a2.length || a1[pos1]<a2[pos2]) ?
                    a1[pos1++] : a2[pos2++]);
        }
        return result;*/
    }
}