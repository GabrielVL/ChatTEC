package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Clase que maneja varios pedidos de clientes
 */

// ClientHandler es una *clase*
class ClientHandler  extends Web implements Runnable {

    // *Encapsulación* en private
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    boolean isloggedin;

    // *Método* constructor de ClientHandler
    public ClientHandler(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.isloggedin = true;
    }

    @Override // *Sobreescritura* del método run
    public void run() {

        String received;

        try {
            received = dis.readUTF();
            this.name = received;
        } catch (IOException e) {
            System.out.println(e);
        }

        while (true) {
            try {
                received = dis.readUTF();

                System.out.println(received);

                if(received.equals("logout")) {
                    this.isloggedin=false;
                    this.s.close();
                    break;
                }

                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();

                for (ClientHandler mc : Server.ar) {

                    if (mc.name.equals(recipient) && mc.isloggedin) {
                        mc.dos.writeUTF(this.name+" : "+MsgToSend);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {

            this.dis.close();
            this.dos.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
