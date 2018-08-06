package br.edu.unicesumar.exerciciostest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.edu.unicesumar.exercicios.Exercicios;

public class ExerciciosTest {

	@Test
	public void exercicioUmTest(){
		Integer totalLinhas = 10;
		
		Exercicios ex = new Exercicios();
		
		assertEquals(totalLinhas, ex.getTotalLinhasArquivo(".\\Resources\\arquivoTeste.txt"));
	}
	
	@Test
	public void exercicioDoisTest(){		
		Exercicios ex = new Exercicios();
		
		ex.gerarBackupArquivo(".\\Resources\\arquivoTeste.txt", ".\\Resources\\Backup\\arquivoTesteBackup.txt");
		
		assertEquals(ex.getTotalLinhasArquivo(".\\Resources\\arquivoTeste.txt"), ex.getTotalLinhasArquivo(".\\Resources\\Backup\\arquivoTesteBackup.txt"));		
	}
}
