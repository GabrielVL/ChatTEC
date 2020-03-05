package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Clase servidor que acepta peticiones de los clientes
 */

// *Herencia* en extends
public class Server extends Web {

    static Vector<ClientHandler> ar = new Vector<>();

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(5050);
        System.out.println("listening on port: " + ss.getLocalPort());

        while (true)
        {
            Socket s = ss.accept();
            System.out.println("New client request received : " + s);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this client...");
            ClientHandler mtch = new ClientHandler(dis, dos);

            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");
            ar.add(mtch);

            t.start();

        }
    }
}
