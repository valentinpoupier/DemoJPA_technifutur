package be.technifutur.poupier.repository;

import be.technifutur.poupier.entities.Supplier;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long>{

    List<Supplier> getFromRegion(String region);

    void vipForInCity(String city);

}
