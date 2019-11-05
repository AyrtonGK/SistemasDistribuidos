package arquivo;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ArquivoServer {

	@WebMethod
	void criaArquivo(String nomeArquivo);

	@WebMethod
	void escreveNoArquivo(String nomeArquivo, String texto);
	
	String leArquivo(String nome);
}