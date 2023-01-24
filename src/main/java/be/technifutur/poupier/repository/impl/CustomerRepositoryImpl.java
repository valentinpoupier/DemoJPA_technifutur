package be.technifutur.poupier.repository.impl;

import be.technifutur.poupier.entities.Customer;
import be.technifutur.poupier.repository.CustomerRepository;
import jakarta.persistence.EntityManager;

public class CustomerRepositoryImpl extends AbstractCrudRepositoryImpl<Customer, String > implements CustomerRepository {

    protected CustomerRepositoryImpl(Class<Customer> entityClass, EntityManager manager) {
        super(entityClass, manager);
    }

    @Override
    protected void adaptId(String id, Customer customer) {
        customer.setId(id);
    }
}

