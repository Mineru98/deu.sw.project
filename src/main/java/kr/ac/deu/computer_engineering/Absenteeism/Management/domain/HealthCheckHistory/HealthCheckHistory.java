package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "HealthCheckHistory")
public class HealthCheckHistory {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("승인 여부")
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private Boolean isVerified;

    @Comment("검진 여부")
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private Boolean isCompleted;

    @Comment("적용연도")
    @Column(nullable = false)
    private LocalDate applyYear;

    @Comment("검진날짜")
    @Column()
    private LocalDate applyDate;

    @Comment("직원 Id")
    @JsonBackReference
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HealthCheckHistory that = (HealthCheckHistory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
