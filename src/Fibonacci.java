import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Fibonacci {

	private Integer maior;

	private ArrayList<Integer> numeros_arquivo;

	private ArrayList<Integer> listaFibonacci;

	public Integer getMaior() {
		return maior;
	}

	public void setMaior(Integer maior) {
		this.maior = maior;
	}

	public ArrayList<Integer> getNumeros() {
		return numeros_arquivo;
	}

	public void setNumeros(ArrayList<Integer> numeros) {
		this.numeros_arquivo = numeros;
	}

	public ArrayList<Integer> getListaFibonacci() {
		return listaFibonacci;
	}

	public void setListaFibonacci(ArrayList<Integer> listaFibonacci) {
		this.listaFibonacci = listaFibonacci;
	}

	//metodo que retorna o valor da sequencia contido na posicao passada por parametro
			public static long fibo(int n) {
		        int F = 0;     // atual
		        int ant = 0;   // anterior
		  
		        for (int i = 1; i <= n; i++) {
		  
		            if (i == 1) {
		                F = 1;
		                ant = 0;
		            } else {
		                F += ant;
		                ant = F - ant;
		            }
		  
		        }
		  
		        return F;
		    }
			
	
	/*
	 * Funcao que remove todas as palavras do texto deixando apenas numeros_arquivo
	 */
	public String removeCaracteres(String palavra) {

		return palavra.replaceAll("[^0-9]", " ").trim();
	}

	/**
	 * Metodo que cria a sequencia de Fibonacci até o valor passado como parâmetro a
	 * partir do terceiro elemento da sequência.
	 **/
	public List<Integer> sequenciaFibonacci(int maximo) {

		listaFibonacci = new ArrayList<>();
		listaFibonacci.add(0);
		listaFibonacci.add(1);

		int i = 1;
		while (listaFibonacci.get(i) + listaFibonacci.get(i - 1) <= maximo) {
			// Pega o valor contido no indice atual + anterior
			Integer valor = listaFibonacci.get(i) + listaFibonacci.get(i - 1);
			listaFibonacci.add(valor);
			i++;
		}
		return listaFibonacci;
	}

	/**
	 * Metodo que retorna um conjunto de elementos contidos na lista de Fibonacci e
	 * no arquivo texto. Sao criados dois conjuntos 'A' e 'B'. 'A' armazena os
	 * elementos gerados no metodo sequenciaFibonacci() atraves do metodo //addAll()
	 * da classe de conjuntos. 'B' armazena todos os elementos retirados do arquivo
	 * texto. Apos é realizada a operacao de intersecao de A e B retornando o novo
	 * conjunto.
	 **/

	public Set<Integer> intersecaoArquivoFibonacci() {

		Set<Integer> conjuntoA = new TreeSet<Integer>();
		conjuntoA.addAll(listaFibonacci);
		conjuntoA.retainAll(numeros_arquivo);

		return conjuntoA;
	}

	/**
	 * Metodo que retorna 'B' - 'A'. Lista de elementos que estao no arquivo e nao
	 * na sequencia de Fibonacci. Recece como parametros a lista de Fibonacci e o
	 * arquivo texto. Percorre o conjunto e adiciona em uma lista os valores
	 * diferentes.
	 **/
	public List<Integer> arquivoMenosFibonacci() {

		Set<Integer> conjuntoD = new HashSet<>();
		conjuntoD.addAll(listaFibonacci);

		conjuntoD.retainAll(numeros_arquivo);

		ArrayList<Integer> lista = new ArrayList<>();
		for (int i = 0; i < numeros_arquivo.size(); i++) {
			if (!conjuntoD.contains(numeros_arquivo.get(i).intValue())) {
				lista.add(numeros_arquivo.get(i).intValue());
			}
		}
		Set<Integer> conjuntoOrdenado = new TreeSet<Integer>(lista);
		ArrayList<Integer> ordenado = new ArrayList<>();
		ordenado.addAll(conjuntoOrdenado);

		return ordenado;
	}

	/**
	 * Metodo que retorna a lista Final 'B'- 'A' U 'C' . Lista de elementos
	 * presentes na sequencia de Fibonacci e no arquivo mais o restante dos
	 * numeros_arquivo contidos no arquivo(não pertencentes a sequencia) na ordem em
	 * que aparecem no arquivo.
	 **/
	public List<Integer> arquivoFinal(Set<Integer> intersecaoAB, List<Integer> arquivoMenosFibonacci) {

		ArrayList<Integer> listaFinal = new ArrayList<>();
		listaFinal.addAll(intersecaoAB);
		listaFinal.addAll(arquivoMenosFibonacci);
		return listaFinal;
	}

	/** Metodo que mostra todas as informações referentes a classe **/
	public void mostrar() {
		System.out.println("Numeros retirados do arquivo: " + numeros_arquivo);
		System.out.println("Maior Valor do Arquivo: " + maior);
		System.out.println("Sequencia de Fibonacci gerada até valor: " + sequenciaFibonacci(maior));
		System.out.println("Intersecao de A - B: " + intersecaoArquivoFibonacci());
		System.out.println("Intersecao de B -A: " + arquivoMenosFibonacci());
		System.out.println("Arquivo Final: " + arquivoFinal(intersecaoArquivoFibonacci(), arquivoMenosFibonacci()));
	}

	/**
	 * Metodo responsavel por fazer a leitura do arquivo, armazenar cada linha do
	 * arquivo em uma String, remover todos os caracteres diferentes de
	 * numeros_arquivo, transformar o texto em numeros_arquivo,e pegar o maior valor
	 * contido no texto para estabelecer o limite que será gerada a sequencia de
	 * fibonacci.
	 **/

	public void realizaOperacao() throws IOException {

		/* Leitura do arquivo */
		InputStream input = new FileInputStream("teste.txt");
		InputStreamReader leitorStream = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(leitorStream);

		/* Cada linha do arquivo lido e armazenado na String texto */
		String textoConverter = reader.readLine();

		/* Vetor que armazena os numeros_arquivo Inteiros */
		numeros_arquivo = new ArrayList<>();
		maior = 0;
		while (textoConverter != null) {
			textoConverter = removeCaracteres(textoConverter);
			String[] result = textoConverter.split(" ");
			for (int i = 0; i < result.length; i++) {
				numeros_arquivo.add(Integer.parseInt(result[i]));

				if (numeros_arquivo.get(i) > maior)
					maior = numeros_arquivo.get(i);
			}

			textoConverter = reader.readLine();

		}
		mostrar();
		reader.close();

	}

	public static void main(String[] args) throws IOException {

		Fibonacci fibonacci = new Fibonacci();
		fibonacci.realizaOperacao();

	}

}
