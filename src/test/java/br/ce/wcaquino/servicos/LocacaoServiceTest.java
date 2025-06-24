package br.ce.wcaquino.servicos;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;

public class LocacaoServiceTest {

	// Princípio FIRST
	// FAST -> Teste deve ser executado rápido
	// INDEPENDENT -> Não deve depender de outro teste
	// REPEATABLE -> Pode ser executado quando e quantas vezes quiser
	// SELF-VERIFYING -> Auto verificável, deve saber quando o teste funcionou e quando falhou
	// TIMELY -> "Oportuno", deve ser criado no momento correto

	/*
	 	Frameworks do tipo Unit devem definir:
		1- Test Runner -> Quem vai executar os testes e coletar resultados
		2- Test Fixture -> Pré condições necessárias p os testes
		3- Test Suites -> Elencar testes que serão executados
		4- Test Result Formatter -> Organização de resultado
		5- Assertions -> Verifica comportamento ou estado do que está sendo testado
	*/

    @Test
    public void teste() {
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario X");
        Filme filme = new Filme("Filme Z", 1, 10.0);

        Locacao locacao = service.alugarFilme(usuario, filme);

        assertEquals(10.0, locacao.getValor(), 0.01);
        assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));
    }
}
