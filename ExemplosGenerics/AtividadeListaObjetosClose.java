package ExemplosGenerics;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

final class AtividadeListaObjetosClose{
    private static record ItemLista<T>(T valor) implements Closeable{
        @Override
        public void close() throws IOException {
            System.out.format("Fechando item {%s}!\n", this.valor);
        }
        
    }

    private static void fecharTudo(List<? extends Closeable> lista) throws IOException{
        for(var item: lista)
            item.close();
    }

    public static void main(String[] args) throws Exception{
        fecharTudo(List.of(
            new ItemLista<>("Valor 1"),
            new ItemLista<>("Valor 2"),
            new ItemLista<>("Valor 3"),
            new ItemLista<>("Valor 4")
        ));
    }
}