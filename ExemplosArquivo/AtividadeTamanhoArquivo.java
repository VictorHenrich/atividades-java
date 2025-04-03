package ExemplosArquivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class AtividadeTamanhoArquivo{
    private final Path caminhoArquivo;

    private static Path concatenarCaminhos(String ...caminhosArquivo){
        Path caminho = Path.of("");

        for(var __caminho: caminhosArquivo){
            caminho = caminho.resolve(__caminho);
        }

        return caminho;
    }

    public AtividadeTamanhoArquivo(String ...caminhosArquivo){
        this(concatenarCaminhos(caminhosArquivo));
    }

    public AtividadeTamanhoArquivo(String caminhoArquivo){
        this(Path.of(caminhoArquivo));
    }
    
    public AtividadeTamanhoArquivo(Path caminhoArquivo){
        this.caminhoArquivo = caminhoArquivo;
    }

    public long verificarTamanhoArquivo() throws IOException{
        return this.verificarTamanhoArquivo(this.caminhoArquivo);
    }

    public long verificarTamanhoArquivo(Path caminhoArquivo) throws IOException{
        long tamanhoArquivo = 0;

        if(Files.isDirectory(caminhoArquivo)){
            try(var dadosDiretorio = Files.newDirectoryStream(caminhoArquivo)){
                for(var arquivo: dadosDiretorio)
                    tamanhoArquivo += this.verificarTamanhoArquivo(arquivo);

            }catch(IOException erro){
                throw new IOException("Falha ao processar dados de diretório!", erro);
            }

        }else
            try{
                tamanhoArquivo = Files.size(caminhoArquivo);

            }catch(IOException erro){
                throw new IOException("Falha ao buscar tamanho de arquivo!", erro);
            }

        return tamanhoArquivo;
    }

    public static void main(String[] args) throws IOException{
        var caminhoPassado = args[0];

        var atividade = new AtividadeTamanhoArquivo(caminhoPassado);

        long tamanhoArquivo = atividade.verificarTamanhoArquivo();

        System.out.printf("Tamanho do arquivo gerado: %d\n", tamanhoArquivo);
    }
}