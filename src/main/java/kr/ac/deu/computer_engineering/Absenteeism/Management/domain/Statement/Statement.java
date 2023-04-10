package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Statement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Statement")
public class Statement {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("정산기준날짜")
    @Column(nullable = false)
    private LocalDate standardDate;

    @Comment("금액")
    @Column(nullable = false)
    private Long amount;

    @Comment("직원 Id")
    @JsonBackReference
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Statement that = (Statement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
