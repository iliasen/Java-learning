package clientPart3;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTCP extends Frame implements ActionListener {
    TextField tf, tf1, tf2;
    TextArea ta;

    public static void main(String args[]) {
        ClientTCP c = new ClientTCP();
        c.GUI();
    }

    private void GUI() {
        setTitle("TCP Client");
        setLayout(new FlowLayout());

        Label labelIP = new Label("IP Address:");
        tf = new TextField("127.0.0.1", 15);
        add(labelIP);
        add(tf);

        Label labelPort = new Label("Port:");
        tf1 = new TextField("1024", 5);
        add(labelPort);
        add(tf1);

        Label labelData = new Label("Data to send:");
        tf2 = new TextField(30);
        add(labelData);
        add(tf2);

        Button buttonSend = new Button("Send");
        buttonSend.addActionListener(this);
        add(buttonSend);

        ta = new TextArea(10, 40);
        add(ta);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        setSize(450, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Send")) {
            String serverAddress = tf.getText();
            int serverPort = Integer.parseInt(tf1.getText());
            String dataToSend = tf2.getText();

            try {
                Socket socket = new Socket(serverAddress, serverPort);
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                byte[] dataBytes = dataToSend.getBytes();
                os.write(dataBytes);

                byte[] buffer = new byte[1024];
                int bytesRead = is.read(buffer);

                String receivedData = new String(buffer, 0, bytesRead);
                ta.append("Received from server: " + receivedData + "\n");

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}