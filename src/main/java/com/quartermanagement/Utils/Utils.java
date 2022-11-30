package com.quartermanagement.Utils;

import javafx.scene.control.Alert;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    public static LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }


    public static String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16));
            }
            generatedPassword = sb.toString();
        }   catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static void createDialog(Alert.AlertType type, String title, String headerText, String contentText) {
        Alert warning = new Alert(type);
        warning.setTitle(title);
        warning.setHeaderText(headerText);
        warning.setContentText(contentText);
        warning.show();
    }

    public static String convertDate(String date){
        String result;
        String[] date_split = date.split("-");
        result = date_split[2]+"/"+date_split[1]+"/"+date_split[0];
        return result;
    }

    public static String convertTime(String time){
        String result;
        String[] timeArr = time.split(" ");
        String[] date = timeArr[0].split("-");
        result = timeArr[1] + " "+ date[2]+"/"+date[1]+"/"+date[0];
        return result;
    }

    public static boolean isValidTime(String time)
    {
//        String regex = "^(2[0-3]{2}|[01]?[0-9]{2}):([0-5]?[0-9]{2})$";
        String regex = "^(2[0-3]|[1][0-9]|[0][0-9]):(0[0-9]|[1-5][0-9])$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(time);
        return m.matches();
    }

    public static boolean greaterTime(String startDate, String startTime, String endDate, String endTime){
        String[] startDateArr = startDate.split("-");
        String[] startTimeArr = startTime.split(":");
        String[] endDateArr = endDate.split("-");
        String[] endTimeArr = endTime.split(":");
        String starttime = startDateArr[0] + startDateArr[1] + startDateArr[2] + startTimeArr[0]+ startTimeArr[1];
        String endtime = endDateArr[0] + endDateArr[1] + endDateArr[2] + endTimeArr[0] + endTimeArr[1];
        if(Long.parseLong(endtime) > Long.parseLong(starttime)) return true;
        else return false;
    }
    public static boolean isCccd(String cccd)
    {
        String regex = "^\\d{12}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cccd);
        return !m.matches();
    }
}

