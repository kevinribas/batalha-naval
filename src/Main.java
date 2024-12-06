import controller.JogoController;
import java.util.Scanner;
import model.Rankeada;
import view.AnimacaoView;
import view.JogoView;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        JogoController jogoController;
        Scanner scanner = new Scanner(System.in);
        Boolean historiaExibida = false;

        AnimacaoView.exibirAnimacao();
        System.out.print("\033[H\033[2J");
        outloop: while (true) {

            JogoView.exibirMenu();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    jogoController = new JogoController();
                    if (!historiaExibida) {
                        AnimacaoView.exibirHistoria();
                        Thread.sleep(3000);
                        historiaExibida = true;
                    }
                    jogoController.comecarJogo();
                    break;
                case 2:
                    System.out.print("Digite seu nome para o ranking: ");
                    scanner.nextLine();
                    String nomeJogador = scanner.nextLine();

                    jogoController = new JogoController(nomeJogador);
                    if (!historiaExibida) {
                        AnimacaoView.exibirHistoria();
                        Thread.sleep(3000);
                        historiaExibida = true;
                    }
                    jogoController.comecarJogo(true);
                    break;
                case 3:
                    Rankeada.exibirRanking();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break outloop;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }
}
