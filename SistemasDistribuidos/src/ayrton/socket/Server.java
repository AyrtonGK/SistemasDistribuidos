package ayrton.socket;
import java.net.*;
import java.sql.SQLException;

import java.io.*;

public class Server {

	private ServerSocket sSock;
	private void criaServerSocket(int porta) throws IOException {
		sSock = new ServerSocket(porta);
	}
	private Socket esperaConexao() throws IOException{
		Socket sock = sSock.accept();
		return sock;
	}
	private void fechaSocket(Socket sock) throws IOException {
		sock.close();
	}
	private void trataConexao(Socket socket) throws IOException  {
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			
			String msg = input.readUTF();
			System.out.println("Mensagem recebida.");
			if(msg.contains("/criaarquivo")) {
				output.writeUTF("Arquivo Criado");
				output.flush();
        	}else {
			output.writeUTF("Hello World!");
			output.flush();
        	}
			input.close();
			output.close();
			
		}catch(IOException e) {

		}finally {
			fechaSocket(socket);
		}
	}
	
	private void filtro(String mensagem) {
		String str = mensagem;
		for(int i=0 i<)
		if(str.contains("/criaArquivo")) {
			
		}
	}
    public static void main(String[] args) {
        try {
        	Server server = new Server();
        	server.criaServerSocket(6013);
            System.out.println("Aguardando cliente...");
            //Espera pela conexão
            while(true) { 
                //Accept bloqueia o processo até que receba nova conexão
            	Socket client = server.esperaConexao();
            	//Socket client = sock.accept();
            	
            	//Prepara um objeto para escrever pelo socket
            	server.trataConexao(client);
	//	PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                //Escreve a data através do socket
      //         pout.println(new java.util.Date().toString());
            
           //    System.out.println("Data enviada para " + client.getInetAddress());
                //Fecha o socket e volta a esperar outras conexões
                client.close();
            }
        } catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
}
