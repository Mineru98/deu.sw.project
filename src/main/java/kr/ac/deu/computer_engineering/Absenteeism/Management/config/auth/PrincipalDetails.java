package kr.ac.deu.computer_engineering.Absenteeism.Management.config.auth;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRole;
import lombok.Data;

import java.util.List;

@Data
public class PrincipalDetails {
    private User user;
    private List<UserAndRole> userAndRole;

    public PrincipalDetails(User user, List<UserAndRole> userAndRole) {
        this.user = user;
        this.userAndRole = userAndRole;
    }
}
