package com.example.productosmascotasbd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productosmascotasbd.model.Productomascotabd;
import com.example.productosmascotasbd.repository.ProductomascotabdRepository;

@Service
public class ProductomascotabdServiceImpl implements ProductomascotabdService{
    @Autowired
    private ProductomascotabdRepository productomascotabdRepository;

    @Override
    public List<Productomascotabd> getAllProductomascotabds(){
        return productomascotabdRepository.findAll();
    }

    @Override
    public Optional<Productomascotabd> getProductomascotabdById(Long id){
        return productomascotabdRepository.findById(id);
    }

    @Override
    public Productomascotabd createEnvio(Productomascotabd productomascotabd) {
        return productomascotabdRepository.save(productomascotabd);
    }

    @Override
    public Productomascotabd updateEnvio(Long id, Productomascotabd productomascotabd) {
        if(productomascotabdRepository.existsById(id)){
            productomascotabd.setId(id);
            return productomascotabdRepository.save(productomascotabd);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEnvio(Long id) {
        productomascotabdRepository.deleteById(id);
    }
}
