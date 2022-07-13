package org.sofka.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "teams")
public class Equipo {

    @Id
    private String id;
    private String name;
    private String teamCode;
    private String country;

}
