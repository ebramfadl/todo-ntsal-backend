package com.example.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @SequenceGenerator(sequenceName = "category_sequence",allocationSize = 1,name = "category_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    private Long id;

    private String title;
    private String description;

    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;
}