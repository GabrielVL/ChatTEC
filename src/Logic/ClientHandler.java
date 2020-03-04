package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

// ClientHandler class
class ClientHandler  extends Web implements Runnable {
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    boolean isloggedin;

    // constructor
    public ClientHandler(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.isloggedin=true;
    }

    @Override
    public void run() {

        String received;

        try {
            received = dis.readUTF();
            this.name = received;
        }catch (IOException e) {
            System.out.println(e);
        }

        while (true)
        {
            try
            {
                // receive the string
                received = dis.readUTF();

                System.out.println(received);

                if(received.equals("logout")){
                    this.isloggedin=false;
                    this.s.close();
                    break;
                }

                // break the string into message and recipient part
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();

                // search for the recipient in the connected devices list.
                // ar is the vector storing client of active users
                for (ClientHandler mc : Server.ar)
                {
                    // if the recipient is found, write on its
                    // output stream
                    if (mc.name.equals(recipient) && mc.isloggedin==true)
                    {
                        mc.dos.writeUTF(this.name+" : "+MsgToSend);
                        break;
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
