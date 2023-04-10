package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Role.Role;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "UserAndRole")
public class UserAndRole {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자 Id")
    @JsonBackReference
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Comment("권한 Id")
    @JsonBackReference
    @JoinColumn(name = "roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    public UserAndRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAndRole that = (UserAndRole) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
