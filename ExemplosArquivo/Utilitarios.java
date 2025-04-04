package ExemplosArquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class Utilitarios {
    public static Path concatenarCaminhos(String ...caminhosArquivo){
        Path caminho = Path.of("");

        for(var __caminho: caminhosArquivo){
            caminho = caminho.resolve(__caminho);
        }

        return caminho;
    }

    public static void criarPasta(Path caminho) throws IOException{
        var caminhoPasta = Files.isDirectory(caminho) ? caminho : caminho.getParent();

        try {
            if(!Files.exists(caminhoPasta))
                Files.createDirectory(caminhoPasta);
                
        } catch (IOException erro) {
            throw new IOException("Falha ao criar pasta!", erro);
        }
    }

    public static FileInputStream criarFluxoEntradaParaArquivo(Path arquivo) throws IOException{
        try {
            return new FileInputStream(arquivo.toFile());

        } catch (FileNotFoundException  erro) {
            throw new IOException("Falha ao converter fluxo de entrada para arquivo: " + arquivo, erro);
        }
    }

    public static FileInputStream criarFluxoEntradaParaArquivo(String caminhoArquivo) throws IOException{
        return criarFluxoEntradaParaArquivo(Path.of(caminhoArquivo));
    }

    public static FileOutputStream criarFluxoSaidaParaArquivo(Path arquivo) throws IOException{
        try {
            return new FileOutputStream(arquivo.toFile());

        } catch (FileNotFoundException  erro) {
            throw new IOException("Falha ao converter fluxo de saida para arquivo: " + arquivo, erro);
        }
    }

    public static FileOutputStream criarFluxoSaidaParaArquivo(String caminhoArquivo) throws IOException{
        return criarFluxoSaidaParaArquivo(Path.of(caminhoArquivo));
    }
}
