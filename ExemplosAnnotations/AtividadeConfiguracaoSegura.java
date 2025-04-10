package ExemplosAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

final class AtividadeConfiguracaoSegura {
    @Retention(RetentionPolicy.RUNTIME)
    private static @interface Grupo{
        String value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    private static @interface ConfiguracaoSegura{
        Grupo[] permissoes();
    }

    @ConfiguracaoSegura(permissoes={@Grupo("administrador"), @Grupo("usuario_comum")})
    private static class ClasseImplementada{}

    public static void main(String[] args) {
        Optional
            .ofNullable(
                ClasseImplementada
                    .class
                    .getAnnotation(ConfiguracaoSegura.class)
            )
            .ifPresent(System.out::println);

        List
            .of(
                ClasseImplementada
                    .class
                    .getAnnotationsByType(Grupo.class)
            )
            .stream()
            .forEach(System.out::println);
    }
}
