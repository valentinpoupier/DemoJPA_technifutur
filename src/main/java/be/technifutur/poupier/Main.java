package be.technifutur.poupier;

import be.technifutur.poupier.repository.SupplierRepository;
import be.technifutur.poupier.repository.impl.SupplierRepositoryImpl;
import be.technifutur.poupier.utils.EMFSharer;

public class Main {
    public static void main(String[] args) {

        SupplierRepository repository = new SupplierRepositoryImpl();

        repository.vipForInCity("Montréal");

        EMFSharer.getInstance().close();
    }
}