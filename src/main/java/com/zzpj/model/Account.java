package com.zzpj.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Account")
@NamedQueries({
        @NamedQuery(name = "Account.findByLogin", query = "SELECT a FROM Account a WHERE a.login = :login")
})
public class Account {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "login", nullable = false, unique = true)
    @Size(max = 24)
    private String login;

    @NotNull
    @ToString.Exclude
    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @OneToMany
    @JoinTable(
            name = "Recipe_Account",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> favouriteRecipes = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "access_level", nullable = false, referencedColumnName = "id")
    private AccessLevel accessLevel;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
