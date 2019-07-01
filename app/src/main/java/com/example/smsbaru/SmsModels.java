package com.example.smsbaru;

public class SmsModels {

    int id;
    String op;
    String no_tujuan;
    String status;
    String waktu;
    String keterangan;
    String nominal;
    String jenis;

    // contrustor(empty)
    public SmsModels() {
    }

    // constructor
    public SmsModels(String no_tujuan, String op, String nominal, String status, String waktu, String keterangan, String jenis) {

        this.no_tujuan = no_tujuan;
        this.op = op;
        this.nominal = nominal;
        this.status = status;
        this.waktu = waktu;
        this.keterangan = keterangan;
        this.jenis = jenis;
    }

    /*Setter and Getter*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
    public String getNominal(){
        return nominal;
    }

    public void  setNominal(String nominal){
        this.nominal = nominal;
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

    public String getKeterangan(){
        return keterangan;
    }

    public void setKeterangan(String keterangan){
        this.keterangan = keterangan;
    }
    public String getJenis(){
        return jenis;
    }
    public void setJenis(String jenis){
        this.jenis=jenis;
    }
}
