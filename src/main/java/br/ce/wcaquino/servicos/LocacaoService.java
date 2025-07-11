package br.ce.wcaquino.servicos;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

	// colocar os arquivos de teste em test/java... e colocar a mesma estrutura de pacotes,
	// consigo acessar todas as regras da classe, mesmo que tenha protected
	
    public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {
				if(filme == null){
					throw new LocadoraException("Filme vazio");
				}
				if(usuario == null){
					throw new LocadoraException("Usuario vazio");
				}
				if (filme.getEstoque() == 0) {
            throw new FilmeSemEstoqueException();
        }

		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

}