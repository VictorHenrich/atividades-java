package ExemploInnerClass.AtividadeMunicaoHeroi;


final class AtividadeMunicaoHeroi{
    public static void main(String[] args) {
        int quantidadeMunicao = Integer.parseInt(args[0]);

        var heroi = new Heroi(quantidadeMunicao);

        try {
            while(true)
                heroi.atacar();
            
        } catch (Exception erro) {
            System.out.println("OPS, Algo deu errado: " + erro.getMessage());
        }
    }
}