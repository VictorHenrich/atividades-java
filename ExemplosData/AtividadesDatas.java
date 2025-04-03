package ExemplosData;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;


final class AtividadesDatas{
    public void mostrarTempoPassado(String data, String dataAtual) throws Exception{
        System.out.printf("Data passada: %s\n", data);
        
        System.out.printf("Data atual: %s\n", dataAtual);

        Temporal[] listaDatas = {
            Utilitarios.transformarCaracterEmData(dataAtual),
            Utilitarios.transformarCaracterEmData(data)
        };

        for(int indice=0; indice < listaDatas.length; indice++){
            var valorData = listaDatas[indice];

            if(valorData instanceof LocalDate)
                listaDatas[indice] = ((LocalDate) valorData).atStartOfDay();
        }

        var duracao = Duration.between(listaDatas[0], listaDatas[1]);

        System.out.println(
            "Quantidade de dias: " + duracao.toDays() +
            "\nQuantidade de horas: " + duracao.toHours() +
            "\nQuantidade em minutos: " + duracao.toMinutes()
        );
    }

    public void mostrarTempoPassado(String data) throws Exception{
        this.mostrarTempoPassado(
            data, 
            Utilitarios.dataAtual().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
        );
    }

    public static void main(String[] args) throws Exception{
        String data = args[0], dataAtual = null;

        var atividade = new AtividadesDatas();

        if(args.length > 1){
            dataAtual = args[1];

            atividade.mostrarTempoPassado(data, dataAtual);

        }else
            atividade.mostrarTempoPassado(data);
    }
}