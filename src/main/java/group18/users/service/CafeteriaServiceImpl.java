package group18.users.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import group18.users.collection.Cafeteria;
import group18.users.repository.CafeteriaRepository;

@Service
public class CafeteriaServiceImpl implements CafeteriaService{

    @Autowired
    private CafeteriaRepository cafeteriaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String save(Cafeteria cafeteria){
        return cafeteriaRepository.save(cafeteria).getCafeteriaId();   
    }

    @Override
    public List<Cafeteria> getCafeteriaStartwith(String name) {
        return cafeteriaRepository.findByNameStartsWith(name);
    }

    @Override
    public void delete(String id) {
        cafeteriaRepository.deleteById(id);
    }

    @Override
    public List<Cafeteria> getByCafeteriaPrice(Integer minPrice, Integer maxPrice) {
        return cafeteriaRepository.findCafeteriaByPrice(minPrice, maxPrice);
    }

    @Override
    public Page<Cafeteria> search(String name, Integer minPrice, Integer maxPrice, String food, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList();

        if(name != null && !name.isEmpty()){
            criteria.add(Criteria.where("name").regex(name, "i"));
        }

        if(minPrice != null && maxPrice != null){
            criteria.add(Criteria.where("price").gte(minPrice).lte(maxPrice));
        }

        if(food != null && !food.isEmpty()){
            criteria.add(Criteria.where("food").is(food));
        }
        
        if(!criteria.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        Page<Cafeteria> cafes = PageableExecutionUtils.getPage(mongoTemplate.find(query, Cafeteria.class), pageable, () -> mongoTemplate.count(query.skip(0).limit(0), Cafeteria.class));

        return cafes;
    }

    @Override
    public List<Document> getHighestPriceByCafeteria() {

        UnwindOperation unwindOperation = Aggregation.unwind("name");

        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "price");

        GroupOperation groupOperation = Aggregation.group("name").first(Aggregation.ROOT).as("HighestPrice");

        Aggregation aggregation = Aggregation.newAggregation(unwindOperation, sortOperation, groupOperation);

        List<Document> cafeteria = mongoTemplate.aggregate(aggregation, Cafeteria.class,Document.class).getMappedResults();
        
        return cafeteria;
    }
}
