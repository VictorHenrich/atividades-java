package ExemplosReflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;

final class AtividadeMostrarDadosObjeto {
    private static record Classe1(String valor, String valor2){}

    private static record Classe2(String dado, String dado2){}

    private static String apresentarObjeto(Object objeto){
        var dadosClasseObjeto = objeto.getClass();

        var dadosApresentacao = new StringBuilder(
            "<%s ".formatted(dadosClasseObjeto.getSimpleName())
        );

        Arrays
            .asList(dadosClasseObjeto.getDeclaredFields())
            .stream()
            .forEach(propriedade -> {
                try {
                    boolean propriedadeEstatica = Modifier.isStatic(propriedade.getModifiers());

                    propriedade.setAccessible(true);
                    
                    dadosApresentacao.append(
                        "%s=%s ".formatted(
                            propriedade.getName(),
                            propriedade.get(propriedadeEstatica ? null : objeto)
                        )
                    );

                } catch (IllegalAccessException erro) {
                    throw new RuntimeException(
                        "Falha ao tentar imprimir dado de propriedade", 
                        erro
                    );
                }
            });

            dadosApresentacao.append("/>");

        return dadosApresentacao.toString();
    }

    public static void main(String[] args) {
        var dadosObjeto1 = new Classe1("Valor 1", "Valor 2");

        var dadosObjeto2 = new Classe2("Dado 1", "Dado 2");

        System.out.println(apresentarObjeto(dadosObjeto1));

        System.out.println(apresentarObjeto(dadosObjeto2));
    }
}
