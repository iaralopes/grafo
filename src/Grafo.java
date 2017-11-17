import java.util.LinkedList;
import java.util.Queue;

/*
 * Autor: Iara Lopes
 * 
 */

public class Grafo {
	static int[][] matriz_adjacencia;
	static int[][] matriz_pesos;
	static int quant_vertices;
	
   /*
    * Objetivo: retornar quantas arestas o grafo possui
    * 
    * O metodo percorre a matriz de adjacencias em busca de celulas
    * com valor >= 1, cada vez que acha contabiliza + 1 na quantidade
    * de arestas.
    */
	public static int contaArestas() {
		
		int quant_adjacencias = 0;
		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1) {
					if (!ehDigrafo()) {
					if (i < j) {
					 quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
					} 
					}
					else {
					 quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
					}
				}
			}
		}
		return quant_adjacencias;

	}
	
	/*
	 * Objetivo: retornar quantos vertices pendentes o grafo possui 
	 * 
	 * O metodo percorre a matriz contando o valor do grau de cada vertice,
	 * aqueles que possuirem grau igual a 1 eh considerado como pendente. 
	 */
	public static int contaPendentes() {
		int quant_pendentes = 0;
		int quant_adjacencias = 0;
		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1 && i != j) {
					quant_adjacencias++;
				}
			}
			if (quant_adjacencias == 1) {
				quant_pendentes++;
			}
			quant_adjacencias = 0;
		}

		return quant_pendentes;
	}
	
	/*
	 * Objetivo: retornar quantos vertices nulos o grafo possui 
	 * 
	 * O metodo percorre a matriz contando o valor do grau de cada vertice,
	 * aqueles que possuirem grau igual a 0 eh considerado como nulo. 
	 */
	public static int contaNulos() {
		int quant_nulos = 0;
		int quant_adjacencias = 0;
		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
				}
			}
			if (quant_adjacencias == 0) {
				quant_nulos++;
			}
			quant_adjacencias = 0;
		}

		return quant_nulos;
	}
	
	/*
	 * Objetivo: retornar o grau de um determinado vertice
	 * 
	 * Parametro: vertice a ser analisado para retornar o seu grau
	 * 
	 * O metodo percorre a matriz testando se os valores das celulas
	 * sao iguais ou maiores que 1, caso verdadeiro, os valores sao 
	 * somados.
	 */
	public static int contaGrau(int vertice) {
		int quant_adjacencias = 0;
		for (int j = 1; j < quant_vertices + 1; j++) {
			if (matriz_adjacencia[vertice][j] >= 1) {
				quant_adjacencias = quant_adjacencias + matriz_adjacencia[vertice][j];
			}
		}

		return quant_adjacencias;
	}
	
	
	/*
	 * Objetivo: retornar quantos componentes existem
	 * 
	 * O metodo utiliza a busca em profundidade para contar
	 * quantos componentes existem.
	 */
	public static int contaComponentes() {
		int [] vertices_visitados = new int [quant_vertices];
		int componentes = 0;
		
		for (int x = 0; x < quant_vertices; x++) {
			if (vertices_visitados[x] == 0) {
				componentes++;
				aux_contaComponentes(x, vertices_visitados);
			}
		}
		
		return componentes;
	}
	
	/*
	 * Objetivo: auxiliar na contagem de componentes existentes no grafo
	 * 
	 * Parametro: vertice especifico e array de vertices a serem visitados
	 * 
	 * O metodo percorre de forma recursiva o grafo, ou seja, usa-se a
	 * busca em profundidade.
	 */
	public static void aux_contaComponentes(int vertice, int [] vertices_visitados) {
		vertices_visitados[vertice] = 1;
		for (int x = 0; x < quant_vertices; x++) {
				if (matriz_adjacencia[vertice + 1][x + 1] == 1 && vertices_visitados[x] == 0) {
					aux_contaComponentes(x, vertices_visitados);
				}
		}
		
		vertices_visitados[vertice] = 2;
	}
	
	
	/*
	 * Objetivo: percorrer os vertices em profundidade 
	 * 
	 * O metodo se inicia em um vertice e vai tracando a busca por
	 * outros vertices ligados, ate chegar em um vertice que nao tem
	 * mais nenhum outro vertice a ser percorrido. Apos isso, o caminho
	 * vai sendo definitivamente percorrido de forma contraria.
	 */
	public static void BFS () {
		int [] vertices_visitados = new int [quant_vertices];
		
		for (int x = 0; x < quant_vertices; x++) {
			if (vertices_visitados[x] == 0) {
				vertices_visitados[x] = 1;
				Queue<Integer> vertices_para_visitar = new LinkedList<Integer>();
				vertices_para_visitar.add(x);
				
				while (!vertices_para_visitar.isEmpty()) {
					int vertice_atual = vertices_para_visitar.remove();
					
					for (int y = 0; y < quant_vertices; y++) {
						if (matriz_adjacencia[vertice_atual + 1][y + 1] == 1 && vertices_visitados[y] == 0) {
							vertices_visitados[y] = 1;
							vertices_para_visitar.add(y);
						}
					}
					
					vertices_visitados[vertice_atual] = 2;
					System.out.printf("%d ",vertice_atual + 1);
				}
				
			}
		}
		
			
	}
	
	/*
	 * Objetivo: percorrer os vertices em largura 
	 * 
	 * O metodo se inicia em um vertice e a partir dele vai visitando
	 * outros vertices que estao ligados a ele, ate nao haver mais 
	 * nenhum vertice no grafo.
	 */	
	public static void DFS () {
		int [] vertices_visitados = new int [quant_vertices];
		
		for (int x = 0; x < quant_vertices; x++) {
			if (vertices_visitados[x] == 0) {
				percorrer(x, vertices_visitados);
				
			}
		}

	}
	
	/*
	 * Objetivo: auxiliar o metodo de busca em largura
	 * 
	 * Parametro: vertice especifico e array de vertices a serem visitados
	 * 
	 * A funcao auxilia na recursividade do metodo DFS.
	 */
	public static void percorrer (int vertice, int [] vertices_visitados) {
		vertices_visitados[vertice] = 1;
		for (int x = 0; x < quant_vertices; x++) {
				if (matriz_adjacencia[vertice + 1][x + 1] == 1 && vertices_visitados[x] == 0) {
					percorrer(x, vertices_visitados);
				}
		}
		
		vertices_visitados[vertice] = 2;
		System.out.printf("%d ", vertice + 1);
	}
	

	/*
	 * Objetivo: verificar se o grafo nao possui arestas paralelas e nem auto loops
	 * 
	 * O metodo percorre a matriz procurando celulas com valores maiores que 1 e 
	 * valores iguais a 1 em celulas com o valor da linha e coluna identicos,
	 * caso encontre, informa que o grafo nao eh simples. 
	 */
	public static boolean ehSimples() {
		boolean ehSimples = true;

		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] > 1) {
					ehSimples = false;
				}

				if (i == j && matriz_adjacencia[i][j] == 1) {
					ehSimples = false;
				}
			}
		}

		return ehSimples;
	}

	/*
	 * Objetivo: verificar se todos os vertices do grafo possuem o mesmo grau
	 * 
	 * O metodo compara todos os valores dos graus de cada vertice em busca de 
	 * uma igualdade total. 
	 */
	public static boolean ehRegular() {
		boolean ehRegular = true;
		int quant_adjacencias = 0;
		int[] listaGraus = new int[quant_vertices];

		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
				}

			}
			listaGraus[i - 1] = quant_adjacencias;
			quant_adjacencias = 0;
		}

		for (int x = 1; x < quant_vertices; x++) {
			if (listaGraus[x - 1] == listaGraus[x]) {
				ehRegular = ehRegular && true;
			} else {
				ehRegular = ehRegular && false;
			}
		}

		return ehRegular;
	}
	
	/*
	 * Objetivo: verificar se o grafo eh nulo, ou seja, possui quantidade de arestas = 0
	 * 
	 * O metodo percorre a matriz procurando celulas com valores iguais ou maiores que 1,
	 * com a proposta de soma-los, caso encontre. Caso contrário, se essa soma permanecer 
	 * igual a zero, o grafo eh considerado nulo.
	 */
	public static boolean ehNulo() {
		boolean ehNulo = true;
		int quant_adjacencias = 0;

		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
				}
			}
		}

		if (quant_adjacencias == 0) {
			ehNulo = true;
		} else {
			ehNulo = false;
		}

		return ehNulo;
	}
	
	/*
	 * Objetivo: verificar se todos os vertices do grafo sao interligados
	 * 
	 * O metodo percorre a matriz procurando celulas com valores iguais ou maiores
	 * que 1, o resultado da soma desses valores eh comparado com a quantidade de 
	 * arestas que o grafo precisaria ter para ser completo.
	 */
	public static boolean ehCompleto() {
		boolean ehCompleto = true;
		int quant_adjacencias = 0;

		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
				}
			}
		}

		int maximo = ((quant_vertices - 1) * (quant_vertices - 1 + 1)) / 2;
		if (maximo == (quant_adjacencias / 2)) {
			ehCompleto = true;
		} else {
			ehCompleto = false;
		}
		return ehCompleto;
	}
	
	/*
	 * Objetivo: verificar se o grafo pode ser percorrido visitando cada aresta
	 * apenas 1 vez
	 * 
	 * O metodo compara os graus de todos os vertices do grafo para ver se possuem
	 * valores pares, caso afirmativo, ele eh considerado euleriano.
	 */
	public static boolean ehEuleriano () {
		boolean ehEuleriano = true;
		for (int i = 1; i <= quant_vertices; i++) {
	  	if ((contaGrau(i) % 2) != 0) {
			ehEuleriano = false;
		}
		}

		return ehEuleriano;
	}
	
	/*
	 * Objetivo: verificar se o grafo possui exatamente dois vertices de grau impar
	 * 
	 * O metodo compara os graus de todos os vertices do grafo para ver se existem
	 * valores impares, caso afirmativo, eles sao contabilizados e comparados no 
	 * final, checando se o resultado foi igual a dois.
	 */
	public static boolean ehUnicursal () {
		boolean ehUnicursal = false;
		int contaVertices = 0;
		for (int i = 1; i <= quant_vertices; i++) {
	  	if ((contaGrau(i) % 2) != 0) {
			contaVertices++;
		}
		}
		
		if(contaVertices == 2) {
			ehUnicursal = true;
		}

		return ehUnicursal;
	}
	
	/*
	 * Objetivo: verificar se os vertices do grafo podem ser divididos em dois
	 * conjuntos distintos
	 * 
	 * O metodo testa se os vertices adjacentes no grafo podem participar, cada um,
	 * de dois conjuntos distintos, sem que hajam vertices adjacentes no mesmo. 
	 */
	public static boolean ehBipartido() {
		boolean ehBipartido = true;
		int [] vertices_visitados = new int [quant_vertices];
		
		vertices_visitados[0] = 1;
		Queue<Integer> vertices_para_visitar = new LinkedList<Integer>();
		vertices_para_visitar.add(0);
		
		while (!vertices_para_visitar.isEmpty()) {
			int vertice_atual = vertices_para_visitar.remove();
			   
			   for (int x = 0; x < quant_vertices; x++) {
				   if (matriz_adjacencia[vertice_atual + 1][x + 1] == 1) {
					   if (vertices_visitados[x] == 0) {
						   vertices_visitados[x] = vertices_visitados[vertice_atual] == 1 ? 2 : 1;
						   vertices_para_visitar.add(x);
					   } else if (vertices_visitados[x] == vertices_visitados[vertice_atual]) {
						   ehBipartido = false;
					   }
				   }
			   }
		}
		
		return ehBipartido;
		
	}
	
	/*
	 * Objetivo: verificar se o grafo eh uma arvore
	 * 
	 * O metodo verifica se o grafo eh conexo testando de a quantidade
	 * de componentes eh igual 1 , se possui a quantidade de 
	 * arestas = (quantidade de vertices - 1), e se eh simples.
	 * Se todos os testes forem verdadeiros, ele eh considerado 
	 * uma arvore. 
	 */
	public static boolean ehArvore() {
		boolean ehArvore = false;
		  if (contaComponentes() == 1 && contaArestas() == (quant_vertices - 1) && ehSimples()) {
			  ehArvore = true;
		  }
		return ehArvore;
	}
	
	/*
	 * Objetivo: imprimir o grafo de entrada em forma de matriz de adjacencia
	 * 
	 * O metodo percorre toda a matriz de adjacencia gerada a partir da entrada.
	 */
	public static void imprimeMatriz() {
		int i;
		
		System.out.print("\n      ");
		for (i = 1; i <= quant_vertices; i++) {
				System.out.print(i + "   ");
		}
		System.out.print("\n    ");
		
		for (i = 0; i < quant_vertices * 4; i++)
			System.out.print("-");
		System.out.print("-\n");
		
		for (i = 1; i < quant_vertices + 1; i++) {
			System.out.print(i + " |");
			for (int j = 1; j < quant_vertices + 1; j++) {
				System.out.print("   " + matriz_adjacencia[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * Objetivo: imprimir uma matriz contendo os pesos das arestas
	 * 
	 * O metodo percorre toda a matriz gerada a partir dos pesos
	 * informados na entrada.
	 */
	public static void imprimeMatrizPesos() {
		for (int i = 0; i < quant_vertices + 1; i++) {
			for (int j = 0; j < quant_vertices + 1; j++) {
				System.out.print(matriz_pesos[i][j] + "   ");
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * Objetivo: imprimir uma matriz de adjacencias representando o inverso do 
	 * grafo de entrada
	 * 
	 * O metodo percorre toda a matriz de adjacencias invertendo os valores
	 * originais, exceto celulas que possuem linhas e colunas com valores identicos.
	 */
	public static void imprimeMatrizComplementar() {
		int i;
		
		System.out.print("\n      ");
		for (i = 1; i <= quant_vertices; i++) {
				System.out.print(i + "   ");
		}
		System.out.print("\n    ");
		
		for (i = 0; i < quant_vertices * 4; i++)
			System.out.print("-");
		System.out.print("-\n");
		
		for (i = 1; i < quant_vertices + 1; i++) {
			System.out.print(i + " |");
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (i != 0 && j != 0 && i != j) {
					if (matriz_adjacencia[i][j] >= 1) {
						System.out.print("   " + "0");
					} else {
						System.out.print("   " + "1");
					}
				} else if (i == j && matriz_adjacencia[i][j] >= 1) {
					System.out.print("   " + "0");
				} else {
					System.out.print("   " + matriz_adjacencia[i][j]);
				}
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * Objetivo: imprimir os indices dos "vizinhos" de um determinado vertice 
	 * 
	 * Parametro: vertice a ser analisado para retornar os seus vizinhos
	 * 
	 * O metodo percorre a matriz buscando as adjacencias existentes na
	 * linha que representa o vertice a ser analisado, dessa forma,
	 * toda vez que a celula possuir o valor >= 1, o indice da coluna
	 * eh impresso. 
	 */
	public static void imprimeAdjacentes(int vertice) {
		for (int j = 1; j < quant_vertices + 1; j++) {
			if (matriz_adjacencia[vertice][j] >= 1) {
				System.out.print(j + " ");
			}
		}
	}
	
	/*
	 * Objetivo: verificar se o grafo eh dirigido 
	 * 
	 * O metodo percorre a matriz de adjacencias buscando arestas que
	 * possuem apenas uma direcao, caso encontre pelo menos uma, o grafo
	 * eh considerado como dirigido. 
	 */
	public static boolean ehDigrafo () {
		boolean ehDigrafo = false;
		for (int i = 1; i < quant_vertices + 1; i++) {
		for (int j = 1; j < quant_vertices + 1; j++) {
			if (matriz_adjacencia[i][j] >= 1) {
				if (matriz_adjacencia[j][i] < 1) {
					ehDigrafo = true;
				}
			}
		}
	}
		
		return ehDigrafo;
	}
	
	/*
	 * Objetivo: verificar se o digrafo possui todos vertices com o mesmo grau
	 * 
	 * O metodo percorre a matriz de adjacencias somando o grau de entrada ao
	 * grau de saida de cada vertice, apos isso, compara todos os resultados
	 * para ver se sao iguais. 
	 */
	public static boolean ehDigrafoRegular () {
		boolean ehDigrafoRegular = false;
		boolean ehRegular = true;
		int quant_adjacencias = 0;
		int[] listaGraus = new int[quant_vertices];

		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
				}
				if (matriz_adjacencia[j][i] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[j][i];
				}

			}
			listaGraus[i - 1] = quant_adjacencias;
			quant_adjacencias = 0;
		}

		for (int x = 1; x < quant_vertices; x++) {
			if (listaGraus[x - 1] == listaGraus[x]) {
				ehRegular = ehRegular && true;
			} else {
				ehRegular = ehRegular && false;
			}
		}
		
		if (!ehDigrafo()) {
			ehDigrafoRegular = false;
		}
		
		return ehDigrafoRegular;
	}
	
	/*
	 * Objetivo: verificar se o grau de entrada do vertice eh igual ao grau de saida
	 * 
	 * O metodo percorre a matriz de adjacencias somando o grau de entrada e o
	 * grau de saida de cada vertice, apos isso, as duas somas sao comparadas em busca
	 * de igualdade.
	 */
	public static boolean ehDigrafoBalanceado () {
		int quant_entrada = 0;
		int quant_saida = 0;
		boolean ehDigrafoBalanceado = true;
		for (int v = 1; v < quant_vertices + 1; v++) {
			for (int s = 1; s < quant_vertices + 1; s++) {
				if (matriz_adjacencia[v][s] >= 1) {
					quant_saida = quant_saida + matriz_adjacencia[v][s];
				}
			}
			
			for (int e = 1; e < quant_vertices + 1; e++) {
				if (matriz_adjacencia[e][v] >= 1) {
					quant_entrada = quant_entrada + matriz_adjacencia[e][v];
				}
			}
			
			if (quant_saida == quant_entrada) {
				ehDigrafoBalanceado = ehDigrafoBalanceado && true;
			} else {
				ehDigrafoBalanceado = ehDigrafoBalanceado && false;
			}
		}
		
		
		if (!ehDigrafo()) {
			ehDigrafoBalanceado = false;
		}

		return ehDigrafoBalanceado;
		
	}
	
	/*
	 * Objetivo: verificar se o digrafo possui todos vertices com grau par
	 * 
	 * O metodo percorre a matriz de adjacencias somando o grau de entrada ao
	 * grau de saida de cada vertice, apos isso, compara todos os resultados
	 * para ver se sao pares.
	 */
	public static boolean ehDigrafoEuleriano () {
	  boolean ehDigrafoEuleriano = true;
	  int quant_adjacencias = 0;
	  
		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_adjacencia[i][j] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[i][j];
				}
				if (matriz_adjacencia[j][i] >= 1) {
					quant_adjacencias = quant_adjacencias + matriz_adjacencia[j][i];
				}
			}
			if ((quant_adjacencias % 2) != 0) {
				ehDigrafoEuleriano = ehDigrafoEuleriano && false;
			}
			
			quant_adjacencias = 0;
		}
		
		if (!ehDigrafo()) {
			ehDigrafoEuleriano = false;
		}
		  
	  return ehDigrafoEuleriano;
	}	
	
	/*
	 * Objetivo: auxiliar os metodos dijkstra e prim
	 * 
	 * Parametro: array com os pesos para ir ate cada vertice e array
	 * para checar se vertices foram visitados ou nao
	 * 
	 * Complexidade: O(n) onde n eh a quantidade de vertices
	 * 
	 * O metodo busca o vertice que possui valor de peso minimo. 
	 */
	public static int menorPeso(int [] pesos, boolean[] foiVisitado) {
		int menor = Integer.MAX_VALUE;
		int vertice = -1;
		for (int x = 0; x < quant_vertices; x++) {
			if (foiVisitado[x] == false && pesos[x] < menor) {
				menor = pesos[x];
				vertice = x;
			}

		}
		return vertice;
	}
	
	/*
	 * Objetivo: retornar a menor distancia para chegar em cada vertice
	 * 
	 * Complexidade: O(n^2) onde n eh a quantidade de vertices
	 * 
	 * O metodo considera um conjunto de menores caminhos, iniciando com
	 * um vertice inicial e a partir disso, percorre esse conjunto buscando
	 * nas adjacencias dos seus vertices, aquele com menor distancia ao inicial.
	 */
    public static void dijkstra() {
		int [] pesos = new int[quant_vertices];
		boolean [] foiVisitado = new boolean[quant_vertices];
		int infinito = Integer.MAX_VALUE;

		for (int x = 0; x < quant_vertices; x++) {
			pesos[x] = infinito; 
		}

		pesos[0] = 0; 

		for (int i = 0; i < quant_vertices; i++) {
			int v = menorPeso(pesos, foiVisitado);
			foiVisitado[v] = true; 
			for (int j = 0; j < quant_vertices; j++) {
				if (foiVisitado[j] == false && matriz_pesos[v + 1][j + 1] > 0 && pesos[v] != infinito
					&& pesos[v] + matriz_pesos[v + 1][j + 1] < pesos[j]) {
					
					pesos[j] = pesos[v] + matriz_pesos[v + 1][j + 1];
					
				}
			}
		}
		
		for (int x = 0; x < quant_vertices; x++) {
			System.out.println("Custo do caminho ate o vertice " + (x + 1) +  " = " + pesos[x]);
		}
	}
    
	/*
	 * Objetivo: retornar a ordem que os vertices sao inseridos na AGM utilizando Prim
	 * 
	 * Complexidade: O(n^2) onde n eh a quantidade de vertices
	 * 
	 * O metodo implementa um algoritmo guloso onde visa encontrar um subgrafo em que a
	 * soma total das arestas eh minimizada e todos os vertices estao interligados.
	 */
	 public static void prim() {
		int [] pesos = new int[quant_vertices];
		boolean [] foiVisitado = new boolean[quant_vertices];
		Queue<Integer> AGM = new LinkedList<Integer>();
		
		int infinito = Integer.MAX_VALUE;
		
		for (int x = 0; x < quant_vertices; x++) {
			pesos[x] = infinito;
			foiVisitado[x] = false;
		}
		
		pesos[0] = 0;
		for (int i = 0; i < quant_vertices; i++) {
			int v = menorPeso(pesos, foiVisitado);
			foiVisitado[v] = true;
			AGM.add(v + 1);
			for (int j = 0; j < quant_vertices; j++) {
				if (matriz_pesos[v + 1][j + 1] > 0 && foiVisitado[j] == false && matriz_pesos[v + 1][j + 1] < pesos[j]) {
					pesos[j] = matriz_pesos[v + 1][j + 1];

				}
			}

		}
		
		System.out.print("AGM: " + AGM);

	}
	

	/*
	 * Objetivo: retornar a ordem que os vertices sao inseridos na AGM utilizando Kruskal
	 * 
	 * Complexidade: O(n^2) onde n eh a quantidade de vertices
	 * 
	 * O metodo implementa um algoritmo guloso onde busca gerar uma AGM incluindo de forma
	 * crescente vertices que possuem o menor peso nas suas adjacencias, ate quando todos
	 * os vertices forem visitados. 
     */
	public static void kruskal() {
		Queue<Integer> vertices_para_visitar = new LinkedList<Integer>();
		Queue<Integer> AGM = new LinkedList<Integer>();
		int contador = 1;
		
		for (int x = 1; x <= quant_vertices; x++) {
			vertices_para_visitar.add(x);
		}
		
		while (!vertices_para_visitar.isEmpty()) {
		for (int i = 1; i < quant_vertices + 1; i++) {
			for (int j = 1; j < quant_vertices + 1; j++) {
				if (matriz_pesos[i][j] == contador) {
					if (vertices_para_visitar.contains(i) == true) {
					vertices_para_visitar.remove(i);
					if (AGM.contains(i) == false) {
						AGM.add(i);
					}
					if (AGM.contains(j) == false) {
						AGM.add(j);
					}
			//		System.out.print(i + " - " + j + " \n");
					}
				}
			}
		}
		contador++;
		
	 }
		System.out.println("AGM: " + AGM);
	}
	 

    
	
	
	/*
	 * Objetivo: adicionar adjacencias na matriz de adjacencias
	 * 
	 * Parametro: vertice da direita, vertice da esquerda e peso da aresta que liga elas
	 * 
	 * O metodo acrescenta 1 na celula que representa a adjacencia entre os dois vertices
	 * informados e > 1 se estiver sendo adicionado uma aresta paralela.
	 */
	public static void addAresta(int v_esq, int v_dir, int peso) {
		if (matriz_adjacencia[v_esq][v_dir] >= 1) {
			matriz_adjacencia[v_esq][v_dir] = matriz_adjacencia[v_esq][v_dir] + 1;
		} else {
			matriz_adjacencia[v_esq][v_dir] = 1;
		}
		
		matriz_pesos[v_esq][v_dir] = peso;
	}
	
	/*
	 * Objetivo: contruir a matriz de adjacencias
	 * 
	 * O metodo cria a matriz de adjacencias e a de pesos a partir
	 * da quantidade de vertices.
	 */
	public static void inicializaMatriz() {
		matriz_adjacencia = new int[quant_vertices + 1][quant_vertices + 1];
		matriz_pesos = new int[quant_vertices + 1][quant_vertices + 1];

		for (int j = 0; j < quant_vertices + 1; j++) {
			matriz_adjacencia[0][j] = j;
			matriz_pesos[0][j] = j;
		}

		for (int i = 1; i < quant_vertices + 1; i++) {
			matriz_adjacencia[i][0] = i;
			matriz_pesos[i][0] = i;
		}

	}
	

	public static void main(String[] args) {
		/*
		 * Para utilizar o algoritmo eh preciso seguir dois passos:
		 * 
		 * 1. Atualize o valor da variavel "quant_vertices" para a quantidade de 
		 * vertices do seu grafo.
		 * 
		 * 2. Insira as arestas junto com o seu peso. 
		 * 
		 * Exemplo para cada aresta: 
		 * addAresta([insira o primeiro vertice], [insira o segundo vertice], [peso]);
		 * 
		 * REGRA 01: o valor chave dos vertices sao contados a partir do valor 1,
		 * por exemplo, se o grafo tem 5 vertices, seus vertices serao representados por:
		 * [1, 2, 3, 4, 5].
		 * 
		 * REGRA 02: em caso de grafos nao dirigidos, a aresta devera ser inserida duas
		 * vezes, uma vez representando o direcionamento da direita para a esquerda e
		 * outra vez representando o direcionamento da esquerda para direita. Por exemplo,
		 * caso exista uma aresta que liga o vertice 1 e 2, a representacao sera feita 
		 * da seguinte forma:
		 * addAresta(1, 2, 3);
		 * addAresta(2, 1, 3);
		 */
		
		quant_vertices = 7; //informe a quantidade de vertices aqui
		inicializaMatriz();
		
//      CRIE ABAIXO O SEU NOVO GRAFO
//      OBS: nao se esqueca de deixar o grafo padrao comentado
		


//      GRAFO PADRAO PARA TESTES (para 1 componente - 7 vertices / para 2 componentes - 8 vertices)
		addAresta(1, 2, 3);
		addAresta(2, 1, 3);
		
		addAresta(1, 6, 6);
		addAresta(6, 1, 6);
	
		addAresta(2, 3, 2);
		addAresta(3, 2, 2);
		
		addAresta(2, 6, 5);
		addAresta(6, 2, 5);
		
		addAresta(3, 4, 1);
		addAresta(4, 3, 1);
		
		addAresta(3, 5, 7);
		addAresta(5, 3, 7);
		
		addAresta(4, 5, 8);
		addAresta(5, 4, 8);
		
		addAresta(4, 7, 9);
		addAresta(7, 4, 9);
		
		addAresta(5, 6, 4);
		addAresta(6, 5, 4);
		

		imprimeMatriz();

		System.out.println("\n\n\nPARTE 1");
		System.out.println("1- Numero de vertices e numero de arestas:");
		
		System.out.println("O grafo possui " + quant_vertices + " vertices e " + contaArestas() + " arestas.");
		
		System.out.println("\n2- Numero de vertices pendentes e isolados:");
		
		System.out.println("O grafo possui " + contaPendentes() + " vertice(s) pendente(s).");
		System.out.println("O grafo possui " + contaNulos() + " vertice(s) isolado(s).");
		
		System.out.println("\n3- Numero de componentes:");
		System.out.println("O grafo possui " + contaComponentes() + " componente(s).");
		
		System.out.println("\n4- Grau dos vertices e adjacentes:");
		for (int x = 0; x < quant_vertices; x++) {
			System.out.println("O vertice " + (x+1) + " possui grau " + contaGrau(x+1));
			System.out.print("Os adjacentes ao vertice " + (x+1) + " sao: ");
			imprimeAdjacentes(x+1);
			System.out.println("\n");
		}
		
		System.out.println("\n5- Simples, regular, nulo, completo, euleriano e unicursal:");
		System.out.println("O grafo eh simples? " + ehSimples());
		System.out.println("O grafo eh regular? " + ehRegular());
		System.out.println("O grafo eh nulo? " + ehNulo());
		System.out.println("O grafo eh completo? " + ehCompleto());
	    System.out.println("O grafo eh euleriano? " + ehEuleriano()); 
	    System.out.println("O grafo eh unicursal? "+ ehUnicursal()); 
		
	    System.out.println("\n6- Grafo complementar:");
	    imprimeMatrizComplementar();
	    
	    System.out.println("\n7- O grafo eh bipartido? " + ehBipartido());
	    
	    System.out.println("\n\n\nPARTE 2");
		System.out.println("\n1- Digrafo regular, balanceado, fortemente conexo e euleriano:");
		if (ehDigrafo() == true) {
		System.out.println("O digrafo eh regular? " + ehDigrafoRegular());
		System.out.println("O digrafo eh balanceado? " + ehDigrafoBalanceado());
	    System.out.println("O grafo eh euleriano? " + ehDigrafoEuleriano()); 
		}
		else {
			System.out.println("O grafo de entrada nao eh um digrafo.");
		}
	    
		System.out.println("\n2- Busca em profundidade:");
		DFS();
		
		System.out.println("\n\n3- Busca em largura:");
		BFS();
		
		System.out.println("\n\n4- Dijkstra em Digrafo:");
		if (ehDigrafo() == true) {
		dijkstra();
		}
		else {
        System.out.println("O grafo de entrada nao eh um digrafo.");
		}

	    System.out.println("\n\n\nPARTE 3");
		System.out.println("\n1- O grafo eh uma arvore? " + ehArvore());
		System.out.println("\n2 - Algoritmo de Prim: ");
		if (contaComponentes() <= 1) {
		prim();
		}
		else { System.out.println("\nO grafo de entrada eh um digrafo."); }
		System.out.println("\n\n3 - Algoritmo de Kruskal: ");
		if (contaComponentes() <= 1) {
		kruskal();
		}
		else { System.out.println("\nO grafo de entrada eh um digrafo."); }
			
	}

}
