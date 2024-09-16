package com.example.productosmascotasbd.service;

import java.util.List;
import java.util.Optional;

import com.example.productosmascotasbd.model.Productomascotabd;

public interface ProductomascotabdService {
    List<Productomascotabd> getAllProductomascotabds();
    Optional<Productomascotabd> getProductomascotabdById(Long id);
    Productomascotabd createEnvio(Productomascotabd productomascotabd);
    Productomascotabd updateEnvio(Long id, Productomascotabd productomascotabd);
    void deleteEnvio(Long id);
}
