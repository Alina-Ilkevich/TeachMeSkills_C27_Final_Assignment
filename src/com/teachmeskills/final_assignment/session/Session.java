package com.teachmeskills.final_assignment.session;

import com.teachmeskills.final_assignment.consts.Consts;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public final class Session {
    private String accessToken;
    private Date expDate;

    public Session() {
        setAccessToken();
        setExpDate();
    }

    public boolean isSessionAlive() {
        if (this.accessToken.length() == Consts.ACCESS_TOKEN_LENGTH &&
                this.expDate.after(new Date())) {
            return true;
        } else {
            return false;
        }
    }

    private void setAccessToken() {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";
        this.accessToken = new Random().ints(Consts.ACCESS_TOKEN_LENGTH, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void setExpDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);
        this.expDate = calendar.getTime();
    }
}
