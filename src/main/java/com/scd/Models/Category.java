package com.scd.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private String code;
    private String name;
    private String description;
    private List<Category> subcategories;
    private List<Product> products;
}
