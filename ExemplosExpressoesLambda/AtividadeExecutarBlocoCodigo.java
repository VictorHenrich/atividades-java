package ExemplosExpressoesLambda;

final class AtividadeExecutarBlocoCodigo {
    @FunctionalInterface
    private static interface Executador{
        public void executar(int indice);
    }

    private static void repetir(Executador blocoCodigo, int quantidadeRepeticoes, int pulos){
        for(var indice = 0; indice < quantidadeRepeticoes; indice += pulos){
            blocoCodigo.executar(indice);
        }
    }

    private static void repetir(Executador blocoCodigo, int quantidadeRepeticoes){
        repetir(blocoCodigo, quantidadeRepeticoes, 1);
    }

    private static void blocoCodigo(int indice){
        System.out.printf("Executando código, com indice: %d!\n", indice);
    }

    public static void main(String[] args) {
        var quantidadeRepeticoes = 10;

        repetir(
            AtividadeExecutarBlocoCodigo::blocoCodigo,
            quantidadeRepeticoes
        );

        repetir(
            indice -> System.out.printf("Executando outro código, com indice: %d!\n", indice),
            quantidadeRepeticoes,
            3
        );
    }
}
