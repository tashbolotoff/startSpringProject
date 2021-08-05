package kg.brigada.startspringproject.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")

public class User extends  Audit<String>{

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    UserRole userRole;

    String username;

    String password;

    Boolean isActive;
}
