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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Public.class, Views.Internal.class})
    @Null(message = "Id should be empty")
    private Long id;

    @Column(name = "title")
    @JsonView({Views.Public.class, Views.Internal.class})
    @NotEmpty(message = "Title should not be empty")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER,
               cascade = CascadeType.REFRESH)
    @JsonView(Views.Internal.class)
    @NotEmpty(message = "User should not be empty")
    private User user;

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
