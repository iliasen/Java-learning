public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        mass(5);

        isPalindrome("Madam, I'm Adam!");
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
}