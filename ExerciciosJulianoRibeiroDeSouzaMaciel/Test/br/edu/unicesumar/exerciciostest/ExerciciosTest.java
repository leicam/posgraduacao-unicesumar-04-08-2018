package br.edu.unicesumar.exerciciostest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.edu.unicesumar.exercicios.Exercicios;

public class ExerciciosTest {
	Integer totalLinhas = 0;
	
	@Test
	public void exercicioUmTest(){
		totalLinhas = 10;
		
		Exercicios ex = new Exercicios();
		
		assertEquals(totalLinhas, ex.getTotalLinhasArquivo("./Resources/arquivoTeste.txt"));
	}
	
	@Test
	public void exercicioDoisTest(){		
		Exercicios ex = new Exercicios();
		
		ex.gerarBackupArquivo("./Resources/arquivoTeste.txt", "./Resources/Backup/arquivoTesteBackup.txt");
		
		assertEquals(ex.getTotalLinhasArquivo("./Resources/arquivoTeste.txt"), ex.getTotalLinhasArquivo("./Resources/Backup/arquivoTesteBackup.txt"));		
	}
	
	@Test
	public void exercicioTresTest(){
		totalLinhas = 1;
		
		Exercicios ex = new Exercicios();
		
		ex.gerarArquivoComFiltro("./Resources/arquivoTeste.txt", "./Resources/arquivoTesteFiltro.txt", "2003");
		
		assertEquals(totalLinhas, ex.getTotalLinhasArquivo("./Resources/arquivoTesteFiltro.txt"));		
	}
	
	@Test
	public void exercicioQuatroTest() throws Exception{
		Exercicios ex = new Exercicios();
		
		ex.carregarRegistros("./Resources/arquivoTeste.txt", "2003");		
	}
}
