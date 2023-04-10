package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.Schedule;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Statement.Statement;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("계좌번호")
    @Column(length = 32)
    private String accountNumber;

    @Comment("은행이름")
    @Column(length = 64)
    private String nameOfBank;

    @Comment("설명")
    @Lob
    private String description;

    @Comment("회사 Id")
    @JsonBackReference
    @JoinColumn(name = "companyId")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Company company;

    @Comment("직원 Id")
    @JsonBackReference
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private User user;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void createDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account that = (Account) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
