package com.example.productosmascotasbd.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.productosmascotasbd.model.Estadoenviobd;
import com.example.productosmascotasbd.model.Productomascotabd;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductomascotabdRepositoryTest {
    @Autowired
    private ProductomascotabdRepository productomascotabdRepository;

    @Test
    public void guardaProductoTest(){
        Productomascotabd productomascotabd = new Productomascotabd();

        productomascotabd.setId(1L);
        productomascotabd.setNombreProducto("Prueba Producto");
        productomascotabd.setDestino("Prueba Destino");
        productomascotabd.setUbicacionActual("En bodega");
        productomascotabd.setEstadoEnvio(Estadoenviobd.PENDIENTE);

        Productomascotabd resultado = productomascotabdRepository.save(productomascotabd);

        assertNotNull(resultado.getId());
        assertEquals("Prueba Producto", resultado.getNombreProducto());
        assertEquals("Prueba Destino", resultado.getDestino());
        assertNotNull(resultado.getUbicacionActual());
        assertEquals(Estadoenviobd.PENDIENTE, productomascotabd.getEstadoEnvio());
    }
}
