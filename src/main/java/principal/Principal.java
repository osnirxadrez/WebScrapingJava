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
	            urlsUltimasNoticiasMercados.addAll(Noticia.getUrlsUltimasNoticiasMercados(1, urlPrincipal));
	            urlsUltimasNoticiasMercados.addAll(Noticia.getUrlsUltimasNoticiasMercados(2, urlPrincipal));
	            urlsUltimasNoticiasMercados.addAll(Noticia.getUrlsUltimasNoticiasMercados(3, urlPrincipal));

	            System.out.println("Últimas Notícias");
	            for (String url : urlsUltimasNoticiasMercados) {
	                noticia.retiraInformacoes(url);
	                System.out.println(noticia.toString());
	            }
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
