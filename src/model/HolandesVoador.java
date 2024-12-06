package model;

public class HolandesVoador extends Embarcacao {
    public HolandesVoador() {
        super(5);
    }

    @Override
    public void embarcacaoAtingida() {
        super.embarcacaoAtingida();
        if (super.getVida() == 0) {
            System.out.println("Você afundou o Holandes Voador!");
        }
    }

}
