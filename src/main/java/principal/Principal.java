package principal;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Noticia noticia = new Noticia();
		ArrayList<String> urlNoticias = new ArrayList<String>();
		 String urlPrincipal = "https://www.infomoney.com.br/mercados/";
		 Document document = null;
		 
		 try {			
			document = Jsoup.connect(urlPrincipal).data("action","infinite_scroll")
					.data("page", String.valueOf(document))
					.data("order","DESC")
					.userAgent("Mozilla")
					.post(); 
		//	 document = Jsoup.connect(urlPrincipal).get();
			
			Elements elements = document.select("div[class=row py-3 item]");
			
			for(Element element : elements) {
				urlNoticias.add(element.select("a").attr("href"));
			}
			
			for(String url : urlNoticias) {
				noticia.retiraInformacoes(url);
				System.out.println(noticia);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
