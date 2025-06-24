package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
  @Test
  public void test(){
    // Boolean testes
    Assert.assertTrue(true);
    Assert.assertFalse(false);

    // Comparacoes
    Assert.assertEquals(1,1);
    Assert.assertEquals(2.1,2.1, 0.01); // o delta quer dizer que pega 2 casas decimais
    Assert.assertEquals(Math.PI, 3.14, 0.01); // to pegando só as 2 casas

    // Tipos primitivos
    int i = 5;
    Integer i2 = 5;
      // apesar do java lidar com ambos (tipo primitivo e objeto) o junit não possui
      // então, para testar basta converter um deles.
    Assert.assertEquals(Integer.valueOf(i), i2);
    Assert.assertEquals(i, i2.intValue());
    // mesma lógica para outros tipos primitivos
    // para string, a assertiva é case sensitive
    Assert.assertEquals("teste", "teste");
    Assert.assertTrue("teste".equalsIgnoreCase("Teste"));
    Assert.assertTrue("teste".startsWith("te"));

    // Objetos
    Usuario u1 = new Usuario("Usuario 1");
    Usuario u2 = new Usuario("Usuario 1");
      // a comparação padrão do equals só verifica se é a mesma instância do objeto
      // para verificar outro equals, é necessário definir um equals no Objeto
    Assert.assertEquals(u1, u2); // vejo se possuem o mesmo nome, equals implementado na classe Usuario
    Assert.assertSame(u1, u1); // verifica se é a mesma instância
    Assert.assertNotSame(u1, u2); // verifica se não é a mesma instância

    // OBS: as assertivas também possuem negativas
    // É possível também colocar uma string no inicío de cada assertiva, que será exibida caso o teste falhe
    Assert.assertEquals("Teste de comparação", u1, u2);
    // OBS2: Atenção às ordens dos parâmetros de cada assertiva, tem o esperado e o obtido
  }


}
