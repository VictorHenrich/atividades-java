package ExemplosStream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

final class AtividadeRankingNotasAlunos {
    private static record Aluno(String nome, double[] notas){
        public Aluno(String nome, List<Double> notas){
            this(nome, notas.stream().mapToDouble(item -> item).toArray());
        }

        public double calcularMedia(){
            return (
                Arrays
                    .stream(this.notas)
                    .average()
                    .orElse(0)
            );
        }

        @Override
        public final String toString() {
            return String.format(
                "<Aluno nome='%s' media=%f />", 
                this.nome, 
                this.calcularMedia()
            );
        }
    }

    private static record Ranking(Aluno[] alunos){
        public static int QUANTIDADE_ALUNOS_LISTA = 3;

        public Ranking(List<Aluno> alunos){
            this(alunos.toArray(Aluno[]::new));
        }

        public List<Aluno> capturarMelhoresAlunos(){
            return Arrays
                .stream(this.alunos)
                .sorted(Comparator.comparing(Aluno::calcularMedia).reversed())
                .limit(QUANTIDADE_ALUNOS_LISTA)
                .toList();
        }
    }

    public static void main(String[] args) {
        List<Aluno> alunos = List.of(
            new Aluno("Victor", List.of(9d, 9d, 9d, 9d)),
            new Aluno("Stephanie", List.of(8d, 8d, 8d, 8d)),
            new Aluno("Marcelo", List.of(7d, 7d, 7d, 7d)),
            new Aluno("Claudio", List.of(6d, 6d, 6d, 6d)),
            new Aluno("Marcos", List.of(5d, 5d, 5d, 5d))
        );

        var melhoresAlunos = new Ranking(alunos).capturarMelhoresAlunos();

        System.out.printf("Melhores alunos da classe: %s", melhoresAlunos);
    }

}
