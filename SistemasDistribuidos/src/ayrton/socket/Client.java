package ayrton.socket;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String args[]) {
		try {
			Client client = new Client();
			Socket sock = new Socket("127.0.0.1", 6013);
			ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(sock.getInputStream());
			
			//client.criaArquivo(input, output);
			//client.copiaOArquivo(input, output);
			//client.escreveNoArquivo(input, output);
			// Finaliza Input e output
			input.close();
			output.close();
			// Fecha a conexão com o socket
			sock.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void criaArquivo(ObjectInputStream input, ObjectOutputStream output) throws IOException {
		// comando enviado para servidor execudar.
		String msg = "/CriaArquivo";
		output.writeUTF(msg);
		output.flush();
		System.out.println("Comando:" + msg );
		// mensagem para cliente informar o nome do arquivo.
		msg = input.readUTF();
		System.out.println("Resposta: " + msg);
		// eh infomradoo nome do arquivo para o servidor.
		Scanner sc = new Scanner(System.in);
		System.out.printf("Ensira o nome do arquivo: ");
		msg = sc.next();
		output.writeUTF(msg);
		output.flush();
		msg = input.readUTF();
		System.out.println("Resposta: " + msg);
		// finaliza scanner do teclado.
		sc.close();

	}

	private void escreveNoArquivo(ObjectInputStream input, ObjectOutputStream output) throws IOException {
		// comando enviado para servidor execudar.
		String msg = "/EscreveNoArquivo";
		output.writeUTF(msg);
		output.flush();
		System.out.println("Enviado:" + msg + " enviada.");
		// mensagem para cliente informar o nome do arquivo.
		msg = input.readUTF();
		System.out.println("Resposta: " + msg);
		// eh infomradoo nome do arquivo para o servidor.
		Scanner sc = new Scanner(System.in);
		System.out.printf("Ensira o nome do arquivo: ");
		msg = sc.next();
		output.writeUTF(msg);
		output.flush();
		// servidor informa para colocar texto para escrever no arquivo.
		msg = input.readUTF();
		System.out.println("Resposta: " + msg);
		// realizado a leitura do texto para enviar para o servidor.
		System.out.printf("Digite o texto para escrever no arquivo: ");
		msg = sc.next();
		output.writeUTF(msg);
		output.flush();
		msg = input.readUTF();
		System.out.println("Resposta: " + msg);
		// finaliza scanner do teclado.
		sc.close();

	}

	private void copiaOArquivo(ObjectInputStream input, ObjectOutputStream output) throws IOException {
		// comando enviado para servidor execudar.
		String msg = "/CopiarArquivo";
		output.writeUTF(msg);
		output.flush();
		System.out.println("Enviado:" + msg + " enviada.");
		// mensagem para cliente informar o nome do arquivo.
		msg = input.readUTF();
		System.out.println("Resposta: " + msg);
		// eh infomradoo nome do arquivo para o servidor.
		Scanner sc = new Scanner(System.in);
		msg = sc.next();
		output.writeUTF(msg);
		output.flush();
		msg = input.readUTF();
		System.out.println("Resposta: " + msg);
		// finaliza scanner do teclado.
		sc.close();

	}
}
