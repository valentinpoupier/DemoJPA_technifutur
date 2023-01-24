package be.technifutur.poupier.repository;

import be.technifutur.poupier.entities.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>{

    List<Product> getUnitPriceBetween(double min, double max);

}
