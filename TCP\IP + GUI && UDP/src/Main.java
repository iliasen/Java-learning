import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) throws IOException {
        //String port = System.getenv("PORT");
        ServerSocket http = new ServerSocket(5000);
        System.out.println("Server start on port: " + http.getLocalPort());
        Socket reg = http.accept();

        InputStream inputstream = reg.getInputStream();
        OutputStream outputstream = reg.getOutputStream();
        int c = inputstream.read(); // чтение байта из входного потока
        outputstream.write(c);    //запись байта в выходной поток
    }
}