package Hash;

public class TabelaHash<T> {

    public T vetor[];
    public int nElementos;

    public TabelaHash(int capacidade) {
        this.vetor = (T[]) new Object[capacidade];
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public void imprime() {
        System.out.println("Chave\tValor");
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(i + " -->\t[ " + vetor[i] + " ]");
        }
    }

    private int funcaoHashDiv(T elemento) {
        int hashCode = elemento.hashCode();
        int chave = hashCode % this.vetor.length;
        return chave;
    }

    public void insere(T elemento) {
        int chave = funcaoHashDiv(elemento);
        this.vetor[chave] = elemento;
        this.nElementos++;
    }

    public T remove(T elemento) {
        int chave = funcaoHashDiv(elemento);

        T removido = this.vetor[chave];

        this.vetor[chave] = null;
        this.nElementos--;

        return removido;
    }

    public T busca(T elemento) {

        int chave = funcaoHashDiv(elemento);

        return this.vetor[chave];

    }

    public boolean contem(T elemento) {
        int chave = funcaoHashDiv(elemento);
        return this.vetor[chave] != null;
    }
}




