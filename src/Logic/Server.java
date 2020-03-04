package Logic;

// Java implementation of  Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

// Server class
public class Server extends Web{

    // Vector to store active clients
    static Vector<ClientHandler> ar = new Vector<>();

    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5050
        ServerSocket ss = new ServerSocket(5050);
        System.out.println("listening on port: " + ss.getLocalPort());



        // running infinite loop for getting
        // client request
        while (true)
        {
            // Accept the incoming request
            Socket s = ss.accept();

            System.out.println("New client request received : " + s);

            // obtain input and output streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this client...");

            // Create a new handler object for handling this request.
            ClientHandler mtch = new ClientHandler(dis, dos);

            // Create a new Thread with this object.
            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            // add this client to active clients list
            ar.add(mtch);

            // start the thread.
            t.start();

        }
    }
}