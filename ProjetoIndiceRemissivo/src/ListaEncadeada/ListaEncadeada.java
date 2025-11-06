package ListaEncadeada;

public class ListaEncadeada<T> {

    private static class Nodo<T> {
        public T elemento;
        public Nodo<T> proximo;

        public Nodo(T elemento) {
            this.elemento = elemento;
            this.proximo = null;
        }
    }

    private Nodo<T> primeiro;
    private Nodo<T> ultimo;
    private int n_elementos;

    public ListaEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.n_elementos = 0;
    }

    // Método usado para adicionar a ocorrência na classe Palavra
    public void insereFinal(T elemento) {
        Nodo<T> novoNodo = new Nodo<>(elemento);
        if (ultimo == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            ultimo.proximo = novoNodo;
            ultimo = novoNodo;
        }
        n_elementos++;
    }

    // Método usado para evitar duplicatas de linha na classe Palavra
    public boolean contem(T elemento) {
        Nodo<T> atual = primeiro;
        while (atual != null) {
            if (atual.elemento.equals(elemento)) return true;
            atual = atual.proximo;
        }
        return false;
    }

    /**
     * Retorna a lista de ocorrências separadas por espaço (Ex: "4 5 6")
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> atual = primeiro;
        while (atual != null) {
            sb.append(atual.elemento);
            // Adiciona um ESPAÇO se não for o último elemento.
            if (atual.proximo != null) sb.append(" ");
            atual = atual.proximo;
        }
        return sb.toString();
    }
}