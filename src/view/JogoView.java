package view;

import java.util.Random;

public class JogoView {

    public void exibirMensagem(String message) {
        System.out.println(message);
    }

    public void exibirMensagemColorida(String message, String colorCode) {
        System.out.println(colorCode + message + "\u001B[0m");
    }

    public void exibirTabuleiroEscondido(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public String exibirMensagemDeAcertoAleatoria() {
        String[] messages = {"Que tiro certeiro!", "Na mosca!", "Que mirinha, ein?!",
                "Acertou em cheio!", "Belo tiro!"};
        Random random = new Random();
        return messages[random.nextInt(messages.length)];
    }

    public String exibirMensagemDeErroAleatorio() {
        String[] messages = {"Tiro na água!", "Você errou!", "Nada por aqui.",
                "Melhor sorte na próxima!", "Quase lá!"};
        Random random = new Random();
        return messages[random.nextInt(messages.length)];
    }

    public static void exibirMenu() {
        System.out.println("\u001B[33m" + "~~~~~ " + "\u001B[0m" + "\033[1;93m" + "Batalha de 1720"
                + "\u001B[0m" + "\u001B[33m" + " ~~~~~" + "\u001B[0m");
        System.out.println("\u001B[33m" + "1 - Modo Normal" + "\u001B[0m");
        System.out.println("\u001B[33m" + "2 - Modo Rankeado" + "\u001B[0m");
        System.out.println("\u001B[33m" + "3 - Ver Ranking" + "\u001B[0m");
        System.out.println("\u001B[33m" + "4 - Sair\n " + "\u001B[0m");
        System.out.println("\u001B[38m" + "Escolha uma opção: " + "\u001B[0m");
    }

}
