package be.technifutur.poupier;

import be.technifutur.poupier.entities.Category;
import be.technifutur.poupier.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("northwind");

        EntityManager em = emf.createEntityManager();

        // GET ONE
        Product product = em.find(Product.class, 33L);

        System.out.println(product.getName());
        System.out.println(product.getCategory().getCategoryName());
        System.out.println(product.getSupplier().getCompanyName());
        System.out.println(product.getQttPerUnit());
        System.out.println("...........");

        // GET ALL
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> list = query.getResultList();
        list.forEach(p -> System.out.println(p.getName()));

        //  UPDATE
        em.getTransaction().begin();
        product.setQttPerUnit("500g");
        em.getTransaction().commit();

        System.out.println(product.getName());
        System.out.println(product.getCategory().getCategoryName());
        System.out.println(product.getSupplier().getCompanyName());
        System.out.println(product.getQttPerUnit());
        System.out.println("...........");

        // INSERT
        Category cat = new Category();
        cat.setId((short)18);
        cat.setCategoryName("Others");
        cat.setDescription("Product with default category");

        em.getTransaction().begin();
        // em.persist(cat); // si id != id déjà existant, sinon exception
        em.merge(cat); // si id != id déjà existant, sinon update
        em.getTransaction().commit();

        //DELETE
        em.getTransaction().begin();
        cat = em.merge(cat);
        em.remove(cat);
        em.getTransaction().commit();

        emf.close();
    }
}