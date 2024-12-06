package model;

class Canoa extends Embarcacao {

    public Canoa() {
        super(2);
    }

    @Override
    public void embarcacaoAtingida() {
        super.embarcacaoAtingida();
        if (super.getVida() == 0) {
            System.out.println("Você afundou uma Canoa!");
        }
    }
}
