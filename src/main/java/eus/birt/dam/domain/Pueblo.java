package eus.birt.dam.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import lombok.AllArgsConstructor;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"type",
"geometry",
"properties"
})

@Data
@AllArgsConstructor
@Document(collection="pueblos")

public class Pueblo {
		
	@JsonProperty("_id")
	private String id;
	@JsonProperty("id")
    private Integer establecimientoId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("geometry")
    private Geometry geometry;
	@JsonProperty("properties")
	private Properties properties; 

}
