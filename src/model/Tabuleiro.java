package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Tabuleiro {
    @SuppressWarnings("FieldMayBeFinal")
    private char[][] matriz;
    private final int size;
    private List<Embarcacao> embarcacoes;
    private Jogador jogador;

    public Tabuleiro() {
        this(null);
    }

    public Tabuleiro(String nomeJogador) {
        this.size = 10;
        this.matriz = new char[size][size];
        this.embarcacoes = new ArrayList<>();
        this.jogador = new Jogador(nomeJogador, size);
        inicializarTabuleiro();
    }

    public Tabuleiro(int size, String nomeJogador) {
        this.size = size;
        this.matriz = new char[size][size];
        this.embarcacoes = new ArrayList<>();
        this.jogador = new Jogador(nomeJogador, size);
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriz[i][j] = '~';
            }
        }
        alocarEmbarcacoesAleatoriamente();
    }

    private void popularEmbarcacoes() {
        Embarcacao submarino = new Canoa();
        Embarcacao cruzador1 = new PerolaNegra();
        Embarcacao cruzador2 = new PerolaNegra();
        Embarcacao portaAviao = new HolandesVoador();
        Embarcacao cargueiro = new VingancaDaRainha();
        embarcacoes.add(submarino);
        embarcacoes.add(cruzador1);
        embarcacoes.add(cruzador2);
        embarcacoes.add(portaAviao);
        embarcacoes.add(cargueiro);
    }

    private void alocarEmbarcacoesAleatoriamente() {
        popularEmbarcacoes();
        Random random = new Random();
        for (Embarcacao embarcacao : embarcacoes) {
            boolean alocado = false;
            while (!alocado) {
                Posicoes p = Posicoes.values()[random.nextInt(100)];
                boolean horizontal = random.nextBoolean();

                if (posicaoLivre(embarcacao.getTamanho(), p, horizontal)) {
                    alocarEmbarcacao(embarcacao, p, horizontal);
                    alocado = true;
                }
            }
        }
    }

    private boolean posicaoLivre(int tamanho, Posicoes p, boolean horizontal) {
        if (horizontal) {
            if (p.getColuna() + tamanho > this.size) {
                return false;
            }
            for (int i = 0; i < tamanho; i++) {
                if (matriz[p.getLinha()][p.getColuna() + i] != '~') {
                    return false;
                }
            }
        } else {
            if (p.getLinha() + tamanho > this.size) {
                return false;
            }
            for (int i = 0; i < tamanho; i++) {
                if (matriz[p.getLinha() + i][p.getColuna()] != '~') {
                    return false;
                }
            }
        }
        return true;
    }

    private void alocarEmbarcacao(Embarcacao e, Posicoes p, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < e.getTamanho(); i++) {
                matriz[p.getLinha()][p.getColuna() + i] = '#';
                e.atualizarPosicao(i, Posicoes.getPosicao(p.getLinha(), p.getColuna() + i));
            }
        } else {
            for (int i = 0; i < e.getTamanho(); i++) {
                matriz[p.getLinha() + i][p.getColuna()] = '#';
                e.atualizarPosicao(i, Posicoes.getPosicao(p.getLinha() + i, p.getColuna()));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean atirar(int x, int y) {
        if (!validarTiro(x, y)) {
            System.out.println("\u001B[34m" + "Você já atirou nessa coordenada." + "\u001B[0m");
            return false;
        }
        jogador.incrementarTentativas();
        if (matriz[x][y] == '#') {
            matriz[x][y] = 'X';
            Embarcacao e = embarcacoes.stream().filter(
                    embarcacao -> embarcacao.getPosiconamento().contains(Posicoes.getPosicao(x, y)))
                    .collect(Collectors.toList()).get(0);
            e.embarcacaoAtingida();
            return true;
        } else if (matriz[x][y] == '~') {
            matriz[x][y] = 'O';
        }
        return false;
    }

    public boolean validarTiro(int x, int y) {
        return matriz[x][y] == '~' || matriz[x][y] == '#';
    }

    public boolean ganhou() {
        for (Embarcacao embarcacao : embarcacoes) {
            if (!embarcacao.isAfundado()) {
                return false;
            }
        }
        return true;
    }

    public boolean perdeu() {
        return jogador
                .verificarDerrota((int) embarcacoes.stream().filter(e -> !e.isAfundado()).count());
    }

    public void exibirTabuleiro() {
        System.out.print("   ");
        for (int num = 1; num <= size; num++) {
            System.out.print(String.format("\u001B[38;5;208m%2d\u001B[0m", num));
            if (num == 9) {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.print("  ");
        for (int num = 1; num <= size + 2; num++) {
            System.out.print("\u001B[38;5;208m--\u001B[0m");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {

            System.out.print(String.format("\u001B[38;5;208m%c \u001B[0m", 'A' + i));
            System.out.print(String.format("\u001B[38;5;208m| \u001B[0m"));

            for (int j = 0; j < size; j++) {
                char symbol = matriz[i][j];
                switch (symbol) {
                    case 'X':
                        System.out.print("\u001B[32m" + symbol + "\u001B[0m ");
                        break;
                    case 'O':
                        System.out.print("\u001B[31m" + symbol + "\u001B[0m ");
                        break;
                    default:
                        System.out.print("\u001B[34m~\u001B[0m ");
                        break;
                }
            }
            System.out.println();
        }
        if ((jogador.getLimiteTentativas() - jogador.getTentativas()) > 15) {
            System.out.println("\u001B[33m" + "Você ainda pode atirar mais " + "\u001B[0m"
                    + "\033[1;33m" + (jogador.getLimiteTentativas() - jogador.getTentativas())
                    + " vezes." + "\u001B[0m");
        } else {
            System.out.println("\u001B[31m" + "Você ainda pode atirar mais " + "\u001B[0m"
                    + "\033[1;31m" + (jogador.getLimiteTentativas() - jogador.getTentativas())
                    + " vezes." + "\u001B[0m");
        }
    }

    public Jogador getJogador() {
        return jogador;
    }
}
