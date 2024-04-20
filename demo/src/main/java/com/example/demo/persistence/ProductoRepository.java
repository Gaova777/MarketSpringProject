package com.example.demo.persistence;

import com.example.demo.persistence.crud.ProductoCrudRepository;
import com.example.demo.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//con @Repository estereo tipo de spring fr, asi le indicamos a spring, que esta clase se conectara con la bdd
//con @Component es una generalizacion de este tipo de anotaciones, es decir que tambien se puede llegar a ahcer
@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;


    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List <Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(int idProducto){
        return  productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

}
