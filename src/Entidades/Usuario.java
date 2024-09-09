package Entidades;

abstract class Usuario {
	protected String nome;
	protected String documento;
	
	public Usuario() {
		
	}

	public Usuario(String nome, String documento) {
		this.nome = nome;
		this.documento = documento;
	}

	public abstract boolean validarDocumento(String documento);

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	
	
	
}
