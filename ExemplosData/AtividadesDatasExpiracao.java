package ExemplosData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

final class AtividadesDatasExpiracao {
    public void expiraEm(String data) throws Exception{
        var dataExpiracao = Utilitarios.transformarCaracterEmData(data);

        var dataAtual = Utilitarios.dataAtual();

        final Temporal[] datas = {
            dataExpiracao,
            dataAtual
        };

        for(var indice=0; indice < datas.length; indice++){
            Temporal valorData = datas[indice];

            if(valorData instanceof LocalDate valorSerComparado)
                datas[indice] = valorSerComparado.atStartOfDay();
        }

        long diferencaAnos = ChronoUnit.YEARS.between(datas[0], datas[1]);

        long diferencaMeses = ChronoUnit.MONTHS.between(datas[0], datas[1]);

        long diferencaDias = ChronoUnit.DAYS.between(datas[0], datas[1]);

        long diferencaHoras = ChronoUnit.HOURS.between(datas[0], datas[1]);

        long diferencaSegundos = ChronoUnit.SECONDS.between(datas[0], datas[1]);

        System.out.printf(
            "Seus dados irão se expirar em: " +
            "%d Anos, %d Meses, %d Dias, %d Horas e %d Segundos",
            diferencaAnos,
            diferencaMeses,
            diferencaDias,
            diferencaHoras,
            diferencaSegundos
        );
        
    }

    public static void main(String ...args) throws Exception{
        String dataPassada = args[0];

        var atividadeDataExpiracao = new AtividadesDatasExpiracao();

        atividadeDataExpiracao.expiraEm(dataPassada);
    }
}
