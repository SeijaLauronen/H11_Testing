package com.example.testipohja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.security.PermissionCollection;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String defaultTxt = "";
        fragmentManager = getSupportFragmentManager();
        setFragment(PohjaFragment.newInstance(""), false);
    }

    //public void setFragment(Fragment fragment, boolean replace) {
    public boolean setFragment(Fragment fragment, boolean replace) {
        if (fragment ==null){
            return false;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.pohjaFrame, fragment);
        transaction.commit();
        return true;
    }
}
