package arquivo;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

class ArquivoClient {

	public static void main(String args[]) throws Exception {
		String nomeArquivo,texto;
		URL url = new URL("http://127.0.0.1:9876/arquivo?wsdl");
		QName qname = new QName("http://arquivo/", "ArquivoServerImplService");
		Service ws = Service.create(url, qname);
		ArquivoServer arquivo = ws.getPort(ArquivoServer.class);
		System.out.println("Digite o nome do arquivo");
		Scanner sc = new Scanner(System.in);
		nomeArquivo = sc.next();
		System.out.println("Arquivo a ser criado" + nomeArquivo);
		arquivo.criaArquivo(nomeArquivo);
		arquivo.escreveNoArquivo(nomeArquivo, "teste dentro do arquivo4");
		texto = arquivo.leArquivo(nomeArquivo);
		System.out.println("O texto do arquivo " + texto);
	}
}