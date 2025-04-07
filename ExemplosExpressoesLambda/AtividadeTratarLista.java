package ExemplosExpressoesLambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

final class AtividadeTratarLista {

    private static final record TratarLista(List<Integer> lista){
        public TratarLista(Integer ...lista){
            this(new ArrayList<>(Arrays.asList(lista)));
        }

        public TratarLista somarValores(int quantidadeSerSomada){
            this.lista.replaceAll(numero -> numero + quantidadeSerSomada);
    
            return this;
        }

        public TratarLista removerNumerosPares(){
            this.lista.removeIf(numero -> numero % 2 == 0);
            
            return this;
        }

        public TratarLista ordernarNumerosDecrescente(){
            this.lista.sort(Comparator.reverseOrder());

            return this;
        }
    }

    public static void main(String[] args) {
        var quantidadeSerSomada = 2;

        List<Integer> lista = (
            new TratarLista(3, 6, 2, 4, 9, 7, 1, 5, 8, 0)
            .somarValores(quantidadeSerSomada)
            .removerNumerosPares()
            .ordernarNumerosDecrescente()
            .lista()
        );

        System.out.format("Lista tratada: %s\n", lista);
    }
}
