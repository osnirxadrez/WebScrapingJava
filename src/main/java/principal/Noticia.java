package principal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Noticia {
	private String url ;
	private String titulo;
	private String subtitulo;
	private String autor;
	private String dataPublicacao;
	private String conteudo;
	
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
	
	//Métodos
    public String retiraTitulo(Document document, String caminhoTitulo) {
    	Element elementTitulo = document.select(caminhoTitulo).first();
		String titulo = elementTitulo.select("h1").text();
		
		return titulo;
    }
    public String retiraSubTitulo(Document document, String caminhoSubTitulo) {
    	Element elementSubTitulo = document.select(caminhoSubTitulo).first();
		String subTitulo = elementSubTitulo.text();
		
		return subTitulo;
    }
    public String retiraAutor(Document document, String caminhoAutor) {
    	Element elementAutor = document.select(caminhoAutor).first();
		String autor = elementAutor.select("a").text();
		
		return autor;
    }
    public String retiraDataPublicacao(Document document, String caminhoDataPublicacao) {
    	Element elementDataPublicacao = document.select(caminhoDataPublicacao).first();
		String dataPublicacao = elementDataPublicacao.attr("datetime");
		
		return dataPublicacao;
    }
    public String retiraConteudo(Document document, String caminhoConteudo) {
    	Element elementConteudo = document.select(caminhoConteudo).first();
		String conteudo = elementConteudo.select("p").text();
		
		return conteudo;
    }
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + ((dataPublicacao == null) ? 0 : dataPublicacao.hashCode());
		result = prime * result + ((subtitulo == null) ? 0 : subtitulo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	
	
	@Override
	public String toString() {
		return "Noticia [url="+url+", titulo=" + titulo + ", subtitulo=" + subtitulo + ", autor=" + autor
				+ ", dataPublicacao=" + dataPublicacao + ", conteudo=" + conteudo + "]";
	}	
	

	public Noticia() {
		super();
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noticia other = (Noticia) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (conteudo == null) {
			if (other.conteudo != null)
				return false;
		} else if (!conteudo.equals(other.conteudo))
			return false;
		if (dataPublicacao == null) {
			if (other.dataPublicacao != null)
				return false;
		} else if (!dataPublicacao.equals(other.dataPublicacao))
			return false;
		if (subtitulo == null) {
			if (other.subtitulo != null)
				return false;
		} else if (!subtitulo.equals(other.subtitulo))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}	
}
