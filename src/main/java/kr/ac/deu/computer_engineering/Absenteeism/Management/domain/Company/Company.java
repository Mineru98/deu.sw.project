package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Company")
public class Company {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회사명")
    @Column(length = 64)
    private String companyName;

    @Comment("본사여부")
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private Boolean isMain;

    @Comment("회사 GPS 좌표 Lat")
    @Column(precision = 18, scale = 15, nullable = true)
    private Double lat;

    @Comment("회사 GPS 좌표 Lng")
    @Column(precision = 18, scale = 15, nullable = true)
    private Double lng;

    @Comment("급여정산날짜")
    private LocalDate settlementDate;

    @Comment("회사 주소")
    @Column(length = 512)
    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<User> userList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company that = (Company) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
