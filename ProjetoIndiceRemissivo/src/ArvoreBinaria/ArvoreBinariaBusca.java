package ArvoreBinaria;

import ListaEncadeada.Palavra;

public class ArvoreBinariaBusca {

    // ---------- CLASSE NODO ----------
    class Nodo {
        public Palavra elemento;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(Palavra elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    // ---------- ATRIBUTOS ----------
    private Nodo raiz;
    private int nElementos;

    // ---------- CONSTRUTOR ----------
    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    // ---------- INSERÇÃO ORDENADA ----------
    public void inserir(Palavra palavra) {
        raiz = inserirRecursivo(raiz, palavra);
    }

    private Nodo inserirRecursivo(Nodo atual, Palavra palavra) {
        if (atual == null) {
            nElementos++;
            return new Nodo(palavra);
        }

        int cmp = palavra.compareTo(atual.elemento);
        if (cmp < 0) {
            atual.esquerdo = inserirRecursivo(atual.esquerdo, palavra);
        } else if (cmp > 0) {
            atual.direito = inserirRecursivo(atual.direito, palavra);
        } else {
            // Se a palavra já existir, não insere nova — só retorna o mesmo nó
            return atual;
        }
        return atual;
    }

    // ---------- BUSCA ----------
    public Palavra buscar(String texto) {
        return buscarRecursivo(raiz, texto.toLowerCase());
    }

    private Palavra buscarRecursivo(Nodo atual, String texto) {
        if (atual == null) return null;

        int cmp = texto.compareTo(atual.elemento.getTexto());
        if (cmp == 0) return atual.elemento;
        else if (cmp < 0) return buscarRecursivo(atual.esquerdo, texto);
        else return buscarRecursivo(atual.direito, texto);
    }

    // ---------- PERCURSO EM ORDEM ----------
    public void imprimirEmOrdem() {
        imprimirEmOrdemRec(raiz);
    }

    private void imprimirEmOrdemRec(Nodo nodo) {
        if (nodo == null) return;
        imprimirEmOrdemRec(nodo.esquerdo);
        System.out.println(nodo.elemento);
        imprimirEmOrdemRec(nodo.direito);
    }

    // ---------- CONTAGEM ----------
    public int tamanho() {
        return nElementos;
    }
}
