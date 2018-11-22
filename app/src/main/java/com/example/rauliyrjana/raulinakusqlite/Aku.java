package com.example.rauliyrjana.raulinakusqlite;

public class Aku {
    private long id;
    private String kirjanNumero;
    private String kirjanNimi;
    private String hankinta;
    private String painos;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getKirjanNimi(){
        return kirjanNimi;
    }
    public void setKirjanNimi(String kirjanNimi){
        this.kirjanNimi = kirjanNimi;
    }
    public String getKirjanNumero(){
        return kirjanNumero;
    }
    public void setKirjanNumero(String kirjanNumero){
        this.kirjanNumero=kirjanNumero;
    }

    public String getHankinta(){
        return hankinta;
    }
    public void setHankinta(String hankinta){
        this.hankinta=hankinta;
    }

    public String getPainos(){
        return painos;
    }
    public void setPainos(String painos){
        this.painos=painos;
    }

    @Override
    public String toString() {
        return kirjanNumero + ". " + kirjanNimi + ". " + hankinta + ". " + painos ;
    }
}
