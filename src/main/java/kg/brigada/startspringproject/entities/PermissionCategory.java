package kg.brigada.startspringproject.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "epid_permission_category")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PermissionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_category_id")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "name_ru")
    private String nameRu;

    @JsonManagedReference
    @OneToMany(mappedBy="permissionCategory")
    private Set<Permission> permissions;
}
