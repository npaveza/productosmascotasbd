package com.example.productosmascotasbd.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productosmascotasbd.model.Productomascotabd;
import com.example.productosmascotasbd.service.ProductomascotabdService;

@RestController
@RequestMapping("/productomascota")
public class ProductomascotabdController {
    private static final Logger log = LoggerFactory.getLogger(ProductomascotabdController.class);
    @Autowired
    private ProductomascotabdService productomascotabdService;
    
    @GetMapping
    public CollectionModel<EntityModel<Productomascotabd>> getAllProductomascotabds() {
        List<Productomascotabd> productomascotabds = productomascotabdService.getAllProductomascotabds();
        log.info("GET /productomascota");
        log.info("Retornando todos los envíos");
        List<EntityModel<Productomascotabd>> productoenvioResources = productomascotabds.stream()
            .map(productomascotabd -> EntityModel.of(productomascotabd,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductomascotabdById(productomascotabd.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductomascotabds());
        CollectionModel<EntityModel<Productomascotabd>> resources = CollectionModel.of(productoenvioResources, linkTo.withRel("envios"));

        return resources;
    }

    @GetMapping("/{id}")
    public EntityModel<Productomascotabd> getProductomascotabdById(@PathVariable Long id) {
        Optional<Productomascotabd> productomascotabds = productomascotabdService.getProductomascotabdById(id);

        if (productomascotabds.isPresent()) {
            return EntityModel.of(productomascotabds.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductomascotabdById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductomascotabds()).withRel("Todos-envios"));
        } else {
            throw new ProductoNotFoundException("Envio not found with id: " + id);
        }
    }

    @PostMapping
    public EntityModel<Productomascotabd> createCita(@Validated @RequestBody Productomascotabd productomascotabd) {
        Productomascotabd createEnvio = productomascotabdService.createEnvio(productomascotabd);
            return EntityModel.of(createEnvio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductomascotabdById(createEnvio.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductomascotabds()).withRel("Toda-CitaMedica"));

    }

    @PutMapping("/{id}")
    public EntityModel<Productomascotabd> updateEnvio(@PathVariable Long id, @RequestBody Productomascotabd productomascotabd) {
        Productomascotabd updateEnvio = productomascotabdService.updateEnvio(id, productomascotabd);
        return EntityModel.of(updateEnvio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductomascotabdById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductomascotabds()).withRel("Todas-las-Citas"));

    }

    @DeleteMapping("/{id}")
    public void deleteEnvio(@PathVariable Long id){
        productomascotabdService.deleteEnvio(id);
    }


    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }

}