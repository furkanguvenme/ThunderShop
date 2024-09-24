package com.example.thundershop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users", schema = "public")
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "Name must not be empty")
    @NotNull(message = "Name must not be null")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Surname must not be empty")
    @NotNull(message = "Surname must not be null")
    private String lastName;

    @NotBlank(message = "Phone number must not be empty")
    @NotNull(message = "Phone number must not be null")
    private String phone;

    @NotBlank(message = "Email must not be empty")
    @NotNull(message = "Email must not be null")
    private String email;

    @Column(name = "avatar_url")
    @NotBlank(message = "Avatar link must not be empty")
    @NotNull(message = "Avatar link must not be null")
    private String avatarUrl;

    @NotBlank(message = "Password must not be empty")
    @NotNull(message = "Password must not be null")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", schema = "public",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_address", schema = "public",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    @JsonIgnoreProperties("users")
    private List<Address> addresses = new ArrayList<>();

    public void addNewAddress(Address address){
        addresses.add(address);
    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "favourites", schema = "public", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "product_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "product_id"}))
    private List<Product> favourites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "user")
    private List<ProductReviews> productReviewsList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "cart", schema = "public", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> cartList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
