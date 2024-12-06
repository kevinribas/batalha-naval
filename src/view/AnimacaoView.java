package view;

import java.util.Scanner;

public class AnimacaoView {
    private static volatile boolean running = true;
    private static volatile String ondaAtual = "";

    public static void exibirHistoria() throws InterruptedException {
        Integer contador = 0;
        String historia = "\033[1;34m" + "A Batalha de 1720:" + "\u001B[0m" + "\u001B[34m"
                + " Os mares são dominados pelo caos e pela violência, com piratas saqueando "
                + "navios mercantes e espalhando o terror pelos oceanos. Você, um capitão experiente da marinha real, foi "
                + "designado para restaurar a ordem e proteger os valiosos carregamentos que abastecem o império.\n\n"
                + "\u001B[0m";
        for (char c : historia.toCharArray()) {
            System.out.print(c);
            Thread.sleep(50);
            contador++;
            if (contador >= 90 && c == ' ') {
                System.out.println();
                contador = 0;
            }
        }
    }

    public static void exibirAnimacao() {
        System.out.print("\033[H\033[2J");

        Thread animacao = new Thread(() -> {
            String[] ondas = {
                    "\u001B[34m" + "          ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~        " + "\u001B[0m",
                    "\u001B[34m" + "         ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~         " + "\u001B[0m",};

            int index = 0;
            while (running) {
                ondaAtual = ondas[index];
                exibirBarco(ondaAtual);
                exibirMensagem();
                index = (index + 1) % ondas.length;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        animacao.start();

        capturarEntrada(scanner -> {
            running = false;
            return;
        });
    }

    private static void exibirBarco(String onda) {
        System.out.print("\033[H");
        System.out.println("\033[1;93m" + "              |    |    |              ");
        System.out.println("             )_)  )_)  )_)             ");
        System.out.println("            )___))___))___)\\          ");
        System.out.println("           )____)____)_____)\\\\        ");
        System.out.println("         _____|____|____|____\\\\\\__    ");
        System.out.println("         \\                   /         " + "\u001B[0m");
        System.out.println(onda);
        System.out
                .println("\u001B[35m" + "            Batalha de 1720!             " + "\u001B[0m");
    }


    private static void exibirMensagem() {
        System.out.print("\033[9;1H");
        System.out.println("Aperte enter para prosseguir: ");
    }


    private static void capturarEntrada(OnKeyPressListener listener) {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("\033[10;1H> ");
            scanner.nextLine();
            listener.onKeyPress(scanner);
        }
    }

    interface OnKeyPressListener {
        void onKeyPress(Scanner scanner);
    }

}
