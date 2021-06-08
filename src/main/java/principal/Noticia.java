package principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Noticia {
	private String url ;
	private String titulo;
	private String subtitulo;
	private String autor;
	private LocalDateTime dataPublicacao;
	private String conteudo;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");	
    
	//*****************MÉTODOS*********************
    
    //Método que retira o titulo da pagina selecionada
    public String retiraTitulo(Document document, String caminhoTitulo) {
    	Element elementTitulo = document.select(caminhoTitulo).first();
		String titulo = elementTitulo.select("h1").text();
		
		return titulo;
    }
    
    //Método que retira o subtitulo da pagina selecionada
    public String retiraSubTitulo(Document document, String caminhoSubTitulo) {
    	Element elementSubTitulo = document.select(caminhoSubTitulo).first();
		String subTitulo = elementSubTitulo.text();
		
		return subTitulo;
    }
    
    //Método que retira o autor da pagina selecionada
    public String retiraAutor(Document document, String caminhoAutor) {
    	Element elementAutor = document.select(caminhoAutor).first();
		String autor = elementAutor.select("a").text();
		
		return autor;
    }
    
    //Método que retira a data da pagina selecioada e prepara para conversão para o formato desejado
    public static LocalDateTime retiraDataPublicacao(Document document, String caminhoDataPublicacao) {
    	Element elementDataPublicacao = document.select(caminhoDataPublicacao).first();
		String dataPublicacao = elementDataPublicacao.attr("datetime");
		return LocalDateTime.parse(dataPublicacao, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
    
    // Método que retira o conteúdo da pagina selecionada
    public String retiraConteudo(Document document, String caminhoConteudo) {
    	Element elementConteudo = document.select(caminhoConteudo).first();
		String conteudo = elementConteudo.select("p").text();
		
		return conteudo;
    }
    
    // Método que faz o POST na pagina para carregar mais noticias
    public static List<String> getUrlsUltimasNoticiasMercados(int pagina, String urlPrincipal) {
        HttpResponse<JsonNode> httpResponse = Unirest
                .post(urlPrincipal + "?infinity=scrolling")
                .field("action", "infinite_scroll")
                .field("page", String.valueOf(pagina))
                .field("order", "DESC")
                .asJson();
        String postflairs = httpResponse.getBody().getObject().get("postflair").toString();
        return List.copyOf(new JSONObject(postflairs).keySet());
    }
    
    // Retira as informações da pagina selecionada
    public void retiraInformacoes(String url){
		this.url = url;
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			this.titulo = retiraTitulo(document, "div[class=col-md-10 col-xl-8 m-auto]");
			this.subtitulo = retiraSubTitulo(document, "p[class=article-lead]");
			this.autor = retiraAutor(document, "span[class='author-name']");
			this.dataPublicacao = retiraDataPublicacao(document, "time");
			this.conteudo = retiraConteudo(document, "div[class=col-md-9 col-lg-8 col-xl-6  m-sm-auto m-lg-0 article-content]");
		} catch (Exception e) {
			
		}
	}
    
    //toString
	@Override
	public String toString() {
		return "Noticia [url="+url+", titulo=" + titulo + ", subtitulo=" + subtitulo + ", autor=" + autor
				+ ", dataPublicacao=" + DATE_TIME_FORMATTER.format(dataPublicacao) + ", conteudo=" + conteudo + "]";
	}
	
	//Construtores
	public Noticia() {
		super();
	}	
	
	public Noticia(String url, String titulo, String subtitulo, String autor, LocalDateTime dataPublicacao,
			String conteudo) {
		super();
		this.url = url;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.autor = autor;
		this.dataPublicacao = dataPublicacao;
		this.conteudo = conteudo;
	}
}
