package br.ce.wcaquino.entidades;

public class Usuario {

	private String nome;
	
	public Usuario() {}
	
	public Usuario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Usuario usuario = (Usuario) obj;
		if(nome == null){
			if(usuario.nome != null) return false;
		}
		else if(!nome.equals(usuario.nome)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return nome == null ? 0 : nome.hashCode();
	}
}