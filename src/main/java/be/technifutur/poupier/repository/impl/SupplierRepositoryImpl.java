package be.technifutur.poupier.repository.impl;

import be.technifutur.poupier.entities.Supplier;
import be.technifutur.poupier.repository.SupplierRepository;
import be.technifutur.poupier.utils.EMFSharer;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class SupplierRepositoryImpl extends AbstractCrudRepositoryImpl<Supplier, Long> implements SupplierRepository {
    public SupplierRepositoryImpl() {
        super(
                Supplier.class,
                EMFSharer.getInstance().createEntityManager()
        );
    }

    @Override
    protected void adaptId(Long id, Supplier supplier) {
        supplier.setId(id);
    }

    @Override
    public List<Supplier> getFromRegion(String region) {
        String qlQuery = "SELECT s FROM Supplier s WHERE s.region = ?1";
        TypedQuery<Supplier> query = manager.createQuery(qlQuery, Supplier.class);

        query.setParameter(1, region);
        List<Supplier> lists = query.getResultList();
        manager.clear();
        return lists;
    }

    @Override
    public void vipForInCity(String city) {
//        vipForInCityV0(city);
        vipForInCityV1(city);
//        vipForInCityV2(city);
    }


    public void vipForInCityV0(String city) {
        String qlQuery = "SELECT s FROM Supplier s WHERE city = ?1";
        TypedQuery<Supplier> query = manager.createQuery(qlQuery, Supplier.class);
        query.setParameter( 1, city );
        List<Supplier> suppliers = query.getResultList();


        manager.getTransaction().begin();
        suppliers.forEach(s -> s.setCompanyName( s.getCompanyName() + " - VIP" ) );
        manager.getTransaction().commit();

        int affected = suppliers.size();

    }




    public void vipForInCityV1(String city) {
        String qlQuery = """
                        UPDATE Supplier s
                        SET s.companyName = concat(s.companyName, ' - VIP')
                        WHERE s.city = ?1
                        """;
        Query query = manager.createQuery(qlQuery);
        query.setParameter(1, city);

        manager.getTransaction().begin();
        int affected = query.executeUpdate();
        manager.getTransaction().commit();
    }





    public void vipForInCityV2(String city) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaUpdate<Supplier> criteriaUpdate = cb.createCriteriaUpdate(Supplier.class);

        Root<Supplier> root = criteriaUpdate.from(Supplier.class);

        criteriaUpdate
                .set("companyName", cb.concat( root.get("companyName"), " - VIP" ))
                .where( cb.equal(root.get("city"), city) );

        Query query = manager.createQuery( criteriaUpdate );

        manager.getTransaction().begin();
        int affected = query.executeUpdate();
        manager.getTransaction().commit();
    }


}
