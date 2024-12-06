package model;

public class Jogador {
    private String nome;
    private int tentativas;
    private int limiteTentativas;

    public Jogador(int tamanhoTabuleiro) {
        this(null, tamanhoTabuleiro);
    }

    public Jogador(String nome, int tamanhoTabuleiro) {
        this.nome = nome;
        this.tentativas = 0;
        this.limiteTentativas = (tamanhoTabuleiro * tamanhoTabuleiro) / 2;
    }

    public void incrementarTentativas() {
        this.tentativas++;
    }

    public boolean verificarDerrota(int embarcacoesRestantes) {
        return this.tentativas >= this.limiteTentativas && embarcacoesRestantes > 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTentativas() {
        return tentativas;
    }

    public int getLimiteTentativas() {
        return limiteTentativas;
    }
}
