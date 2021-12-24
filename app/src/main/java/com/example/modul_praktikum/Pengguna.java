package com.example.modul_praktikum;

import java.io.Serializable;

public class Pengguna implements Serializable {
    int id;
    String nama;
    String notelp;
    String email;
    String jk;
    String pw;

    public Pengguna(int id, String nama, String notelp, String email, String jk, String pw) {
        this.id = id;
        this.nama = nama;
        this.notelp = notelp;
        this.email = email;
        this.jk = jk;
        this.pw = pw;
    }

    public Pengguna(String nama, String notelp, String email, String jk, String pw) {
        this.nama = nama;
        this.notelp = notelp;
        this.email = email;
        this.jk = jk;
        this.pw = pw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
