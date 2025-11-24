import java.text.Normalizer;

public class TabelaHash {

    private ArvoreBinariaBusca[] vetor;
    private int tamanho;


    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.vetor = new ArvoreBinariaBusca[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = new ArvoreBinariaBusca();
        }
    }


    private int funcaoHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return -1;
        }

        char c = palavra.charAt(0);

        String normalizada = Normalizer.normalize(String.valueOf(c), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        if (normalizada.isEmpty()) {
            return -1;
        }

        char primeiraLetra = Character.toLowerCase(normalizada.charAt(0));

        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        }

        return -1;
    }

    public void inserirOuAtualizar(String texto, int linha) {
        int indice = funcaoHash(texto);

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

    public Palavra buscar(String texto) {
        int indice = funcaoHash(texto);
        if (indice >= 0 && indice < tamanho) {
            return vetor[indice].buscar(texto);
        }
        return null;
    }

    public void imprimirIndiceRemissivo() {
        for (int i = 0; i < tamanho; i++) {
            vetor[i].imprimirEmOrdem();
        }
    }

    public String gerarIndiceComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append("--- ").append((char) ('A' + i)).append(" ---\n");
            sb.append(vetor[i].toString()).append("\n");
        }
        return sb.toString();
    }
}