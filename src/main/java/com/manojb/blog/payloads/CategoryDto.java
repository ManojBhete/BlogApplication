package com.manojb.blog.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

public class CategoryDto {

    private int categoryId;
    @NotBlank
    @Size(min = 4, message = " minimum size of characters at least 4")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10, message = "minimum size of characters at least 10")
    private String categoryDescription;
}
