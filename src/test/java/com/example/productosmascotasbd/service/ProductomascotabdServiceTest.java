package com.example.productosmascotasbd.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

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

    private Productomascotabd producto1, producto2, producto3;

    @BeforeEach
    public void setUp() {
        // Creación del primer producto (ID: 1)
        producto1 = new Productomascotabd();
        producto1.setId(1L);
        producto1.setNombreProducto("Producto 1");
        producto1.setDestino("Destino 1");
        producto1.setUbicacionActual("Ubicación 1");
        producto1.setEstadoEnvio(Estadoenviobd.PENDIENTE);

        // Creación del segundo producto (ID: 2)
        producto2 = new Productomascotabd();
        producto2.setId(2L);
        producto2.setNombreProducto("Producto 2");
        producto2.setDestino("Destino 2");
        producto2.setUbicacionActual("Ubicación 2");
        producto2.setEstadoEnvio(Estadoenviobd.EN_CAMINO);

        // Creación del tercer producto (ID: 3)
        producto3 = new Productomascotabd();
        producto3.setId(3L);
        producto3.setNombreProducto("Producto 3");
        producto3.setDestino("Destino 3");
        producto3.setUbicacionActual("Ubicación 3");
        producto3.setEstadoEnvio(Estadoenviobd.ENTREGADO);
    }

    @AfterEach
    public void tearDown() {
        producto1 = producto2 = producto3 = null;
    }

    @Test
    public void guardaProductoTest() {
        // Simulación de guardar producto 1
        when(productomascotabdRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        when(productomascotabdRepositoryMock.save(any())).thenReturn(producto1);

        if (productomascotabdRepositoryMock.findById(1L).isEmpty()) {
            Productomascotabd resultado1 = productomascotabdService.createEnvio(producto1);

            assertNotNull(resultado1.getId());
            assertEquals("Producto 1", resultado1.getNombreProducto());
            assertEquals("Destino 1", resultado1.getDestino());
            assertNotNull(resultado1.getUbicacionActual());
            assertEquals(Estadoenviobd.PENDIENTE, resultado1.getEstadoEnvio());

            verify(productomascotabdRepositoryMock, times(1)).save(producto1);
        }

        // Simulación de guardar producto 2
        when(productomascotabdRepositoryMock.save(any())).thenReturn(producto2);
        Productomascotabd resultado2 = productomascotabdService.createEnvio(producto2);

        assertNotNull(resultado2.getId());
        assertEquals("Producto 2", resultado2.getNombreProducto());
        assertEquals("Destino 2", resultado2.getDestino());
        assertNotNull(resultado2.getUbicacionActual());
        assertEquals(Estadoenviobd.EN_CAMINO, resultado2.getEstadoEnvio());

        // Simulación de guardar producto 3
        when(productomascotabdRepositoryMock.save(any())).thenReturn(producto3);
        Productomascotabd resultado3 = productomascotabdService.createEnvio(producto3);

        assertNotNull(resultado3.getId());
        assertEquals("Producto 3", resultado3.getNombreProducto());
        assertEquals("Destino 3", resultado3.getDestino());
        assertNotNull(resultado3.getUbicacionActual());
        assertEquals(Estadoenviobd.ENTREGADO, resultado3.getEstadoEnvio());
    }

    @Test
    public void modificaProductoTest() {
        if (productomascotabdRepositoryMock.findById(2L).isPresent()) {
            producto2.setNombreProducto("Producto 2");
            producto2.setDestino("Destino 2");

            when(productomascotabdRepositoryMock.save(any())).thenReturn(producto2);

            Productomascotabd resultado = productomascotabdService.updateEnvio(2L, producto2);

            assertEquals("Producto 2", resultado.getNombreProducto());
            assertEquals("Destino 2", resultado.getDestino());

            verify(productomascotabdRepositoryMock, times(1)).save(producto2);
        }
    }

    @Test
    public void eliminaProductoTest() {
        // Simulación de encontrar producto con ID 3
        when(productomascotabdRepositoryMock.findById(3L)).thenReturn(Optional.of(producto3));

        if (productomascotabdRepositoryMock.findById(3L).isPresent()) {
            doNothing().when(productomascotabdRepositoryMock).deleteById(3L);

            productomascotabdService.deleteEnvio(3L);

            verify(productomascotabdRepositoryMock, times(1)).deleteById(3L);
        }
    }
}