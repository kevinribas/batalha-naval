package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Rankeada {
    private static Map<String, Integer> ranking = new TreeMap<>();

    public static void adicionarJogadorAoRanking(String nomeJogador, int tentativas) {
        if (nomeJogador != null && !nomeJogador.trim().isEmpty() && tentativas > 0) {
            if (!ranking.containsKey(nomeJogador) || ranking.get(nomeJogador) > tentativas) {
                ranking.put(nomeJogador, tentativas);
                System.out.println("\nPontuação salva no ranking!");
            }
        }
    }

    public static Map<String, Integer> getRankingOrdenado() {
        return ranking.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void exibirRanking() {
        if (ranking.isEmpty()) {
            System.out.println("\nAinda não há marinheiros no ranking!");
            return;
        }

        System.out.println("\n=== RANKING DE JODADORES ===");
        System.out.println("Pos.\tMarinheiro\t\tTentativas");
        System.out.println("----------------------------");

        Map<String, Integer> rankingOrdenado = getRankingOrdenado();
        int posicao = 1;

        for (Map.Entry<String, Integer> entry : rankingOrdenado.entrySet()) {
            System.out.printf("%d°\t%-15s\t%d\n", posicao++, entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------\n");
    }

    public static void limparRanking() {
        ranking.clear();
    }

    public static boolean jogadorExiste(String nomeJogador) {
        return ranking.containsKey(nomeJogador);
    }

    public static Integer getMelhorPontuacao(String nomeJogador) {
        return ranking.getOrDefault(nomeJogador, null);
    }
}
