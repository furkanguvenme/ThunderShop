package com.example.thundershop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comment", schema = "public")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description must not be empty")
    @NotNull(message = "Description must not be null")
    private String description;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "comment")
    private List<ProductReviews> productReviewsList = new ArrayList<>();
}
