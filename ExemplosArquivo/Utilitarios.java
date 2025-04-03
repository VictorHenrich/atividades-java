package ExemplosArquivo;

import java.nio.file.Path;

final class Utilitarios {
    public static Path concatenarCaminhos(String ...caminhosArquivo){
        Path caminho = Path.of("");

        for(var __caminho: caminhosArquivo){
            caminho = caminho.resolve(__caminho);
        }

        return caminho;
    }
}
