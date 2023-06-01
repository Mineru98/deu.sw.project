package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.Account;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistory;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.Schedule;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Statement.Statement;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.Team;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRole;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("로그인 계정")
    @Column(unique = true, length = 64, nullable = false)
    private String username;

    @Comment("비밀번호")
    @Column(length = 512)
    private String password;

    @Comment("직원명")
    @Column(length = 64, nullable = false)
    private String name;

    @Comment("입사일")
    @Column(nullable = false)
    private LocalDate dateOfJoin;

    @Comment("퇴사일")
    @Column(nullable = true)
    private LocalDate dateOfLeave;

    @Comment("생년월일")
    @Column(nullable = false)
    private LocalDate birthDay;

    @Comment("전화번호")
    @Column(length = 12)
    private String contactNumber;

    @Comment("네트워크 MAC 주소")
    @Column(length = 14)
    private String networkMacAddress;

    @Comment("관리자 여부")
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private Boolean isManager;

    @Comment("사무직 여부")
    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private Boolean isOfficer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Comment("권한 Id")
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserAndRole> userAndRoles;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<HealthCheckHistory> healthCheckHistoryList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Statement> statementList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accountList;

    @Comment("회사 Id")
    @JsonBackReference
    @JoinColumn(name = "companyId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;

    @Comment("부서 Id")
    @JsonBackReference
    @JoinColumn(name = "teamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Team team;

    @Comment("직급 Id")
    @JsonBackReference
    @JoinColumn(name = "rankId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Rank rank;

    @PrePersist
    public void createDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User that = (User) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
