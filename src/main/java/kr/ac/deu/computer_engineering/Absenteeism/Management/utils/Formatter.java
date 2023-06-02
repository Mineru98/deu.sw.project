package kr.ac.deu.computer_engineering.Absenteeism.Management.utils;

import org.springframework.util.StringUtils;

public class Formatter {
    public static String convertPhoneNumber(String val) {
        if (StringUtils.isEmpty(val)) {
            return "";
        }
        if (val.length() == 10) {
            return val.substring(0, 3) + "-" + val.substring(3, 6) + "-" + val.substring(6);
        } else if (val.length() == 11) {
            return val.substring(0, 3) + "-" + val.substring(3, 7) + "-" + val.substring(7);
        } else {
            return val;
        }
    }
}
