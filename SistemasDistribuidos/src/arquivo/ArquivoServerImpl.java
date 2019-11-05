package arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.jws.WebService;

@WebService(endpointInterface = "arquivo.ArquivoServer")
public class ArquivoServerImpl implements ArquivoServer {

	public void criaArquivo(String nomeArquivo) {
		try {
			// Cria arquivo
            File file = new File(nomeArquivo+".txt");

            // Se o arquivo nao existir, ele gera
            if (!file.exists()) {
                file.createNewFile();
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void escreveNoArquivo(String nomeArquivo, String texto) {
		File arquivo = new File(nomeArquivo + ".txt");
		BufferedWriter buf;
		try {
			buf = new BufferedWriter(new FileWriter(arquivo));
			buf.write(texto);
			buf.flush();
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String leArquivo(String nome) {
		String texto="";
		try {
			FileReader ler = new FileReader(nome+".txt");
            BufferedReader reader = new BufferedReader(ler);  
            String linha;
            while( (linha = reader.readLine()) != null ){
                System.out.println(linha);
                texto.concat(linha);
            }
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		return texto;
	}
}
