package group18.users.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import group18.users.collection.Cafeteria;


@Repository
public interface CafeteriaRepository extends MongoRepository<Cafeteria, String>{
 
    List<Cafeteria> findByNameStartsWith(String name);

    //List<Cafeteria> findByPriceBetween(Integer min, Integer max);

    @Query(value = "{ 'price' : {$gt : ?0, $lt : ?1}}",
            fields = "{food: 0}")
    List<Cafeteria> findCafeteriaByPrice(Integer min, Integer max);
}
