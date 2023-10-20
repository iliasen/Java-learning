package serverPart1;

import java.io.*;//импорт пакета, содержащего классы для
//ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для работы в
import java.util.ArrayList;
import java.util.Random;


//сети Internet
public class Server
{public static void main(String[] arg)
{//объявление объекта класса ServerSocket
    ServerSocket serverSocket = null;
    Socket clientAccepted     = null;//объявление объекта класса Socket
    ObjectInputStream  sois   = null;//объявление байтового потока ввода
    ObjectOutputStream soos   = null;//объявление байтового потока вывода
    try {
        System.out.println("server starting....");
        serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
        clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
        System.out.println("connection established....");
//создание потока ввода soos = new
        sois = new ObjectInputStream(clientAccepted.getInputStream());
        soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока
//вывода
        int clientMessageRecieved = (int)sois.readObject();//объявление //строки и присваивание ей данных потока ввода, представленных
//в виде строки (передано клиентом)
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int min = 1;
        int max= clientMessageRecieved;
        Random random = new Random();
        int randomNum = random.nextInt(max - min +1)+min;
        while(clientMessageRecieved > 0)//выполнение цикла: пока
//строка не будет равна «quite»
        {
            max = clientMessageRecieved;
            System.out.println("message recieved: '"+clientMessageRecieved+"'");
            for(int i = 0; i < randomNum; i++){
                randomNum = random.nextInt(max - min +1)+min;
                arr.add(randomNum);
            }
            //clientMessageRecieved = clientMessageRecieved();//приведение символов строки к
//верхнему регистру
            soos.writeObject(arr.toString());//потоку вывода
            arr.clear();
//присваивается значение строковой переменной (передается клиенту)
            clientMessageRecieved = (int)sois.readObject();//строке
//присваиваются данные потока ввода, представленные в виде строки
//(передано клиентом)
        }   }catch(Exception e) {
    } finally {
        try {
            sois.close();//закрытие потока ввода
            soos.close();//закрытие потока вывода
            clientAccepted.close();//закрытие сокета, выделенного для клиента
            serverSocket.close();//закрытие сокета сервера
        } catch(Exception e) {
            e.printStackTrace();//вызывается метод исключения е
        }
    }
}
}
