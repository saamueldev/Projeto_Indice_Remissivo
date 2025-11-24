package ListaEncadeada;

public class Palavra implements Comparable<Palavra> {

    private String texto;
    private ListaEncadeada<Integer> ocorrencias;

    public Palavra(String texto) {
        // Armazena em minúsculo, conforme o processamento do LeitorArquivo
        this.texto = texto.toLowerCase();
        this.ocorrencias = new ListaEncadeada<Integer>();
    }

    // Adiciona uma nova linha de ocorrência, evitando duplicatas
    public void adicionarOcorrencia(int linha) {
        if (!ocorrencias.contem(linha)) {
            // Assume que sua ListaEncadeada usa insereFinal ou um método semelhante
            ocorrencias.insereFinal(linha);
        }
    }

    public String getTexto() {
        return texto;
    }

    public ListaEncadeada<Integer> getOcorrencias() {
        return ocorrencias;
    }

    // Usado pela ABB para ordenação alfabética
    @Override
    public int compareTo(Palavra outra) {
        return this.texto.compareTo(outra.texto);
    }

    /**
     * Gera a saída formatada do índice remissivo (Ex: and 4 5 6).
     * Depende do toString() da ListaEncadeada retornar os números separados por espaço.
     */
    @Override
    public String toString() {
        return texto + " " + ocorrencias.toString();
    }
}