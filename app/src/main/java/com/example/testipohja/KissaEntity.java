package com.example.testipohja;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class KissaEntity {
    @PrimaryKey(autoGenerate = true)
    int id;
    int ika;
    String nimi;
    String omistaja;
}
