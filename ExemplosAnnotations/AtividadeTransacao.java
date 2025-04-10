package ExemplosAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

final class AtividadeTransacao {
    @Retention(RetentionPolicy.SOURCE)
    @Target({ ElementType.CONSTRUCTOR, ElementType.METHOD })
    private static @interface Transacao{
        boolean reverter() default false;
    }

    private static class ClasseComDecorator{
        @Transacao
        public ClasseComDecorator(){}

        @Transacao(reverter = true)
        public void metodoSemFuncionalidade(){}
    }
    public static void main(String[] args) {
        List.of(
            ClasseComDecorator
                .class
                .getAnnotationsByType(Transacao.class)
        )
        .stream()
        .forEach(System.out::print);
        
        Optional
            .ofNullable(
                ClasseComDecorator
                    .class
                    .getAnnotation(Transacao.class)
            )
            .ifPresent(
                anotacao -> System.out.format(
                    "Dados da anotação unica: %s", 
                    anotacao
                )
            );
    }
}
