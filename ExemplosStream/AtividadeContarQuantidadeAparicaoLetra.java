package ExemplosStream;


import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


final class AtividadeContarQuantidadeAparicaoLetra{
    private static record ContadorAparicaoLetras(String caracteres){
        public Map<Character, Long> contarLetras(){
            return (
                Optional
                    .ofNullable(caracteres)
                    .map(String::toUpperCase)
                    .stream()
                    .flatMapToInt(String::chars)
                    .mapToObj(caracterInteiro -> (char) caracterInteiro)
                    .filter(caracter -> !Character.isWhitespace(caracter))
                    .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    ))
            );
        }
    }

    public static void main(String[] args) {
        String palavra = args[0];

        var atividade = new ContadorAparicaoLetras(palavra);

        Map<Character, Long> dadosLetra = atividade.contarLetras();

        System.out.println("Dados das letras: " + dadosLetra);
    }
}