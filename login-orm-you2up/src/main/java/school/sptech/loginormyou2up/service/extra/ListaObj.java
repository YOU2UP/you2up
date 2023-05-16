package school.sptech.loginormyou2up.service.extra;

public class ListaObj <T> {
    private T[] vetor;
    private int nroElem; //quantidade de elementos na lista


    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public boolean isFull() {
        return nroElem == vetor.length;
    }

    public void adiciona(T valor) {
        if (isFull()) {
            System.out.println("Lista cheia");
        } else {
            vetor[nroElem++] = valor;
        }
    }

    public void exibe() {
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
    }


    public int busca(T pesquisa) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(pesquisa)) {
                return i;
            }
        }

        return -1;
    }


    public boolean removePeloIndice(int indice) {
        if (indice >= nroElem || indice < 0) {
            return false;
        } else {
            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }

            nroElem--;
            return true;
        }
    }

    public boolean removeElemento(T elemento) {
        return removePeloIndice(busca(elemento));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T[] getVetor() {
        return vetor;
    }

    public void setVetor(T[] vetor) {
        this.vetor = vetor;
    }

    public void setNroElem(int nroElem) {
        this.nroElem = nroElem;
    }

    public T getElemento(int indice) {
        if (indice >= 0 && indice < nroElem) {
            return vetor[indice];
        }

        return null;
    }

    public void limpa() {
        nroElem = 0;
    }

    public boolean removeTodasOcorrencias(T elemento) {
        int qtdOcorrencias = 0;

        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elemento)) {
                qtdOcorrencias++;
            }
        }

        for (int i = 0; i < qtdOcorrencias; i++) {
            removePeloIndice(busca(elemento));
        }

        return qtdOcorrencias > 0;
    }

    public boolean adicionaNoIndice(T elemento, int indice) {
        if(indice >= 0 && indice< vetor.length) {
            if (vetor[indice] == null){
                nroElem++;
            }
            vetor[indice] = elemento;
            return true;
        }
        return false;
    }


}