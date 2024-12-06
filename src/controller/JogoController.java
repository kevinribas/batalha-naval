package controller;

import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import model.Posicoes;
import model.Rankeada;
import model.Tabuleiro;
import view.JogoView;

public class JogoController {
    private Tabuleiro tabuleiro;
    private JogoView view;
    private Scanner scanner;

    public JogoController() {
        this.tabuleiro = new Tabuleiro();
        this.view = new JogoView();
        this.scanner = new Scanner(System.in);
    }

    public JogoController(String nomeJogador) {
        this.tabuleiro = new Tabuleiro(nomeJogador);
        this.view = new JogoView();
        this.scanner = new Scanner(System.in);
    }

    public void comecarJogo() {
        comecarJogo(false);
    }

    public void comecarJogo(boolean modoRankeado) {
        view.exibirMensagem("\033[1;34m" + "Bem-Vindo à Batalha de 1720!\n" + "\u001B[0m");
        while (!tabuleiro.ganhou() && !tabuleiro.perdeu()) {
            tabuleiro.exibirTabuleiro();
            Posicoes position = pegarInputUsuario("\u001B[34m"
                    + "Coordenadas do seu disparo (Exemplo: A1, B2...): " + "\u001B[0m");

            if (validarCoordenada(position.getLinha(), position.getColuna())) {
                boolean hit = tabuleiro.atirar(position.getLinha(), position.getColuna());
                if (hit) {
                    String hitMessage = view.exibirMensagemDeAcertoAleatoria();
                    view.exibirMensagemColorida(hitMessage, "\u001B[32m");
                    playSound("src/sons/boom.wav");
                } else {
                    String missMessage = view.exibirMensagemDeErroAleatorio();
                    view.exibirMensagemColorida(missMessage, "\u001B[31m");
                    playSound("src/sons/miss.wav");
                }
            } else {
                view.exibirMensagemColorida("Coordenadas inválidas. Por favor, tente novamente.",
                        "\u001B[33m");
            }
        }

        if (tabuleiro.perdeu()) {
            tabuleiro.exibirTabuleiro();
            view.exibirMensagem("\nVocê perdeu! Tente novamente.\n");
        } else if (tabuleiro.ganhou()) {
            tabuleiro.exibirTabuleiro();
            view.exibirMensagem("\nParabéns! Você venceu a Batalha de 1720!\n");

            if (modoRankeado) {
                String nomeJogador = tabuleiro.getJogador().getNome();
                int tentativas = tabuleiro.getJogador().getTentativas();
                Rankeada.adicionarJogadorAoRanking(nomeJogador, tentativas);
                Rankeada.exibirRanking();
            }
        }
    }

    private Posicoes pegarInputUsuario(String prompt) {
        while (true) {
            try {
                view.exibirMensagem(prompt);
                String input = scanner.next().toUpperCase();
                return Posicoes.getPosicao(input);
            } catch (IllegalArgumentException e) {
                view.exibirMensagemColorida("Entrada inválida. Use o formato A1, B2...",
                        "\u001B[33m");
            }
        }
    }

    private boolean validarCoordenada(int x, int y) {
        return x >= 0 && x < tabuleiro.getSize() && y >= 0 && y < tabuleiro.getSize();
    }

    private void playSound(String soundFile) {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }
}
