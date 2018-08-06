package br.edu.unicesumar.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda {
	public static void main(String[] args) {
		List<String> nomes = Arrays.asList("Marcelo", "Juliano", "Yuji", "Wesley", "Frank", "Jeremias", "Fusca");
		
		/*for(Integer x = 0; x < nomes.size(); x++){
			System.out.println(nomes.get(x));
		}
		
		for (String nome : nomes){
			System.out.println(nome);
		}
		*/
		
		//isso é o começo do lambda (linguagem funcional)
		//nomes.forEach(s -> System.out.println(s)); 
		
		//nomes.forEach(System.out :: println);
		
		//nomes.stream().filter(s -> !s.equals("Juliano")).findAny();
		
		//List<String> nomesFiltrados = nomes.stream().filter(s -> !s.equals("Juliano")).collect(Collectors.toList());
		//nomesFiltrados.forEach(System.out :: println);
		
		nomes.stream().filter(s -> !s.equals("Juliano")).collect(Collectors.toList()).forEach(System.out :: println);
		
	}
}
