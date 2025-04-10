package ExemplosStream;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class AtividadeListaProdutos {
    private static enum CategoriaProduto{
        BRINQUEDO,
        ELETRONICO,
        PAPELARIA;
    }

    private static record Produto(String nome, double preco, CategoriaProduto tipoProduto){
        
    }

    private static record Pedido(
        long id,
        Cliente cliente, 
        List<Produto> produtos, 
        LocalDate dataPedido
    ){
        static long ULTIMO_ID = 0;

        private static long pegarProximoId(){
            ULTIMO_ID++;

            return ULTIMO_ID;
        }

        public Pedido(Cliente cliente, List<Produto> produtos){
            this(pegarProximoId(), cliente, produtos, LocalDate.now());
        }

        public double calcularTotalPedido(){
            return this
                    .produtos
                    .stream()
                    .mapToDouble(Produto::preco)
                    .sum();
        }
    }

    private static record Cliente(String nome){

    }

    public static Map<Cliente, Long> agruparQuantidadePedidos(List<Pedido> pedidos){
        return pedidos
                    .stream()
                    .collect(
                        Collectors.groupingBy(
                            Pedido::cliente,
                            Collectors.counting()
                        )
                    );
    }

    public static  Map<Pedido, Double> agruparCustoTotalPedidos(List<Pedido> pedidos){
        return pedidos
                    .stream()
                    .collect(
                        Collectors.toUnmodifiableMap(
                            Function.identity(),
                            Pedido::calcularTotalPedido
                        )
                    );
    }

    public static Map<Cliente, Long> agruparClientesPorTipoProduto(List<Pedido> pedidos, CategoriaProduto tipoProduto){
        return pedidos
            .stream()
            .collect(
                Collectors.groupingBy(
                    Pedido::cliente,
                    Collectors.reducing(
                        0l,
                        pedido -> (    
                            pedido
                                .produtos()
                                .stream()
                                .filter(produto -> produto.tipoProduto().equals(tipoProduto))
                                .count()
                        ),
                        Long::sum
                    )
                )
            );
    }

    public static List<Long> buscarIDSPedidosPorValor(List<Pedido> pedidos, double valor){
        return pedidos
            .stream()
            .filter(pedido -> pedido.calcularTotalPedido() >= valor)
            .sorted(Comparator.comparing(Pedido::dataPedido))
            .map(Pedido::id)
            .toList();
    }

    public static void main(String[] args) {
        var clientes = List.of(
            new Cliente("Victor"),
            new Cliente("Stephanie"),
            new Cliente("Claudio"),
            new Cliente("Rodrigo")
        );

        var produtos = List.of(
            new Produto("PRODUTO A", 100, CategoriaProduto.BRINQUEDO),
            new Produto("PRODUTO B", 200, CategoriaProduto.ELETRONICO),
            new Produto("PRODUTO C", 300, CategoriaProduto.PAPELARIA)
        );

        var pedidos = 
            IntStream.range(0, clientes.size())
                .mapToObj(
                    indice -> {
                        int indiceAtual = indice <= 0 ? 0 : indice -1;

                        return new Pedido(
                            clientes.get(indiceAtual), 
                            produtos.subList(0, indice)
                        );
                    }
                )
                .toList();

        System.out.format(
            """
            Pedidos de cada cliente: %s

            Custo total de cada pedido: %s

            Nomes clientes que compraram brinquedos: %s

            IDs de pedidos com preços de acima de 500 ordenados por data: %s
            """,
            agruparQuantidadePedidos(pedidos),
            agruparCustoTotalPedidos(pedidos),
            agruparClientesPorTipoProduto(pedidos, CategoriaProduto.BRINQUEDO),
            buscarIDSPedidosPorValor(pedidos, 500)
        );
    }

}
