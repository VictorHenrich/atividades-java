package ExemploInnerClass.AtividadeMemoriaAplicacao;

public class AtividadeMemoriaAplicacao {
    public static void main(String[] args) {
        var atividade = new Memoria();

        Memoria.StatusAplicacao statusMemoria = atividade.status();

        statusMemoria.imprimirValoresDaMemoria();
    }
}
