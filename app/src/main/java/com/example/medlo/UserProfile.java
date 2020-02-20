package com.example.medlo;

public class UserProfile {

    public String userName;
    public String userGender;
    public String userPhone_Number;
    public String userFamily_Phone_Number;
    public String userEmail;
    public String userPassword;
    public String userAddress1;
    public String userAddress2;

    public UserProfile(){

    }
    public UserProfile(String userAddress1, String userAddress2, String userEmail, String userPhone_Number, String userGender, String userName, String userPassword, String userFamily_Phone_Number)
    {
        this.userName=userName;
        this.userGender=userGender;
        this.userPhone_Number=userPhone_Number;
        this.userFamily_Phone_Number=userFamily_Phone_Number;
        this.userEmail=userEmail;
        this.userPassword=userPassword;
        this.userAddress1=userAddress1;
        this.userAddress2=userAddress2;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone_Number() {
        return userPhone_Number;
    }

    public void setUserPhone_Number(String userPhone_Number) {
        this.userPhone_Number = userPhone_Number;
    }

    public String getUserFamily_Phone_Number() {
        return userFamily_Phone_Number;
    }

    public void setUserFamily_Phone_Number(String userFamily_Phone_Number) {
        this.userFamily_Phone_Number = userFamily_Phone_Number;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress1() {
        return userAddress1;
    }

    public void setUserAddress1(String userAddress1) {
        this.userAddress1 = userAddress1;
    }

    public String getUserAddress2() {
        return userAddress2;
    }

    public void setUserAddress2(String userAddress2) {
        this.userAddress2 = userAddress2;
    }


}
