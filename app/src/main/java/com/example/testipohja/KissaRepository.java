package com.example.testipohja;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class KissaRepository {
    KissaDao kissaDao;
    LiveData<List<KissaEntity>> kissaEntityLiveData;

    public KissaRepository(Context applicationContext) {
        this.kissaDao = Tietokanta.getDatabase(applicationContext).kissaDao();
        this.kissaEntityLiveData = kissaDao.haeKaikki();
    }

    //public void InsertKissa(KissaEntity k) {
    public boolean InsertKissa(KissaEntity k) { //SSL

        if (k==null){
            return false;
        }
        if (k.ika < 1  ){ //no jaa, voihan sitä kai olla 0 vuotiaskin...?
            return false;
        }
        if (k.nimi == null || k.nimi.length()<2){
            return false;
        }
        if (k.omistaja == null || k.omistaja.length()<2){
            return  false;
        }

        try {
            kissaDao.LaitaKissa(k);
            return true;
        } catch (Exception e)
        {
            return false;
        }

    }

    public boolean HaeParametreilla(String nimi, int lkm) {
        if (nimi == null || nimi.trim().equals("") || nimi.length()>60 || lkm <1 ){
            return false;
        } else {
            kissaDao.haeNimella(nimi, lkm);
            return true;
        }
    }
}
