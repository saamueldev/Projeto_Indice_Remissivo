package Hash;

import ArvoreBinaria.ArvoreBinariaBusca;
import ListaEncadeada.Palavra;
import java.text.Normalizer; // *** IMPORTAÇÃO NECESSÁRIA ***

public class TabelaHash {

    private ArvoreBinariaBusca[] vetor;
    private int tamanho;

    // ---------- CONSTRUTOR ----------
    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.vetor = new ArvoreBinariaBusca[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = new ArvoreBinariaBusca();
        }
    }

    // ---------- FUNÇÃO DE HASH CORRIGIDA PARA ACENTOS ----------
    private int funcaoHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return -1; // Índice inválido
        }

        char c = palavra.charAt(0);

        // 1. Normaliza para remover acentos (Ex: 'á' -> 'a')
        String normalizada = Normalizer.normalize(String.valueOf(c), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        if (normalizada.isEmpty()) {
            return -1;
        }

        // 2. Obtém a primeira letra em minúsculo (agora sem acento)
        char primeiraLetra = Character.toLowerCase(normalizada.charAt(0));

        // 3. Mapeia para o índice (a=0, b=1, ...)
        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        }

        // Retorna -1 para palavras que comecem com números ou símbolos após a normalização
        return -1;
    }

    // ---------- INSERIR / ATUALIZAR ----------
    public void inserirOuAtualizar(String texto, int linha) {
        int indice = funcaoHash(texto);

        // Só processa se o índice for válido (começa com letra)
        if (indice >= 0 && indice < tamanho) {
            ArvoreBinariaBusca abb = vetor[indice];

            Palavra palavraExistente = abb.buscar(texto);
            if (palavraExistente == null) {
                Palavra nova = new Palavra(texto);
                nova.adicionarOcorrencia(linha);
                abb.inserir(nova);
            } else {
                palavraExistente.adicionarOcorrencia(linha);
            }
        }
    }

    // ---------- BUSCA ----------
    public Palavra buscar(String texto) {
        int indice = funcaoHash(texto);
        if (indice >= 0 && indice < tamanho) {
            return vetor[indice].buscar(texto);
        }
        return null;
    }

    // Mantenha ou remova conforme a necessidade do seu LeitorArquivo.java
    public void imprimirIndiceRemissivo() {
        for (int i = 0; i < tamanho; i++) {
            vetor[i].imprimirEmOrdem();
        }
    }

    // Mantenha ou remova conforme a necessidade do seu LeitorArquivo.java
    public String gerarIndiceComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append("--- ").append((char) ('A' + i)).append(" ---\n");
            sb.append(vetor[i].toString()).append("\n");
        }
        return sb.toString();
    }
}