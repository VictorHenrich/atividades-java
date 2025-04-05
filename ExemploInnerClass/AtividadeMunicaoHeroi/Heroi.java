package ExemploInnerClass.AtividadeMunicaoHeroi;

final class Heroi{
    private int quantidadeMunicao = 0;

    private final Arma arma;

    private class Arma{
        public void atirar() throws Exception{
            if(this.municaoEstaVazia())
                throw new Exception("Herói não possui balas para atirar!");

            //Ou Heroi.this.quantidadeMunicao--;
            quantidadeMunicao--;

            System.out.println("PIU!");
        }
        
        private boolean municaoEstaVazia(){
            //Ou return Heroi.this.quantidadeMunicao <= 0;
            return quantidadeMunicao <= 0;
        }
    }

    public Heroi(int quantidadeMunicao){
        this.arma = new Arma();

        this.quantidadeMunicao = quantidadeMunicao;
    }

    public void atacar() throws Exception{
        this.arma.atirar();
    }
}
