package group18.users.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group18.users.collection.Cafeteria;
import group18.users.service.CafeteriaService;

@RestController
@RequestMapping("/cafeteria")
public class CafeteriaController {
    
    @Autowired
    private CafeteriaService cafeteriaService;

    @PostMapping
    public String save(@RequestBody Cafeteria cafeteria){
        return cafeteriaService.save(cafeteria);
    }

    @GetMapping
    public List<Cafeteria> getCafeteriaStartWith(@RequestParam("name") String name){
        return cafeteriaService.getCafeteriaStartwith(name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        cafeteriaService.delete(id);
    }

    @GetMapping("/price")
    public List<Cafeteria> getByCafeteriaPrice(@RequestParam Integer minPrice, @RequestParam Integer maxPrice){
        return cafeteriaService.getByCafeteriaPrice(minPrice, maxPrice);
    }

    @GetMapping("/search")
    public Page<Cafeteria> searchCafeteria(@RequestParam(required = false) String name, 
                                            @RequestParam(required = false) Integer minPrice,
                                            @RequestParam(required = false) Integer maxPrice,
                                            @RequestParam(required = false) String food,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return cafeteriaService.search(name, minPrice, maxPrice, food, pageable);
    }


    @GetMapping("/highestPrice")
    public List<Document> getHighestPrice(){
        return cafeteriaService.getHighestPriceByCafeteria();
    }
}
