package org.sofka.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cyclist")
public class Ciclista {

    @Id
    private String id;
    private String name;
    private Integer number;
    private String teamCode;
    private String nationality;
}
