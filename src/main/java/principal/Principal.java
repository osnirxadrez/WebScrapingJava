package principal;

import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Noticia noticia = new Noticia();
		 String urlPrincipal = "https://www.infomoney.com.br/mercados/";
		 
		 try {					 
			   List<String> urlsUltimasNoticiasMercados = new ArrayList<>();
			   //Chamada do m�todo para fazer os POSTs na pagina para que ela retorne novas noticias, 3 vezes, 1 para cada pagina
	            urlsUltimasNoticiasMercados.addAll(Noticia.getUrlsUltimasNoticiasMercados(1, urlPrincipal));
	            urlsUltimasNoticiasMercados.addAll(Noticia.getUrlsUltimasNoticiasMercados(2, urlPrincipal));
	            urlsUltimasNoticiasMercados.addAll(Noticia.getUrlsUltimasNoticiasMercados(3, urlPrincipal));

	            System.out.println("�ltimas Not�cias");
	            
	            //For para chamar o m�todo que retira as informa��es de cada URL e imprimir no console
	            for (String url : urlsUltimasNoticiasMercados) {
	                noticia.retiraInformacoes(url);
	                System.out.println(noticia.toString());
	            }
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
