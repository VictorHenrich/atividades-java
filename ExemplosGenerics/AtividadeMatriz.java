package ExemplosGenerics;

import java.util.ArrayList;
import java.util.List;

final class AtividadeMatriz {
    private static class Matriz<T>{
        public static int QUANTIDADE_LINHAS_E_COLUNAS_PADRAO = 3;

        private final List<List<T>> dadosMatriz;

        public static <T> List<List<T>> gerarMatriz(int quantidadeLinhas, int quantidadeColunas){
            List<List<T>> dadosMatriz = new ArrayList<>();

            for(int indiceLinhas=0; indiceLinhas < quantidadeLinhas; indiceLinhas++){
                List<T> dadosLinha = new ArrayList<>();

                for(int indiceColunas=0; indiceColunas < quantidadeColunas; indiceColunas++)
                    dadosLinha.add(null);

                dadosMatriz.add(dadosLinha);
            }

            return dadosMatriz;
        }

        public Matriz(List<List<T>> matrizInicial){
            this.dadosMatriz = matrizInicial;
        }

        public Matriz(int quantidadeLinhas, int quantidadeColunas){
            this(gerarMatriz(quantidadeLinhas, quantidadeColunas));
        }

        public Matriz(){
            this(
                QUANTIDADE_LINHAS_E_COLUNAS_PADRAO,
                QUANTIDADE_LINHAS_E_COLUNAS_PADRAO
            );
        }

        public void mostrarDadosEmTela(){
            this
                .dadosMatriz
                .stream()
                .forEach(lista -> {
                    lista
                        .stream()
                        .forEach(System.out::println);
                });
        }

        public void alterarValor(T valor, int linha, int coluna) throws Exception{
            var dadosLinha = this.dadosMatriz.get(linha);

            if(dadosLinha == null)
                throw new Exception("Falha ao localizar dados da Matriz");

            dadosLinha.set(coluna, valor);
        }
    }

    public static Matriz<String> criarMatriz(int linhas, int colunas) throws Exception{
        var matriz = new Matriz<String>();

        for(var indiceLinha = 0; indiceLinha < linhas; indiceLinha++){
            for(var indiceColuna = 0; indiceColuna < colunas; indiceColuna++){
                var valor = String.format(
                    "Valor: Linha %d | Coluna %d", 
                    indiceLinha + 1, 
                    indiceColuna + 1
                );

                matriz.alterarValor(valor, indiceLinha, indiceColuna);
            }
        }

        return matriz;
    }

    public static void main(String[] args) throws Exception{
        criarMatriz(
            Matriz.QUANTIDADE_LINHAS_E_COLUNAS_PADRAO, 
            Matriz.QUANTIDADE_LINHAS_E_COLUNAS_PADRAO
        )
        .mostrarDadosEmTela();
    }

}
