package ExemplosArquivo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

final class AtividadeLerESubstituirConteudoArquivo {
    private final static int QUANTIDADE_MEGABYTES = 2;

    private final InputStream dadosEntrada;

    private final OutputStream dadosSaida;

    public AtividadeLerESubstituirConteudoArquivo(
        InputStream dadosEntrada,
        OutputStream dadosSaida
    ){
        this.dadosEntrada = dadosEntrada;
        this.dadosSaida = dadosSaida;
    }

    public AtividadeLerESubstituirConteudoArquivo(
        Path arquivoEntrada,
        Path arquivoSaida
    ) throws IOException{
        this(
            Utilitarios.criarFluxoEntradaParaArquivo(arquivoEntrada), 
            Utilitarios.criarFluxoSaidaParaArquivo(arquivoSaida)
        );
    }

    public AtividadeLerESubstituirConteudoArquivo(
        String arquivoEntrada,
        String arquivoSaida
    ) throws IOException{
        this(
            Utilitarios.criarFluxoEntradaParaArquivo(arquivoEntrada), 
            Utilitarios.criarFluxoSaidaParaArquivo(arquivoSaida)
        );
    }

    private char[] formatarConteudo(char[] dados){
        return new String(dados)
                    .replace(".", "\n")
                    .toCharArray();
    }

    public void transferirDadosEmBytes() throws IOException{
        char[] dados = new char[1024 * 1024 * QUANTIDADE_MEGABYTES];

        try(
            var dadosEntradaTexto = new InputStreamReader(this.dadosEntrada);
            var dadosSaidaTexto = new OutputStreamWriter(this.dadosSaida)
        ) {
            while(true){
                var quantidadeLida = dadosEntradaTexto.read(dados);

                if(quantidadeLida < 0)
                    break;

                dados = this.formatarConteudo(dados);

                dadosSaidaTexto.write(dados, 0, quantidadeLida);
            }

        } catch (IOException erro) {
            throw new IOException("Falha ao tentar transferir dados binários!", erro);
        }
    }

    public void transferirDadosEmTexto() throws IOException{
        try(
            var dadosEntradaTexto = new Scanner(this.dadosEntrada);

            var dadosSaidaTexto = new PrintWriter(this.dadosSaida)
        ) {
            while(dadosEntradaTexto.hasNextLine()){
                var dados = this.formatarConteudo(
                    dadosEntradaTexto.nextLine().toCharArray()
                );

                dadosSaidaTexto.write(dados);
                dadosSaidaTexto.flush();
            }
        }
    }

    /*
        EXEMPLO UTILIZANDO CAMINHO DE ARQUIVOS 
        OBS: É necessário que o arquivo exista para funcionar
    */
    // public static void main(String[] args) throws Exception{
    //     var caminhoArquivoEntrada = args[0];

    //     var caminhoArquivoSaida = args[1];

    //     var atividade = new AtividadeLerESubstituirConteudoArquivo(caminhoArquivoEntrada, caminhoArquivoSaida);

    //     atividade.transferirDados();
    // }

    public static void main(String[] args) throws Exception{
        var caminhoArquivoSaida = Utilitarios.criarFluxoSaidaParaArquivo(args[0]);

        var atividade = new AtividadeLerESubstituirConteudoArquivo(System.in, caminhoArquivoSaida);

       atividade.transferirDadosEmTexto();
    }
}
