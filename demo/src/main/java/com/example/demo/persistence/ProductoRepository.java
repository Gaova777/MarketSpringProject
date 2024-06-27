package com.example.demo.persistence;

import com.example.demo.domain.Product;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.persistence.crud.ProductoCrudRepository;
import com.example.demo.persistence.entity.Producto;
import com.example.demo.persistence.mapper.ProductMapper;
import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
//con @Repository estereo tipo de spring fr, asi le indicamos a spring, que esta clase se conectara con la bdd
//con @Component es una generalizacion de este tipo de anotaciones, es decir que tambien se puede llegar a ahcer
@Repository
public class ProductoRepository implements ProductRepository {

    //si vemos, nosotros estamos declarando mas no instanciando el par de atributos productoCrudRepository y mapper, y como no se instancia
    //Java puede mandar un error de null Pointer exception, como son instanciados en ProductoRepository y no se crean se consideran como nulos
    // lo mejor es dejar que spring lo haga por nosotros, lo crea para evitar este problema colocando la notacion @Autowired esto solo se puede hacer
    // para los componentes relacionados a spring, como lo son en este caso
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }
    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto>  productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }



    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
       Optional <List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
       return productos.map(productos1 -> mapper.toProducts(productos1));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return  productoCrudRepository.findById(productId).map(prod -> mapper.toProduct(prod) );
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));

    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

}
