package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Clase cliente que envia y recibe datos de otros clientes
 */

// *Herencia* en extends
public class Client extends Web {
    final static int ServerPort = 5050;

    public static void main(String[] args) throws  IOException {
        Scanner scn = new Scanner(System.in);

        InetAddress ip = InetAddress.getByName("localhost");

        // Se *instancia* el Socket s
        Socket s = new Socket(ip, ServerPort);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Thread sendMessage = new Thread(() -> {
            while (true) {

                String msg = scn.nextLine();

                try {
                    dos.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread readMessage = new Thread(() -> {

            while (true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        });

        sendMessage.start();
        readMessage.start();

    }
}

