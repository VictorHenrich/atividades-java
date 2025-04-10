package ExemplosStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class AtividadeAlunosAprovados {
    private static record Aluno(String nome, List<Double> notas){
        public Aluno(String nome, double ...notas){
            this(nome, Arrays.stream(notas).boxed().collect(Collectors.toList()));
        }

        public double calcularMedia(){
            return this
                    .notas
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0d);
        }

        @Override
        public final String toString() {
            return String.format(
                "<Aluno nome='%s' media=%.2f />", 
                this.nome, 
                this.calcularMedia()
            );
        }
    }
    
    private static record SalaAula(List<Aluno> alunos, double notaMinimaAprovacao){
        public SalaAula(List<Aluno> alunos){
            this(alunos, 7);
        }

        public SalaAula(){
            this(new ArrayList<>());
        }

        public SalaAula adicionarAluno(Aluno aluno){
            this.alunos.add(aluno);

            return this;
        }

        public boolean validarAprovacao(Aluno aluno){
            return aluno.calcularMedia() >= this.notaMinimaAprovacao;
        }

        public List<Aluno> buscarAlunosAprovados(){
            return this
                    .alunos
                    .stream()
                    .filter(this::validarAprovacao)
                    .toList();
        }
    }

    public static void main(String[] args){
        var salaAula = new SalaAula();

        var alunosAprovados = salaAula
            .adicionarAluno(new Aluno("Victor", 10, 9, 8, 8))
            .adicionarAluno(new Aluno("Stephanie", 9, 9, 9, 9))
            .adicionarAluno(new Aluno("Marquinhos", 7, 7, 7, 7))
            .adicionarAluno(new Aluno("Claudio", 5, 5, 6, 7))
            .adicionarAluno(new Aluno("Felipe", 5, 5, 5, 5))
            .buscarAlunosAprovados();

        System.out.format("Alunos aprovados: %s", alunosAprovados);
    }
}
