package com.example.testipohja;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//@RunWith(AndroidJUnit4.class)
public class MainInstrumentedTest {

    public final Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();



    @Test
    public void useAppContext() {

        //assertNotNull(null); //SSL kokeillaan
        assertNotNull("Onko null","Tämä"); //SSL kokeillaan
        //assertNotNull("Onko null",null); //SSL kokeillaan

        // Context of the app under test.
        assertEquals("com.example.testipohja", appContext.getPackageName());
    }

    @Test
    public void kissaRepoInsertNull(){
        KissaRepository kissaRepository = new KissaRepository(appContext);
        KissaEntity k = null;
        assertFalse(kissaRepository.InsertKissa(k));
        //assertTrue(kissaRepository.InsertKissa(new KissaEntity()));
    }


    @Test
    public void kissaRepoInsertPuutteellisetTiedot(){
        KissaRepository kissaRepository = new KissaRepository(appContext);
        KissaEntity k = new KissaEntity();
        assertFalse(kissaRepository.InsertKissa(k));

        k.ika =-1;
        k.nimi ="Misse";
        k.omistaja="Testaaja Testinen";
        assertFalse(kissaRepository.InsertKissa(k));

        k.ika =2;
        k.nimi =null;
        k.omistaja="Testaaja Testinen";
        assertFalse(kissaRepository.InsertKissa(k));

        k.ika =2;
        k.nimi ="M";
        k.omistaja="Testaaja Testinen";
        assertFalse(kissaRepository.InsertKissa(k));

        k.ika =2;
        k.nimi ="Misse";
        k.omistaja="T";
        assertFalse(kissaRepository.InsertKissa(k));

        //assertTrue(kissaRepository.InsertKissa(new KissaEntity())); //SSL tämmänen ei  mee läpi kääntäjästä
    }

    @Test
    public void kissaRepoInsert(){
        KissaRepository kissaRepository = new KissaRepository(appContext);
        KissaEntity k = new KissaEntity();

        k.ika =2;
        k.nimi ="Misse";
        k.omistaja="Testaaja Testinen";
        assertTrue(kissaRepository.InsertKissa(k));

    }





}
