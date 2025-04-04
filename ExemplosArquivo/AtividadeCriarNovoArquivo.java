package ExemplosArquivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class AtividadeCriarNovoArquivo {
    private Path caminhoArquivo;

    public AtividadeCriarNovoArquivo(String[] caminhoArquivo){
        this(Utilitarios.concatenarCaminhos(caminhoArquivo));
    }

    public AtividadeCriarNovoArquivo(String caminhoArquivo){
        this(Path.of(caminhoArquivo));
    }

    public AtividadeCriarNovoArquivo(Path caminhoArquivo){
        this.caminhoArquivo = caminhoArquivo;
    }

    public void criarArquivo() throws IOException{
        try {
            if(Files.isDirectory(this.caminhoArquivo))
                throw new IOException("Caminho passado se trata de uma pasta!");

            Utilitarios.criarPasta(this.caminhoArquivo);

            var nomeArquivoDivido = this.caminhoArquivo.getFileName().toString().split("\\.");

            var nomeArquivo = nomeArquivoDivido[0];

            var extensaoArquivo = nomeArquivoDivido[1];

            Files.createFile(
                this.caminhoArquivo.getParent().resolve(
                    nomeArquivo + "_" + System.currentTimeMillis() + "." + extensaoArquivo
                )
            );

        } catch (IOException erro) {
            throw new IOException("Falha ao tentar criar novo arquivo!", erro);
        }
    }

    public static void main(String[] args) throws Exception{
        var nomeArquivo = args[0];

        var atividade = new AtividadeCriarNovoArquivo(nomeArquivo);

        atividade.criarArquivo();
    }
}
