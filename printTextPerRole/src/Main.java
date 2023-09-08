import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        String[] roles = new String[] {"Городничий\n",
                "Аммос Федорович\n",
                "Артемий Филиппович\n" ,
                "Лука Лукич"};
        String[] textLines = new String[] {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.\n" ,
                "Аммос Федорович: Как ревизор?\n" ,
                "Артемий Филиппович: Как ревизор?\n" ,
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.\n" ,
                "Аммос Федорович: Вот те на!\n" ,
                "Артемий Филиппович: Вот не было заботы, так подай!\n" ,
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!\n"};

        printTextPerRole(roles, textLines);
    }

    public static String printTextPerRole(String[] roles, String[] textLines) {
        /*System.out.println(Arrays.toString(roles));
        System.out.println(Arrays.toString(textLines));
        */
        for(String i : roles){
            for(String j : textLines){
                //System.out.println("i: "+ i + ", j: " + j);
                String[] newLine = j.split(":");
                System.out.println(Arrays.toString(newLine));
            }
        }
        return "";
    }
}