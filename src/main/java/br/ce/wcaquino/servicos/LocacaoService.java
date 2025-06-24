package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
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

	// Princípio FIRST
	// FAST -> Teste deve ser executado rápido
	// INDEPENDENT -> Não deve depender de outro teste
	// REPEATABLE -> Pode ser executado quando e quantas vezes quiser
	// SELF-VERIFYING -> Auto verificável, deve saber quando o teste funcionou e quando falhou
	// TIMELY -> "Oportuno", deve ser criado no momento correto
	public static void main(String[] args) {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario X");
		Filme filme = new Filme("Filme Z", 1, 10.0);
		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao
		System.out.println(locacao.getValor() == 10.0);
		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

	}
}