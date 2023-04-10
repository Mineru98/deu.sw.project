package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Objects;

@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AllowanceOfRank")
public class AllowanceOfRank {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("금액")
    @Column(nullable = false)
    private Long amount;

    @Comment("지급구간(최소)")
    @Column(nullable = false)
    private Long minInterval;

    @Comment("지급구간(최대)")
    @Column(nullable = false)
    private Long maxInterval;

    @Comment("직급 Id")
    @JsonBackReference
    @JoinColumn(name = "rankId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Rank rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AllowanceOfRank that = (AllowanceOfRank) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
