package SegundaEntrega.api.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class FechaService {
    private static ZoneId zonaArgentina = ZoneId.of("America/Argentina/Buenos_Aires");
    private static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static int cantidadInvocaciones = 0;

public static String getFechaActual() {
    ZonedDateTime fechaActual = ZonedDateTime.now(zonaArgentina);
// setCantidadInvocaciones();
    cantidadInvocaciones++;
    return fechaActual.format(formato);
}

public int getCantidadInvocaciones() {
    return cantidadInvocaciones;
    }

    public void setCantidadInvocaciones() {
    cantidadInvocaciones++;
    }

}
