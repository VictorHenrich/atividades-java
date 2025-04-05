package ExemplosExpressoesLambda;

import java.util.ArrayList;
import java.util.List;

public class AtividadeFiltrandoDadosLista {
    @FunctionalInterface
    private static interface FiltrarItem{
        public boolean filtrar(Object item);
    }

    private static List<Object> filtrarLista(List<Object> lista, FiltrarItem filtro){
        final List<Object> listaTratada = new ArrayList<>();

        lista.forEach(item -> {
            if(filtro.filtrar(item))
                listaTratada.add(item);
        });

        return listaTratada;
    }

    public static void main(String[] args) {
        var numeroPermitido = 5;

        List<Object> lista = List.of(
            1, 5, 6, 10,
            2, 3, 0, -1, -2, -10
        );

        List<Object> novaLista = filtrarLista(
            lista, 
            numero -> (
                numero instanceof Integer numeroTratado && 
                numeroTratado > numeroPermitido
            )
        );

        System.out.format("Nova lista tratada: %s\n", novaLista);
    }
}
