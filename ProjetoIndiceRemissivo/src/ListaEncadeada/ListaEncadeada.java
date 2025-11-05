package ListaEncadeada;

import java.util.NoSuchElementException;

public class ListaEncadeada {
    private static class Nodo {
        public int elemento;
        public Nodo proximo;

        public Nodo(int elemento) {
            this.elemento = elemento;
            this.proximo = null;
        }
    }

    private Nodo primeiro;
    private Nodo ultimo;
    private int n_elementos;

    public ListaEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.n_elementos = 0;
    }

    public void insereInicio(int elemento) {
        Nodo novoNodo = new Nodo(elemento);
        if (primeiro == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            novoNodo.proximo = primeiro;
            primeiro = novoNodo;
        }
        n_elementos++;
    }

    public void removeInicio() {
        if (primeiro == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            primeiro = primeiro.proximo;
            n_elementos--;
            if (n_elementos == 0) {
                ultimo = null;
            }
        }
    }

    public void insereFinal(int elemento) {
        Nodo novoNodo = new Nodo(elemento);
        if (ultimo == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            ultimo.proximo = novoNodo;
            ultimo = novoNodo;
        }
        n_elementos++;
    }

    public void removeFinal() {
        if (ultimo == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            if (primeiro == ultimo) {
                primeiro = null;
                ultimo = null;
            } else {
                Nodo atual = primeiro;
                while (atual.proximo != ultimo) {
                    atual = atual.proximo;
                }
                atual.proximo = null;
                ultimo = atual;
            }
            n_elementos--;
        }
    }

    public void inserePosicao(int elemento, int posicao) {
        if (posicao < 0 || posicao > n_elementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            insereInicio(elemento);
        } else if (posicao == n_elementos) {
            insereFinal(elemento);
        } else {
            Nodo novoNodo = new Nodo(elemento);
            Nodo atual = primeiro;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.proximo;
            }
            novoNodo.proximo = atual.proximo;
            atual.proximo = novoNodo;
            n_elementos++;
        }
    }

    public void removePosicao(int posicao) {
        if (posicao < 0 || posicao >= n_elementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        if (posicao == 0) {
            removeInicio();
        } else if (posicao == n_elementos - 1) {
            removeFinal();
        } else {
            Nodo atual = primeiro;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.proximo;
            }
            atual.proximo = atual.proximo.proximo;
            n_elementos--;
        }
    }

    public void imprimirLista() {
        Nodo atual = primeiro;
        System.out.print("Lista: ");
        while (atual != null) {
            System.out.print(atual.elemento + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}
