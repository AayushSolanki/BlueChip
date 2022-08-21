package com.example.bluechip.models;

public class voucher_ledger_model {

    String date;
    String voucher_type;
    String dr;
    String cr;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVoucher_type() {
        return voucher_type;
    }

    public void setVoucher_type(String voucher_type) {
        this.voucher_type = voucher_type;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public voucher_ledger_model(String date, String voucher_type) {
        this.date = date;
        this.voucher_type = voucher_type;

    }

    public voucher_ledger_model(String voucher_type) {
        this.voucher_type = voucher_type;
    }
}
