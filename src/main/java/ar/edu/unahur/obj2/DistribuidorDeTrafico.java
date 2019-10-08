package ar.edu.unahur.obj2;

import java.util.Map;
import java.util.Random;

public class DistribuidorDeTrafico {

    private Map<String, Proveedor> proveedores;

    public DistribuidorDeTrafico(Map<String, Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    private Random random = new Random();

    public Proveedor proveedor() {
        String proveedor;

        switch (random.nextInt(3)) {
            case 0:proveedor = "Amadeus";
            case 1:proveedor = "Sabre";
            case 2: proveedor = "Worldspan";
            default:proveedor = "Amadeus";
        }
        return proveedores.get(proveedor);
    }
}
