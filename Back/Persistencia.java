package Back;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Cria arquivo xml
 * @author Eduarda
 * @since jullho 2021
 * @version 1.0
 */

public class Persistencia {
	
	/**
	 * O atributo xstream instancia um objeto XStream e um DomDriver
	 */
	private static XStream xstream = new XStream(new DomDriver("ISO8859-1"));
	
	/**
	 * O atributo arquivo representa a instancia de uma class file que obtem
	 *  informações de arquivos e diretórios
	 */
	private static File arquivo;
	
	/**
	 * 
	 * @param c é o salão que será salvo
	 * @param nomeDoArquivo passa uma string que vai nomear o nome do arquivo xml
	 * @throws Exception pode aparecer caso o arquivo xml do salão não consiga ser salvo  ou criado
	 */
	
	public static void salvarCentral(Salao c, String nomeDoArquivo) throws Exception {
		
		arquivo = new File(nomeDoArquivo + ".xml");
		
		String xml = xstream.toXML(c);
		
		try {
			
			arquivo.createNewFile();
			PrintWriter ar = new PrintWriter(arquivo);
			ar.print(xml);
			ar.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param nomeDoArquivo representa o nome do xml do salão a ser recuperado
	 * @return o retorno desse metodo é o salão já existente ou novo
	 * @throws Exception pode ser lançada caso não ocorra algum imprevisto caso
	 * ao tentar verificar se o arquivo existe
	 */

	public static Salao recuperarCentra(String nomeDoArquivo) throws Exception {
		
		arquivo = new File(nomeDoArquivo + ".xml");
		
		try {
			
			if (arquivo.exists()) {
				
				FileInputStream insert = new FileInputStream(arquivo);
				return (Salao) xstream.fromXML(insert);
				
			}
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
			
		Salao d = new Salao();
		salvarCentral(d,nomeDoArquivo);
		return d;
	}

}
