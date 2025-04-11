package ExemplosReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;

final class AtividadeCapturarGettersDaClasse {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    private static @interface CapturaGetters{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    private static @interface IgnoraMetodo{}

    private static <T> void mostrarGetters(T objeto){
        var objetoClasse = objeto.getClass();

        List
            .of(objetoClasse.getDeclaredMethods())
            .stream()
            .filter(metodo -> {
                return (
                    metodo.getName().startsWith("get") &&
                    metodo.getParameterCount() == 0 &&
                    metodo.getDeclaredAnnotation(IgnoraMetodo.class) == null
                );
            })
            .forEach(metodo -> {
                try {
                    metodo.setAccessible(true);
                    
                    System.out.println(
                        metodo.invoke(
                            Modifier.isStatic(metodo.getModifiers()) ? null : objeto
                        )
                    );

                } catch (IllegalAccessException | InvocationTargetException erro) {
                    throw new RuntimeException("Falha ao mapear dados das propriedades da classe");
                }
            });
    }

    @CapturaGetters
    private static class ClasseImplementada{
        private final int propriedade1 = 0;

        private final String propriedade2 = "TESTE";

        private final Object propriedade3 = new Object();
        
        private final List<String> propriedade4 = List.of("String1", "String2", "String3");

        @SuppressWarnings("unused")
        public int getPropriedade1() {
            return propriedade1;
        }

        @SuppressWarnings("unused")
        public String getPropriedade2() {
            return propriedade2;
        }

        @IgnoraMetodo
        public Object getPropriedade3() {
            return propriedade3;
        }

        @SuppressWarnings("unused")
        public List<String> getPropriedade4() {
            return propriedade4;
        }
    }

    public static void main(String[] args) {
        var objetoImplementando = new ClasseImplementada();

        mostrarGetters(objetoImplementando);
    }
}
