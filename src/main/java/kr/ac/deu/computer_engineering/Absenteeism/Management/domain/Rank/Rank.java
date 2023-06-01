package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRank;
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
@Table(name = "Rank")
public class Rank {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("직급명")
    @Column(length = 64)
    private String rankName;

    @JsonManagedReference
    @OneToMany(mappedBy = "rank", fetch = FetchType.LAZY)
    private List<AllowanceOfRank> allowanceOfRankList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rank that = (Rank) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
