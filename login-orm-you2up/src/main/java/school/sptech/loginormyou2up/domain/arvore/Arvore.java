package school.sptech.loginormyou2up.domain.arvore;

public class Arvore {
    private Node raiz;

    public Arvore() {
        raiz = null;
    }

    public Node getRaiz() {
        return raiz;
    }

    public void insere(int info) {
        if (raiz == null) {
            raiz = new Node(info);
        } else {
            Node noDaVez = raiz;
            while (noDaVez != null && noDaVez.getInfo() != info) {
                Node noNovo = new Node(info);
                if (info <= noDaVez.getInfo()) {
                    if (noDaVez.getEsq() == null) {
                        noDaVez.setEsq(noNovo);
                    }
                    noDaVez = noDaVez.getEsq();
                } else {
                    if (noDaVez.getDir() == null) {
                        noDaVez.setDir(noNovo);
                    }
                    noDaVez = noDaVez.getDir();
                }
            }
        }
    }

    public Node busca(int info) {
        Node noDaVez = raiz;
        while (noDaVez != null && noDaVez.getInfo() != info) {
            if (info < noDaVez.getInfo()) {
                noDaVez = noDaVez.getEsq();
            } else {
                noDaVez = noDaVez.getDir();
            }
        }
        return noDaVez;
    }

    public void preOrdem(Node no) {
        if (no != null) {
            System.out.print(no.getInfo() + " ");
            preOrdem(no.getEsq());
            preOrdem(no.getDir());
        }
    }

    public void emOrdem(Node no) {
        if (no != null) {
            emOrdem(no.getEsq());
            System.out.print(no.getInfo() + " ");
            emOrdem(no.getDir());
        }
    }

    public void posOrdem(Node no) {
        if (no != null) {
            posOrdem(no.getEsq());
            posOrdem(no.getDir());
            System.out.print(no.getInfo() + " ");
        }
    }
}
