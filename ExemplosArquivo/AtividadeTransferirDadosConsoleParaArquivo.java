package ExemplosArquivo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

final class AtividadeTransferirDadosConsoleParaArquivo{
    private final Path caminhoArquivo;

    public AtividadeTransferirDadosConsoleParaArquivo(Path caminhoArquivo){
        this.caminhoArquivo = caminhoArquivo;
    }

    public AtividadeTransferirDadosConsoleParaArquivo(String caminhoArquivo){
        this.caminhoArquivo = Path.of(caminhoArquivo);
    }
   

    public void transferirDadosConsole() throws IOException{
        try {
            Utilitarios.criarPasta(this.caminhoArquivo);

            if(!Files.isDirectory(this.caminhoArquivo) && !Files.exists(this.caminhoArquivo))
                Files.createFile(this.caminhoArquivo);

        } catch (IOException erro) {
            throw new IOException("Falha ao transferir dados!", erro);
        }

        try(
            var dadosEntrada = new Scanner(System.in);
            var dadosSaida = new FileOutputStream(this.caminhoArquivo.toFile()) 
        ){
            while(true){
                System.out.print("Digite alguma coisa: ");

                String dadosLidos = dadosEntrada.nextLine() + "\n";

                dadosSaida.write(dadosLidos.getBytes());
            }
        }catch(IOException erro){
            throw new IOException("Falha ao tentar transferir dados!", erro);
        }
    }

    public static void main(String[] args) throws IOException{
        var caminhoArquivo = args[0];

        var atividade = new AtividadeTransferirDadosConsoleParaArquivo(caminhoArquivo);

        atividade.transferirDadosConsole();
    }
}