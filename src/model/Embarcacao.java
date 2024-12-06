package model;

import java.util.ArrayList;

public abstract class Embarcacao {
    private int tamanho;
    private int vida;
    private ArrayList<Posicoes> posiconamento;
    private boolean afundado;

    public Embarcacao(int tamanho) {
        this.tamanho = tamanho;
        this.vida = tamanho;
        this.posiconamento = new ArrayList<Posicoes>();
        this.afundado = false;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getVida() {
        return vida;
    }

    public ArrayList<Posicoes> getPosiconamento() {
        return posiconamento;
    }

    public boolean isAfundado() {
        return afundado;
    }

    public void setAfundado() {
        this.afundado = true;
    }

    public void atualizarPosicao(int index, Posicoes p) {
        this.posiconamento.add(index, p);
    }

    public void embarcacaoAtingida() {
        this.vida--;
        if (this.vida == 0) {
            setAfundado();
        }
    }

    public String toString() {
        return "Embarcacao [tamanho=" + tamanho + ", vida=" + vida + ", afundado=" + afundado + "]" +
                ", posiconamento=" + posiconamento;
    }
}
