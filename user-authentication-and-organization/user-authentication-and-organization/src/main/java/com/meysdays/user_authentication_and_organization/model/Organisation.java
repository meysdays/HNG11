package com.meysdays.user_authentication_and_organization.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "enter your organisation name")
    private String name;
    private String description;

    @ManyToMany(
            mappedBy = "organisations",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonBackReference
    private List<MyUser> user;

    public void addMyUser(MyUser myUser){
        this.user.add(myUser);
        myUser.getOrganisations().add(this);
    }
}
