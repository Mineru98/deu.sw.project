package kr.ac.deu.computer_engineering.Absenteeism.Management.enums;

public enum AuthorityRole {
    ROLE_CEO("대표이사"),
    ROLE_STAFF("직원"),
    ROLE_MANAGER("부서관리자");

    final String value;

    AuthorityRole(String value) {
        this.value = value;
    }
    public String value() {
        return this.value;
    }
}
