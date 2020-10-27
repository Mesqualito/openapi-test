package com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    // explain it better & make it a required value in the Swagger UI (optional!):
    @ApiModelProperty(value = "This is the first name of a customer", required = true)
    @JsonProperty("firstname")
    private String firstName;

    @ApiModelProperty(required = true)
    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;

}
