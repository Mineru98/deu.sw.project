package kr.ac.deu.computer_engineering.Absenteeism.Management.enums;

public enum TypeOfTask {
    ATTENDANCE("출근"),
    UNATTENDANCE("퇴근");

    final String value;

    TypeOfTask(String value) {
        this.value = value;
    }
}
