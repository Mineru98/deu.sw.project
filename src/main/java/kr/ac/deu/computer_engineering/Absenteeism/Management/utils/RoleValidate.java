package kr.ac.deu.computer_engineering.Absenteeism.Management.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class RoleValidate {
    static public Long getUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return userId;
    }
    static public Long getTeamId(HttpSession session) {
        Long teamId = (Long) session.getAttribute("teamId");
        return teamId;
    }

    static public Boolean isRoleCeo(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        List<String> roleList = (List<String>) session.getAttribute("roleList");
        roleList = roleList.stream().filter(t -> t.equals("대표이사")).collect(Collectors.toList());
        return roleList.size() > 0;
    }

    static public Boolean isRoleManager(HttpSession session) {
        List<String> roleList = (List<String>) session.getAttribute("roleList");
        roleList = roleList.stream().filter(t -> t.equals("부서관리자")).collect(Collectors.toList());
        return roleList.size() > 0;
    }

    static public Boolean isRoleStaff(HttpSession session) {
        List<String> roleList = (List<String>) session.getAttribute("roleList");
        roleList = roleList.stream().filter(t -> t.equals("직원")).collect(Collectors.toList());
        return roleList.size() > 0;
    }
}
