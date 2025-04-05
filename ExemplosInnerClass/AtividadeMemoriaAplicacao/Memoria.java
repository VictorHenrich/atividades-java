package ExemplosInnerClass.AtividadeMemoriaAplicacao;

public class Memoria {
    private final static Runtime objetoAplicacao = Runtime.getRuntime();

    private final StatusAplicacao status = new StatusAplicacao();

    public static class StatusAplicacao{
        public void imprimirValoresDaMemoria(){
            System.out.printf(
                """
                Quantidade total de memória: %d
                Quantidade máxima de memória: %d
                Quantidade memória disponível: %d
                """,
                objetoAplicacao.totalMemory(),
                objetoAplicacao.maxMemory(),
                objetoAplicacao.freeMemory()
            );
        }
    }

    public StatusAplicacao status(){
        return this.status;
    }
}
