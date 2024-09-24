package com.example.thundershop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "products", schema = "public")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name")
    @NotBlank(message = "Team name must not be empty")
    @NotNull(message = "Team name must not be null")
    private String teamName;

    @NotNull(message = "Size must not be null")
    private int size;

    @NotNull(message = "Price must not be null")
    private double price;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Jersey must not be null")
    private JerseyType jerseyType;

    @NotNull(message = "Stock must not be null")
    private int stock;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "favourites", schema = "public", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "user_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "product_id"}))
    private List<User> userFavList;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "cart", schema = "public", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userCartList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "product")
    private List<ProductReviews> productReviewsList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_image", schema = "public", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "image_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"image_id", "product_id"}))
    private List<ProductImage> images = new ArrayList<>();

    public void addImage(ProductImage image){
        images.add(image);
    }
}
