package ExemplosExpressoesLambda;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

final class AtividadeListaTelefonica {
    private static record ListaTelefonica(Map<String, String> contatos){
        public static class ExcecaoContatoNaoLocalizado extends NoSuchElementException{
            public ExcecaoContatoNaoLocalizado(String nomeContato){
                super(String.format("Contato '%s' não foi localizado!", nomeContato));
            }
        }

        public ListaTelefonica(){
            this(new HashMap<>());
        }

        public ListaTelefonica adicionarContato(String nome, String telefone){
            this.contatos.put(nome, telefone);

            return this;
        }

        public Optional<String> buscarTelefonePeloNome(String nome){
            for(var contato: this.contatos.entrySet()){
                if(contato.getKey().toUpperCase().trim().equals(nome.toUpperCase().trim()))
                    return Optional.of(contato.getValue());
            }

            return Optional.empty();
        }
    }

    public static void main(String[] args) throws ListaTelefonica.ExcecaoContatoNaoLocalizado {
        var nomeContato = args[0];

        var listaTelefonica = (
            new ListaTelefonica()
                .adicionarContato("Claudio", "111111111")
                .adicionarContato("Victor", "222222222")
                .adicionarContato("Stephanie", "333333333")
                .adicionarContato("Lucas", "4444444444")
                .adicionarContato("Nathan", "5555555555")
        );

        var dadosBusca = listaTelefonica.buscarTelefonePeloNome(nomeContato);

        dadosBusca.ifPresent(
            telefone -> System.out.format(
                "Contato '%s' localizado: %s\n", 
                nomeContato, 
                telefone
            )
        );

        dadosBusca.orElseThrow(
            ()-> new ListaTelefonica.ExcecaoContatoNaoLocalizado(nomeContato)
        );
    }

}
