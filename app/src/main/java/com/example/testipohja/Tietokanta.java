package com.example.testipohja;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {KissaEntity.class}, version = 1, exportSchema = false)
public abstract class Tietokanta extends RoomDatabase {
    public abstract KissaDao kissaDao();

    private static volatile Tietokanta INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static Tietokanta getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Tietokanta.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Tietokanta.class, "tietokanta")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Tietokanta getDatabaseDiffInstance(final Context context) {

            synchronized (Tietokanta.class) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Tietokanta.class, "tietokanta")
                            .build();

            }

        return INSTANCE;
    }




}