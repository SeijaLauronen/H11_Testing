package com.example.testipohja;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;

import androidx.fragment.app.FragmentManager;
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
public class MainInstrumentedTest<ActivityTestRule> {

    public final Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    //Zoom videolla n. kohdassa 25 min: (yritin, auttaisko tänne siirtäminen, ai auttanut)
    //MainActivity mainActivity = new MainActivity(); //SSL 31.3.2020 no ottaako se tämän vaan tänne??
    //NO KUN EI

    @Test
    public void useAppContext() {

        //assertNotNull(null); //SSL kokeillaan
        assertNotNull("Onko null","Tämä"); //SSL kokeillaan
        //assertNotNull("Onko null",null); //SSL kokeillaan

        // Context of the app under test.
        assertEquals("com.example.testipohja", appContext.getPackageName());
    }

//****************************Class MainActivity*********************************************
/*
setFragment() should fail if there is already a fragment in the FrameLayout and replace is false.
setFragment() should fail if there if fragment-parameter is null.
setFragment() should fail if fragment being placed is the same fragment.
*/

    //No en saanu apua tästäkään, ei mene tuo rule.juttukaan läpi ainakaan tässä
    //https://stackoverflow.com/questions/46458735/instrumented-unit-class-test-cant-create-handler-inside-thread-that-has-not-c
    /*
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);
    */

//TODO Tämä ei nyt mene läpi. Nyt sain menemään läpi, vielä on ne testikeissit kesken
    //Tämä virhe tuli ihan ekana, kun käsitteli "vain luokkana" ja yritti luoda uuden instanssin new:lla
    //Can't create handler inside thread Thread[Instr: androidx.test.runner.AndroidJUnitRunner,5,main]
    // that has not called Looper.prepare()
    @Test
    public void mainActivitySetFrag(){
        //31.3.2020 no olisko tästä apua: NO OLI, vihdoinkin!
        //https://fincoapps.com/java-lang-runtimeexception-cant-create-handler-inside-thread-that-has-not-called-looper-prepare/
        Looper.prepare();
        //Tämänkin piti olla vain luokka, eipä ollut
        MainActivity mainActivity = new MainActivity();

        //assertFalse(mainActivity.setFragment(PohjaFragment.newInstance(""),false));
        //mainActivity.setFragment(PohjaFragment.newInstance(""), false);
        assertFalse(mainActivity.setFragment(null, false));
    }
    //****************************KissaRepository*********************************************
    /*
    Class KissaRepository
    HaeParametreilla() should fail the test if nimi is null, empty or way too long.
    HaeParametreilla() should fail the test if lkm is less can 1.
    InsertKissa() should fail the test if entity does not have all attributes provided.
    InsertKissa() should fail the test if entity has nulls or nonsensical values.
    */

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

    @Test
    public void haeKissaParmasNotOK(){
        KissaRepository kissaRepository = new KissaRepository(appContext);

        assertFalse(kissaRepository.HaeParametreilla(null,1));
        assertFalse(kissaRepository.HaeParametreilla("",1));
        assertFalse(kissaRepository.HaeParametreilla("   ",1));
        String nimi = " Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle Kalle";
        assertFalse(kissaRepository.HaeParametreilla(nimi,1));
        assertFalse(kissaRepository.HaeParametreilla("Kalle",0));
        assertFalse(kissaRepository.HaeParametreilla("Kalle",-1));

    }
    @Test
    public void haeKissaParmasOK(){
        KissaRepository kissaRepository = new KissaRepository(appContext);
        assertTrue(kissaRepository.HaeParametreilla("Ville",5));
    }

    //****************************Class Tietokanta*********************************************
    /*
    getInstance() should return the same instance.
    getInstance() should not return null.
     */
    @Test
    public void instanssiDB(){
        Tietokanta db = Tietokanta.getDatabase(appContext);
        assertNotNull(db);

        Tietokanta db2 = Tietokanta.getDatabase(appContext);
        assertSame(db,db2);

        Tietokanta db3 =Tietokanta.getDatabaseDiffInstance(appContext);
        assertNotSame(db,db3);

    }
//************************* Class PohjaFragment************************************************
    /*
    newInstance() should fail if defaultTxt-parameter is null.
    The method executed by pressing the button should produce fail if the text in EditText is empty or longer than 20.
    */

    @Test
    public void instanssiFrag(){

        PohjaFragment pohjaFragment;
        assertNotNull(pohjaFragment = PohjaFragment.newInstance("text"));
        assertNull(pohjaFragment = PohjaFragment.newInstance(null));
    }

    @Test
    public void txtInFrag(){
        //Olisko tähän pitänyt laittaa jotenkin se, että kun nappia painetaan...?
        PohjaFragment pohjaFragment;
        pohjaFragment = PohjaFragment.newInstance("");
        assertFalse(pohjaFragment.checkTxt("1234567890123456789012345"));
        assertFalse(pohjaFragment.checkTxt("  "));
        assertTrue(pohjaFragment.checkTxt(" 34"));
    }


}
