package com.bliss.csc.sw_helper;

import android.widget.EditText;

public class MemberInfo {
    private String name;
    private String hakbun;
    private String PhoneNumber;
    private String birthday;

    public MemberInfo(String name, String hakbun, String PhoneNumber, String birthday){
        this.name = name;
        this.hakbun = hakbun;
        this.PhoneNumber = PhoneNumber;
        this.birthday = birthday;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getHakbun(){
        return this.hakbun;
    }
    public void setHakbun(String hakbun){
        this.hakbun = hakbun;
    }

    public String getPhoneNumber(){
        return this.PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.PhoneNumber = phoneNumber;
    }

    public String getBirthday(){
        return this.birthday;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }


}
