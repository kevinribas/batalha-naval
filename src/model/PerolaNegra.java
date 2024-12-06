package model;

public class PerolaNegra extends Embarcacao {
    public PerolaNegra() {
        super(3);
    }

    @Override
    public void embarcacaoAtingida() {
        super.embarcacaoAtingida();
        if (super.getVida() == 0) {
            System.out.println("Você afundou o Perola Negra!");
        }
    }

}
