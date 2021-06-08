package principal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Noticia noticia = new Noticia();
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
				String url = element.select("a").attr("href");
				noticia.retiraInformacoes(url);
				System.out.println(noticia);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
