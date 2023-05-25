package school.sptech.loginormyou2up.service.extra;


public class PilhaObj<T> {
    private T[] vetor;
    private int topo;

    public PilhaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.topo = -1;
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo >= vetor.length;
    }

    public void push(T info){
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia");
        } else {
            vetor[++topo] = info;
        }
    }

    public T pop(){
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        } else {
            return vetor[topo--];
        }
    }

    public T peek(){
        if (isEmpty()){
            throw new IllegalStateException("Pilha vazia");
        } else {
            return vetor[topo];
        }
    }

    public int getTopo() {
        return topo;
    }

}
