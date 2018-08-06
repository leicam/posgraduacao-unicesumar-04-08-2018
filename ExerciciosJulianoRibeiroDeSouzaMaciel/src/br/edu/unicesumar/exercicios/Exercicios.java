package br.edu.unicesumar.exercicios;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class Exercicios {

	public void exercicioUm(){
		System.out.println("Total de Linhas: " + getTotalLinhasArquivo(".\\Resources\\arquivoTeste.txt"));
	}
	
	public Integer getTotalLinhasArquivo(String caminhoArquivo){
		Integer contador = 0;
		
		try {
			InputStreamReader reader = new FileReader(caminhoArquivo);
			
			BufferedReader bufferReader = new BufferedReader(reader);
			
			
			while(bufferReader.readLine() != null){
				contador++;
			}
			
			reader.close();
		} catch (IOException ex) {
			System.out.println("Erro: " + ex);
		}
		
		return contador;
	}
	
	public void exercicioDois(){
		gerarBackupArquivo(".\\Resources\\arquivoTeste.txt", ".\\Resources\\Backup\\arquivoTeste.txt");
	}
	
	public void gerarBackupArquivo(String from, String to){
		Path FROM = Paths.get(from);
		Path TO = Paths.get(to);

		CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES };

		try {
			Files.copy(FROM, TO, options);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void exercicioTres() {
		gerarArquivoComFiltro(".\\Resources\\arquivoTeste.txt", ".\\Resources\\arquivoTesteFiltro.txt", "1974");
	}
	
	public void gerarArquivoComFiltro(String caminhoArquivo, String caminhoArquivoFiltro, String filtro){
		Path path = Paths.get(caminhoArquivo);
		
		try {
			PrintStream novoArquivo = new PrintStream(caminhoArquivoFiltro);
			
			try (Scanner scanner = new Scanner(path)) {
				while (scanner.hasNextLine() ) {
					if(scanner.nextLine().contains(filtro)){
						novoArquivo.println(scanner.nextLine());
					}
				}
				
				novoArquivo.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	
	public void exercicioQuatro(){
		filtrarRegistros(".\\Resources\\arquivoTeste.txt", "rb");
		
	}
	
	public void filtrarRegistros(String caminhoArquivo, String filtro) {
		List<String> arquivo = new ArrayList<>();
		
		Path path = Paths.get(caminhoArquivo);
		
		//try {				
			try (Scanner scanner = new Scanner(path)) {
				while (scanner.hasNextLine() ) {
					if(scanner.nextLine().contains(filtro)){
						arquivo.add(scanner.nextLine());
					}
				}
				
				arquivo.stream().forEach(System.out :: println);
			} catch (IOException e) {
				e.printStackTrace();

			}
		//} catch (FileNotFoundException e1) {
		//	e1.printStackTrace();
		//}
	}
	
	public void exercicioCinco(){
		
	}
	
	public void exercicioSeis(){
		
	}
}
