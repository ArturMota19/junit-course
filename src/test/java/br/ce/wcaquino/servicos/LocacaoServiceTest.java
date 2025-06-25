package br.ce.wcaquino.servicos;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
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

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector(); // Coleta erros sem interromper os testes

    @Rule
    public ExpectedException exception = ExpectedException.none();

    // o before pode ser usado para evitar repetição de código, vai ser executado sempre antes dos testes!
    @Before
    public void setup(){
        System.out.print("Before");
        service = new LocacaoService();
    }

    @After
    public void tearDown(){
        System.out.print("After");
    }

    // existe também o beforeClass e o afterClass, que se referem as classes
    @BeforeClass
    public static void setupClass(){
        System.out.print("Before Class");
    }

    @AfterClass
    public static void tearDownClass(){
        System.out.print("After Class");
    }

    @Test
    public void testeLocacao() throws Exception { // o throws Exception será gerenciado pelo JUnit
        // cenário -> Pré condições
        Usuario usuario = new Usuario("Usuario X");
        Filme filme = new Filme("Filme Z", 1, 10.0);

        // Ação -> O que vai ser testado
        Locacao locacao = service.alugarFilme(usuario, filme);
        
        // Verificação -> O que deve acontecer
        // AssertThat -> Verifique Que...
        // Deve ser genérico, para que possamos fazer qualquer teste
        // Fluid Interface
        error.checkThat(locacao.getValor(), is(10.0));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    // "Elegante"
    @Test(expected=FilmeSemEstoqueException.class) // o exception não aparece nesse caso pois o JUnit já trata isso
    public void testeLocacao_filmeSemEstoque() throws Exception{
        // cenário -> Pré condições
        Usuario usuario = new Usuario("Usuario X");
        Filme filme = new Filme("Filme Z", 0, 10.0);

        // Ação -> O que vai ser testado
        service.alugarFilme(usuario, filme);
    }

    // "Robusta" -> oferece mais controle sobre o tratamento
    @Test
    public void testeLocacao_usuarioVazio() throws Exception{
        // cenário -> Pré condições
        //Usuario usuario = new Usuario("Usuario X");
        Filme filme = new Filme("Filme Z", 1, 10.0);

        // Ação -> O que vai ser testado
        try {
            service.alugarFilme(null, filme);
            Assert.fail("Deveria lançar uma exceção");
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    // Caso de "Espera"
    @Test 
    public void testeLocacao_filmeVazio() throws LocadoraException, FilmeSemEstoqueException{
        // cenário -> Pré condições
        Usuario usuario = new Usuario("Usuario X");
        
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        // Ação -> O que vai ser testado
        service.alugarFilme(usuario, null);
    }
}

/*
 * Em resumo, a forma elegante funciona bem quando garante-se que a exceção é lançada apenas naquele motivo
 * A robusta é melhor quando se quer ter maior controle (Ex: printar pós try catch)
 * Em grande parte das vezes, a forma de espera funciona bem. Contudo, recomenda-se que se utilize a Robusta.
 */