package be.technifutur.poupier.repository.impl;

import be.technifutur.poupier.entities.Product;
import be.technifutur.poupier.repository.ProductRepository;
import be.technifutur.poupier.utils.EMFSharer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager manager = EMFSharer.getInstance()
            .createEntityManager();

    @Override
    public void add(Product toInsert) {
        manager.getTransaction().begin();
        manager.persist( toInsert );
        manager.getTransaction().commit();
    }

    @Override
    public Optional<Product> get(Long id) {
        Product p = manager.find(Product.class, id);
        manager.clear();
        return Optional.ofNullable( p );
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = manager.createQuery("SELECT prod FROM Product prod", Product.class);
        List<Product> products = query.getResultList();
        manager.clear();
        return products;
    }

    @Override
    public void update(Long id, Product entity) {

        entity.setId( id );

        // SELECT COUNT(*) FROM product WHERE product_id = ?
//        String qlQuery = "SELECT COUNT(p) FROM Product p WHERE p.id = ?1";
//        TypedQuery<Long> countQuery = manager.createQuery(qlQuery, Long.class);
//
//        countQuery.setParameter(1, id);
//
//        long count = countQuery.getSingleResult();

        if ( get(id).isPresent() ){
            manager.getTransaction().begin();
            manager.merge( entity );
            manager.getTransaction().commit();
        }
        else {
            throw new IllegalArgumentException("element does not exist");
        }

    }

    @Override
    public void remove(Long id) {

        manager.getTransaction().begin();

        Product product = manager.find(Product.class, id);
        if( product != null )
            manager.remove( product );

        manager.getTransaction().commit();

    }

    @Override
    public List<Product> getUnitPriceBetween(double min, double max) {


        // SELECT * FROM product WHERE unit_price BETWEEN ? AND ?
        String qlQuery = "SELECT p FROM Product p WHERE p.unitPrice BETWEEN ?1 AND ?2";
        TypedQuery<Product> query = manager.createQuery(qlQuery, Product.class);
        query.setParameter(1, min);
        query.setParameter(2, max);

        List<Product> products = query.getResultList();
        manager.clear();
        return products;

    }
}
