package com.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PropertyValue {

    private final String name;
    private final Object value;
}
