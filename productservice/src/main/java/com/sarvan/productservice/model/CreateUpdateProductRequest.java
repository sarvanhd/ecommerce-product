package com.sarvan.productservice.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUpdateProductRequest {
    @NotBlank(message = "product name is empty")
    String name;
    String description;
    @NotBlank(message = "category is empty")
    String category;
    String subCategory;
    @Min(value = 0)
    double price;
}
