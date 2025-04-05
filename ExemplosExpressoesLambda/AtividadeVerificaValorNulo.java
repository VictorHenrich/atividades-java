package ExemplosExpressoesLambda;

final class AtividadeVerificaValorNulo {
    @FunctionalInterface
    private static interface CasoValorNulo{
        public void executar();
    }

    @FunctionalInterface
    private static interface CasoValorExista{
        public void executar(String dados);
    }

    private static void run(
        String dados, 
        CasoValorNulo comparaValorNulo, 
        CasoValorExista comparaValorExistente)
    {
        if(dados == null)
            comparaValorNulo.executar();

        else
            comparaValorExistente.executar(dados);
    }

    public static void main(String[] args) {
        run(
            null,
            () -> System.out.println("VALOR PASSADO É NULO"),
            dados -> System.out.println("Dado passado não é nulo! " + dados)
        );
    }
}
