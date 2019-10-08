package ar.edu.unahur.obj2;

import ar.edu.unahur.obj2.proveedores.Amadeus;
import ar.edu.unahur.obj2.proveedores.Sabre;
import ar.edu.unahur.obj2.proveedores.Worldspan;
import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.*;

public class OtaTest {
    private Ota ota;

    @BeforeTest
    public void setup() {
        Sabre sabre = new Sabre();
        AdaptadorSabre adaptadorSabre = new AdaptadorSabre(sabre);

        Amadeus amadeus = new Amadeus();
        AdaptadorAmadeus adaptadorAmadeus = new AdaptadorAmadeus(amadeus);

        Worldspan worldspan = new Worldspan();
        AdaptadorWorldspan adaptadorWorldspan = new AdaptadorWorldspan(worldspan);

        Map<String, Proveedor> proveedores = new HashMap<>();
        proveedores.put("Sabre", adaptadorSabre );
        proveedores.put("Amadeus", adaptadorAmadeus);
        proveedores.put("Worldspan", adaptadorWorldspan);

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        ota = new Ota(distribuidorDeTrafico);
    }

    @org.testng.annotations.Test
    public void testBuscarVuelos() {
        DateTime fecha = new DateTime("2019-12-13");

        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");
    }

    @org.testng.annotations.Test
    public void testReservar() {
        DateTime fecha = new DateTime("2019-12-13");

        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");

        Vuelo elegido =  vuelos.get(0);
        Set<Pasajero> pasajeros = Stream.of(new Pasajero("Juan", "Pérez", 40)).collect(Collectors.toSet());

        Boleto boleto = ota.reservar(elegido, pasajeros );

        assertEquals(boleto.getVuelo(), elegido);
    }
}
