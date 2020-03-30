package com.example.testipohja;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface KissaDao {

    @Query("SELECT * FROM kissaentity order by id DESC")
    LiveData<List<KissaEntity>> haeKaikki();

    @Query("SELECT * FROM kissaentity where nimi LIKE :nimi LIMIT :lukumaara")
    LiveData<List<KissaEntity>> haeNimella(String nimi, int lukumaara);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void LaitaKissa(KissaEntity k);
}
