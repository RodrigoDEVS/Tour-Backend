package org.sofka.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "cyclist")
public class Ciclista {

    @Id
    private String id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer number;

    @NotEmpty
    private String teamCode;

    @NotEmpty
    private String nationality;
}
