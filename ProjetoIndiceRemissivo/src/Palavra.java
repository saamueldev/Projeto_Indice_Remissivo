public class Palavra implements Comparable<Palavra> {

    private String texto;
    private ListaEncadeada<Integer> ocorrencias;

    public Palavra(String texto) {
        this.texto = texto.toLowerCase();
        this.ocorrencias = new ListaEncadeada<Integer>();
    }

    public void adicionarOcorrencia(int linha) {
        if (!ocorrencias.contem(linha)) {
            ocorrencias.insereFinal(linha);
        }
    }

    public String getTexto() {
        return texto;
    }

    public ListaEncadeada<Integer> getOcorrencias() {
        return ocorrencias;
    }


    @Override
    public int compareTo(Palavra outra) {
        return this.texto.compareTo(outra.texto);
    }


    @Override
    public String toString() {
        return texto + " " + ocorrencias.toString();
    }
}