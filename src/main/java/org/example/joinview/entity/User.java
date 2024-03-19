package org.example.joinview.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.joinview.config.Views;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Public.class, Views.Internal.class})
    @Null(message = "Id should be empty")
    private Long id;

    @Column(name = "name")
    @JsonView({Views.Public.class, Views.Internal.class})
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "last_name")
    @JsonView({Views.Public.class, Views.Internal.class})
    @NotEmpty(message = "Lastname should not be empty")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonView(Views.Public.class)
    private List<Order> orders;

    @Column(name = "date_of_create")
    @JsonView({Views.Public.class, Views.Internal.class})
    @Null(message = "Date of create should be empty")
    private LocalDateTime dateOfCreate;

    @Column(name = "date_of_last_update")
    @JsonView({Views.Public.class, Views.Internal.class})
    @Null(message = "Date of update should be empty")
    private LocalDateTime dateOfUpdate;

    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
        dateOfUpdate = LocalDateTime.now();
    }
}
