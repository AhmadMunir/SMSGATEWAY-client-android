package com.example.smsbaru;

public class SmsModels {

    int id;
    String isi_sms;
    String no_tujuan;
    String status;
    String waktu;

    // contrustor(empty)
    public SmsModels() {
    }

    // constructor
    public SmsModels(String no_tujuan, String isi_sms, String status, String waktu) {

        this.no_tujuan = no_tujuan;
        this.isi_sms = isi_sms;
        this.status = status;
        this.waktu = waktu;
    }

    /*Setter and Getter*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsi_sms() {
        return isi_sms;
    }

    public void setIsi_sms(String isi_sms) {
        this.isi_sms = isi_sms;
    }

    public String getNo_tujuan() {
        return no_tujuan;
    }

    public void setNo_tujuan(String no_tujuan) {
        this.no_tujuan = no_tujuan;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getWaktu(){
        return  waktu;
    }

    public void setWaktu(String waktu){
        this.waktu = waktu;
    }
}
