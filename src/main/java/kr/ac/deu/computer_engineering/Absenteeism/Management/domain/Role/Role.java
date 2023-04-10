package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Role;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRole;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.AuthorityRole;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("권한명")
    @Enumerated(EnumType.STRING)
    @Column(length = 64, nullable = false, unique = true)
    private AuthorityRole roleName;

    @Comment("설명")
    @Column(length = 128)
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private List<UserAndRole> userAndRoles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role that = (Role) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
