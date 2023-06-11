package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.AuthorityRole;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.TypeOfTask;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("설명")
    @Lob
    private String description;

    @Comment("접속 IP")
    @Column(length = 15, nullable = true)
    private String ip;

    @Comment("GPS 좌표 Lat")
    @Column(precision = 18, scale = 15, nullable = true)
    private Double lat;

    @Comment("GPS 좌표 Lng")
    @Column(precision = 18, scale = 15, nullable = true)
    private Double lng;

    @Comment("승인여부")
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private Boolean isVerified;

    @Comment("일정종류")
    @Enumerated(EnumType.STRING)
    @Column(length = 64, nullable = false)
    private TypeOfTask typeOfTask;

    @Comment("반영날짜")
    private LocalDateTime applyDateTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Comment("직원 Id")
    @JsonBackReference
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @PrePersist
    public void createDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Schedule that = (Schedule) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
