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
	private void trataConexao2(Socket socket)throws IOException{
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			String nomeArquivo;
			String msg = input.readUTF();
			 switch(msg){
			 	case "/CriaArquivo":{
			 		output.writeUTF("ensira o nome do arquivo");
			 		output.flush();
			 		nomeArquivo=input.readUTF();
			 		criaArquivo(nomeArquivo);
			 		output.writeUTF("arquivo Criado");
			 		output.flush();
			 		break;
			 	}
			 	case "/EscreveNoArquivo":{
			 		output.writeUTF("ensira o nome do arquivo");
			 		output.flush();
			 		nomeArquivo = input.readUTF();
			 		output.writeUTF("Digite o texto do arquivo");
			 		output.flush();
			 		String texto = input.readUTF();
			 		escreveNoArquivo(nomeArquivo,texto);
			 		output.writeUTF("escreveu no arquivo");
			 		output.flush();
			 		break;
			 	}
			 	case "/CopiarArquivo":{
			 		output.writeUTF("informe o nome do arquivo");
			 		output.flush();
			 		nomeArquivo = input.readUTF();
			 		copiaArquivo(nomeArquivo);
			 		output.writeUTF("arquivo Copiado");
			 		output.flush();
			 		break;
			 	}
			 	default :{
			 		output.writeUTF("opÁao invalida!");
			 		break;
			 	}
			 }
			 output.close();
			 input.close();
		}catch(IOException e) {
			
		}finally {
			fechaSocket(socket);
		}
	}
	private void criaArquivo(String msg) throws IOException {
		FileWriter arq = new FileWriter(new File(msg+".txt"));
		arq.close();
	}
	private void escreveNoArquivo(String nomeArquivo, String texto) throws IOException {
		File arquivo = new File(nomeArquivo+".txt");
		BufferedWriter buf = new BufferedWriter(new FileWriter(arquivo));
		buf.write(texto);
		buf.flush();
		buf.close();
	}
	
	private void copiaArquivo(String nome) {
		String nomeOrigem = nome + ".txt";
		String nomeDestino = nome + "Copia.txt";
		try {
		FileInputStream fInput = new FileInputStream(nomeOrigem);
		FileOutputStream fOutput = new FileOutputStream(nomeDestino);
		
		byte[] strBuffer = new byte[1024];
		int strLength;
		while((strLength = fInput.read(strBuffer)) > 0) {
			fOutput.write(strBuffer,0,strLength);
		}
		fOutput.close();
		fInput.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
        try {
        	Server server = new Server();
        	server.criaServerSocket(6013);
            System.out.println("Aguardando cliente...");
            //Espera pela conex√£o
            while(true) { 
            	Socket client = server.esperaConexao();
            	server.trataConexao2(client);

                client.close();
            }
        } catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
}
