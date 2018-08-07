package br.edu.unicesumar.exercicios;

//import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.ObjectOutput;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exercicios {

	public void exercicioUm(){
		System.out.println("Total de Linhas: " + getTotalLinhasArquivo("./Resources/arquivoTeste.txt"));
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
		gerarBackupArquivo("./Resources/arquivoTeste.txt", "./Resources/Backup/arquivoTeste.txt");
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
		gerarArquivoComFiltro("./Resources/arquivoTeste.txt", "./Resources/arquivoTesteFiltro.txt", "1974");
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
	
	public void exercicioQuatro() throws Exception{
		carregarRegistros("./Resources/arquivoTeste.txt", "rb");
	}
	
	public void carregarRegistros(String caminhoArquivo, String filtro) throws Exception {
		List<String> arquivo = getRegistros(caminhoArquivo, filtro);
		
		arquivo.forEach(System.out :: println);
	}
	
	public List<String> getRegistros(String caminhoArquivo, String filtro) throws Exception{
		List<String> arquivo = new ArrayList<String>();
		
		Path path = Paths.get(caminhoArquivo);
		
		try {
			Scanner scanner = new Scanner(path);
			
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				
				if(!linha.contains(filtro))
					continue;
					
				arquivo.add(linha);
			}
			
			scanner.close();
		} catch (Exception ex) {
			throw new Exception(ex);
		}

		return arquivo;
	}
	
	public void exercicioCinco() throws Exception{
		carregarPessoasPorDataNascimentoDecrescente("./Resources/arquivoTeste.txt");
	}
	
	public void carregarPessoasPorDataNascimentoDecrescente(String caminhoArquivo) throws Exception {
		List<Pessoa> pessoas = getPessoasOrderByDataNascimentoDecrescente(caminhoArquivo);
		
		pessoas.forEach(p -> System.out.println(p.getFirstName() + " " + p.getBirthYear()));
	}
	
	public List<Pessoa> getPessoasOrderByDataNascimentoDecrescente(String caminhoArquivo) throws Exception {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		pessoas = returnPessoas(caminhoArquivo);		
		pessoas.sort(Comparator.comparing(Pessoa::getBirthYear));
		
		return pessoas;
	}
	
	private List<Pessoa> returnPessoas(String caminhoArquivo) throws Exception {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Path path = Paths.get(caminhoArquivo);
		
		try {
			Scanner scanner = new Scanner(path);
			
			while(scanner.hasNextLine()){
				String linha = scanner.nextLine();
				if(linha.contains("ID"))
					continue;
				
				String[] registro = linha.trim().split(",");			
				pessoas.add(new Pessoa(registro[0], registro[1], registro[2], registro[3], Integer.parseInt(registro[4]), Integer.parseInt(registro[5])));				
			}
			
			scanner.close();
		} catch (Exception ex) {
			throw new Exception(ex);
		}

		return pessoas.stream().filter(distinctByKey(b -> b.getId())).collect(Collectors.toList());
	}
	
	public void exercicioSeis() throws Exception{
		carregarPessoasComNome("Adams");
	}
	
	public void carregarPessoasComNome(String filtro) throws Exception {
		List<Pessoa> pessoas = getPessoasComNome("./Resources/arquivoTeste.txt", filtro);
		Optional<Pessoa> pessoa = encontrarPessoaMaisNova(pessoas);
		
		System.out.println(pessoa);
	}
	
	public Optional<Pessoa> encontrarPessoaMaisNova(List<Pessoa> pessoas){
		pessoas.sort(Comparator.comparing(Pessoa::getBirthYear));
		return pessoas.stream().findFirst();
	}
	
	public List<Pessoa> getPessoasComNome(String caminhoArquivo, String filtro) throws Exception {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		pessoas = returnPessoas(caminhoArquivo);		
		pessoas = pessoas.stream().filter(x-> x.getLastName().equals(filtro)).collect(Collectors.toList());
		
		return pessoas;		
	}
	

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
