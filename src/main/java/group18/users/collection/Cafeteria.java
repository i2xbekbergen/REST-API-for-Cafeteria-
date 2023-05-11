package group18.users.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "cafeteria")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cafeteria {
    
    @Id
    private String cafeteriaId;
    private String name;
    private String food;
    private Integer price;

}
