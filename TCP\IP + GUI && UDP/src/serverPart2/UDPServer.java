package serverPart2;

import java.net.*;
import java.io.*;

public class UDPServer {
    public final static int DEFAULT_PORT = 8001;

    public void runServer() throws IOException {
        DatagramSocket socket = null;
        try {
            boolean stopFlag = false;
            byte[] buf = new byte[512];
            socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UDPServer: Started on " + socket.getLocalAddress() + ":" + socket.getLocalPort());

            while (!stopFlag) {
                DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
                socket.receive(receivePacket);
                String numbers = new String(receivePacket.getData()).trim();

                // Обработка полученных чисел
                String[] numberArray = numbers.split(",");
                int x= Integer.parseInt(numberArray[0]);
                int y = Integer.parseInt(numberArray[1]);
                int z = Integer.parseInt(numberArray[2]);
                double res= Math.sqrt(10*(Math.sqrt(x) + Math.pow(x,y*z))*(Math.sin(z)*Math.sin(z) - Math.abs(x + y))*Math.exp(z));
                System.out.println(res);
                // Отправка ответа клиенту
                String response = "Result: " + res;
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                socket.send(sendPacket);
            }

            System.out.println("UDPServer: Stopped");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer();
            server.runServer();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}