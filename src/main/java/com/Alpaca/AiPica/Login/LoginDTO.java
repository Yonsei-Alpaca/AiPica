package com.Alpaca.AiPica.Login;

import org.apache.ibatis.type.Alias;

@Alias("user")
// 데이터 전달 클래스
public class LoginDTO {
    String ID;
    String PW;

    public LoginDTO(String ID, String PW) {
        this.ID = ID;
        this.PW = PW;
    }

    public String getID() {
        return this.ID;
    }

    public String getPW() {
        return this.PW;
    }

    public void setID(final String ID) {
        this.ID = ID;
    }

    public void setPW(final String PW) {
        this.PW = PW;
    }

    public String toString() {
        return "LoginDTO(ID=" + this.getID() + ", PW=" + this.getPW() + ")";
    }
}
