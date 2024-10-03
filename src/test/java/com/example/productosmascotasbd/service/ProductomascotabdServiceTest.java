package com.example.productosmascotasbd.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productosmascotasbd.model.Estadoenviobd;
import com.example.productosmascotasbd.model.Productomascotabd;
import com.example.productosmascotasbd.repository.ProductomascotabdRepository;

@ExtendWith(MockitoExtension.class)
public class ProductomascotabdServiceTest {
    @InjectMocks
    private ProductomascotabdServiceImpl productomascotabdService;

    @Mock
    private ProductomascotabdRepository productomascotabdRepositoryMock;

    @BeforeEach
    public void setUp() {
        Productomascotabd productomascotabd = new Productomascotabd();
        productomascotabd.setId(1L);
        productomascotabd.setNombreProducto("Prueba Producto");
        productomascotabd.setDestino("Prueba Destino");
        productomascotabd.setUbicacionActual("En bodega");
        productomascotabd.setEstadoEnvio(Estadoenviobd.PENDIENTE);
    }

    @AfterEach
    public void tearDown() {
        Productomascotabd productomascotabd = null; // Liberar recursos si es necesario (en este caso solo limpiamos la referencia)
    }

    @Test
    public void guardaProductoTest(){
        Productomascotabd productomascotabd = new Productomascotabd();
        
        productomascotabd.setId(1L);
        productomascotabd.setNombreProducto("Prueba Producto");
        productomascotabd.setDestino("Prueba Destino");
        productomascotabd.setUbicacionActual("En bodega");
        productomascotabd.setEstadoEnvio(Estadoenviobd.PENDIENTE);


        when(productomascotabdRepositoryMock.save(any())).thenReturn(productomascotabd);

        Productomascotabd resultado = productomascotabdService.createEnvio(productomascotabd);

        assertNotNull(resultado.getId());
        assertEquals("Prueba Producto", resultado.getNombreProducto());
        assertEquals("Prueba Destino", resultado.getDestino());
        assertNotNull(resultado.getUbicacionActual());
        assertEquals(Estadoenviobd.PENDIENTE, productomascotabd.getEstadoEnvio());

        verify(productomascotabdRepositoryMock, times(1)).save(any());
    }
}
