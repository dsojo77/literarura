package com.desafio2.literarura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") String idioma,
        @JsonAlias("authors") String autor) {

}
