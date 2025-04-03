package ExemplosData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

final class Utilitarios {
    private static final String[] FORMATOS_DATA = {
        "yyyy-MM-dd",
        "yyyy/MM/dd",
        "dd-MM-yyyy",
        "dd/MM/yyyy",
        "yyyy.MM.dd",
        "dd.MM.yyyy"
    };

    private static final String[] FORMATOS_HORA = {
        "HH:mm:ss",
        "HH:mm",
        "hh:mm:ss",
        "hh:mm",
        "hh:mm:ss z",
        "HH:mm:ss z",
        "HH:mm:ss Z",
        "HH:mm:ss XXX"
    };

    public static Temporal transformarCaracterEmData(String data) throws Exception{
        for(String formatoData: FORMATOS_DATA){
            for(String formatoHora: FORMATOS_HORA){
                var formato = (
                    new StringBuilder()
                        .append(formatoData)
                        .append(" ")
                        .append(formatoHora)
                        .toString()
                );

                var formatador = DateTimeFormatter.ofPattern(formato);

                try{
                    return LocalDateTime.parse(data, formatador);
                    
                }catch(Exception localDatetimeError){}

                try{
                    return ZonedDateTime.parse(data, formatador);

                }catch(Exception zonedDatetimeError){}
                
            }

            try{
                var formatador = DateTimeFormatter.ofPattern(formatoData);

                return LocalDate.parse(data, formatador);

            }catch(Exception error){}
        }

        throw new Exception(
            String.format("Não foi converter caracter '%s' em objeto data!", data)
        );
    }

    public static ZonedDateTime dataAtual(String zona){
        return ZonedDateTime.now(ZoneId.of(zona));
    }

    public static ZonedDateTime dataAtual(){
        return dataAtual("America/Sao_Paulo");
    }
}
