package com.manojb.blog.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException  {
    String resourceName;
    String field;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String field, long fieldValue) {
        super(String.format("%s not found with this name %s : %s", resourceName, field, fieldValue));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldValue = fieldValue;
    }
}
