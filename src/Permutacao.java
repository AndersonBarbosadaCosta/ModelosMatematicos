import java.util.Arrays;

public class Permutacao {

	// numero de permutacoes
	private static int count = 0;

	// armazena a permutacao corrente
	private static char permuta_corrente[];

	// metodo principal que recebe o vetor cujos elementos serao permutados
	// @param vetor
	public static void permuta(char[] vetor) {
		permuta_corrente = new char[vetor.length];
		permuta(vetor, 0);
	}

	// metodo recursivo que implementa as permutacoes
	// @param vetor e n
	public static void permuta(char[] vetor, int n) {
		if (n == vetor.length) {
			count++;
			imprime();
		} else {
			for (int i = 0; i < vetor.length; i++) {

				permuta_corrente[n] = vetor[i];
				permuta(vetor, n + 1);
			}

		}
	}

	// imprime a permutacao corrente
	private static void imprime() {
		System.out.println();
		System.out.print("(" + count + ") : ");
		for (int i = 0; i < permuta_corrente.length; i++)
			System.out.print(permuta_corrente[i] + " ");
	}

	private static char[] transformaStringChar(String palavra) {

		// metodo que remove os espaços de uma string
		palavra = palavra.replace(" ", "");
		return palavra.toCharArray();
	}

	//metodo recursivo que implementa as permutacoes
	//@param vetor e n
	public static void permutaSemRepeticao(char[]vetor,int n) {
		if(n == vetor.length){
			count++;
			imprime();
		}else{
			for(int i=0; i<vetor.length;i++) {
				boolean achou = false;
				for(int j=0;j<n;j++) {
					if(permuta_corrente[j]==vetor[i]) achou=true;
				}
				if(!achou) {
					permuta_corrente[n]= vetor[i];
					permutaSemRepeticao(vetor,n+1);
				}
			}
		}
	}
	
	
	
	/**
	 * Metodo recebe duas Strings, elimina os espaços,compara se tem o mesmo
	 * comprimento para entao transforma-las em um vetor de char, ordena-las e
	 * comparar se sao iguais confirmando assim uma ser permutacao da outra
	 **/

	private static boolean ePermutacao(String palavraUm, String palavraDois) {

		if (palavraUm.replace(" ", "").length() == palavraDois.replace(" ", "").length()) {

			char[] palavra = transformaStringChar(palavraUm);
			char[] palavra2 = transformaStringChar(palavraDois);
			// Ordena os dois vetores de char
			Arrays.sort(palavra);
			Arrays.sort(palavra2);

			if (Arrays.equals(palavra, palavra2)) {
				System.out.println("É Permutação");
				return true;
			} else {
				System.out.println("Não é Permutação ");
				return false;
			}
		} else {
			return false;
		}

	}

}
