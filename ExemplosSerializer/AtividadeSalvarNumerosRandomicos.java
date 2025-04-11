package ExemplosSerializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class AtividadeSalvarNumerosRandomicos {
    private static record NumerosRandomico(
        List<Integer> numerosRandomicos,
        Random objetoRandom
    ) implements Serializable{
        public static transient int NUMERO_INICIAL_RANDOMICO = 0;

        public static transient int NUMERO_FINAL_RANDOMICO = 1000;

        public static transient Path CAMINHO_ARQUIVO = Path.of("./ExemplosSerializer/arquivo_serializer.bin");

        public NumerosRandomico(){
            this(new ArrayList<>(), new Random());
        }

        public NumerosRandomico gerarNumeroRandomico()  throws IOException{
            var numeroRandomico = this.objetoRandom.nextInt(
                NUMERO_INICIAL_RANDOMICO,
                NUMERO_FINAL_RANDOMICO
            );

            var numeroRandomicosCapturados = this.lerNumerosArmazenados();

            numerosRandomicos.add(numeroRandomico);

            this.numerosRandomicos.addAll(numeroRandomicosCapturados);

            this.atualizarArquivo();

            return this;
        }

        private void atualizarArquivo() throws IOException{
            try(var dadosArquivo = new ObjectOutputStream(Files.newOutputStream(CAMINHO_ARQUIVO))){
                dadosArquivo.writeObject(this);

            }catch(IOException erro){
                throw new IOException(
                    "Falha ao tentar gravar dados do arquivo do objeto!", 
                    erro
                );
            }
        }

        private List<Integer> lerNumerosArmazenados() throws IOException{
            try(var dadosArquivo = new ObjectInputStream(Files.newInputStream(CAMINHO_ARQUIVO))){
                return ((NumerosRandomico) dadosArquivo.readObject()).numerosRandomicos;

            }catch(IOException erro){
                return new ArrayList<>();

            }catch(ClassNotFoundException erro){
                throw new IOException(
                    "Falha ao ler dados do arquivo do objeto",
                    erro
                );
            }
        }
    }

    public static void main(String[] args) throws Exception{
        var numerosRandomicos = new NumerosRandomico();

        numerosRandomicos
            .gerarNumeroRandomico()
            .numerosRandomicos()
            .forEach(
                numeroRandomico -> System.out.format(
                    "Numero gerado: {%d}\n", 
                    numeroRandomico
                )
            );
    }
}
