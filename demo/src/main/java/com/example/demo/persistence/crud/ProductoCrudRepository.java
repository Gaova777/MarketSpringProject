package com.example.demo.persistence.crud;

import com.example.demo.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    //una forma de llevar a cabo un query para en este caso coger todos los produtos, la forma clasica
    //seria:

/*    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
      List<Producto> getByCategoria(int idCategoria);
      En este caso no usamos los metodos Spring para hacer consultas, en el nombre del metodo, podemos
      nombrarlo como queramos, asi se llama, llamar de forma Nativa usando querys
*/
    /*
    en la linea 21 podemos hacer uso de los metodos de springrepository para hacer de la misma forma a como
    se suele hacer de forma nativa
    */
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List <Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
