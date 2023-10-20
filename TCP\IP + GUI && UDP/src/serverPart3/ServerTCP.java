package serverPart3;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerTCP {
    static int countclients = 0; // счетчик подключившихся клиентов

    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            sock = new ServerSocket(1024); // создаем серверный сокет работающий локально по порту 1024
            while (true) { // бесконечный цикл для возможности подключения последовательно нескольних клиентов
                Socket client = sock.accept(); // сработает, когда клиент подключится,
                // для него выделится отдельный сокет client
                countclients++; // количество подключившихся клиентов увеличивается на 1
                System.out.println("=======================================");
                System.out.println("Client " + countclients + " connected");
                is = client.getInputStream(); // получили входной поток для чтения данных
                os = client.getOutputStream();// получили выходной поток для записи данных
                boolean flag = true;
                while (flag) {
                    byte[] bytes = new byte[1024];
                    int bytesRead = is.read(bytes); // чтение информации, посланной клиентом, из входного потока в массив bytes[]
                    if (bytesRead == -1) {
                        break; // если клиент закрыл соединение, выходим из цикла
                    }
                    String str = new String(bytes, 0, bytesRead, "UTF-8").trim(); // переводим тип byte в тип String и вызываем trim() для удаления пробелов
                    String[] clientMessage = str.split("\\s+"); // разбиваем строку на подстроки по пробелам

                    System.out.println(Arrays.toString(clientMessage));

                    StringBuilder builder = new StringBuilder();
                    for (int i = clientMessage.length - 1; i >= 0; i--) {
                        String word = clientMessage[i]; // получаем слово из массива
                        for (int j = word.length() - 1; j >= 0; j--) {
                            char letter = word.charAt(j); // получаем букву из слова
                            builder.append(letter); // добавляем букву в конец строки
                        }
                        builder.append(" "); // добавляем пробел после слова
                    }
                    String mirroredString = builder.toString().trim();


                    bytes = mirroredString.getBytes();
                    os.write(bytes); // отправляем клиенту информацию
                }
                System.out.println("Client " + countclients + " disconnected");
                client.close(); // закрытие сокета, выделенного для работы с подключившимся клиентом
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            if (is != null) {
                is.close(); // закрытие входного потока
            }
            if (os != null) {
                os.close(); // закрытие выходного потока
            }
            if (sock != null) {
                sock.close(); // закрытие серверного сокета
            }
        }
    }
}