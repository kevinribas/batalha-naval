package model;

public class VingancaDaRainha extends Embarcacao {
    public VingancaDaRainha() {
        super(4);
    }

    @Override
    public void embarcacaoAtingida() {
        super.embarcacaoAtingida();
        if (super.getVida() == 0) {
            System.out.println("Você afundou o Vinganca da Rainha!");
        }
    }

}
