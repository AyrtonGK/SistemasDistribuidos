package ayrton.socket;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[]) {
        try {
            //Estabelece conex�o com socket servidor (localhost)
            Socket sock = new Socket("127.0.0.1", 6013);
            //Obtem o fluxo de entrada
            //InputStream in = sock.getInputStream();
            //Prepara um objeto de leitura do fluxo
            //BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            //L� a data do socket
            /*String line;
            while ((line = bin.readLine())!= null) {
                System.out.println(line);
            }*/
            ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(sock.getInputStream());
            String msg = "/criaarquivo";
            	output.writeUTF(msg);
            	output.flush();
            	System.out.println("Mensagem:"+ msg +" enviada.");
            	msg = input.readUTF();
            	System.out.println("Resposta: " + msg);
            	
            input.close();
            output.close();
            //Fecha a conex�o com o socket
            sock.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
