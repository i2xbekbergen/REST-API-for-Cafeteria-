package group18.users.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.bson.Document;
import org.springframework.data.domain.Page;

import group18.users.collection.Cafeteria;

public interface CafeteriaService {

    String save(Cafeteria cafeteria);

    List<Cafeteria> getCafeteriaStartwith(String name);

    void delete(String id);

    List<Cafeteria> getByCafeteriaPrice(Integer minPrice, Integer maxPrice);

    Page<Cafeteria> search(String name, Integer minPrice, Integer maxPrice, String food, Pageable pageable);

    List<Document> getHighestPriceByCafeteria();
    
}
