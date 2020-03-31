package com.example.testipohja;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PohjaFragment extends Fragment {

    private PohjaViewModel mViewModel;
    private EditText editText;
    private Button button;
    private TextView textView;


    public static PohjaFragment newInstance(String defaultTxt)
    {
        if (defaultTxt == null) { //SSL added this
            return null;
        }
        return new PohjaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pohja_fragment, container, false);
        button = v.findViewById(R.id.nappi); //SSL
        editText = v.findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "TestiProjekti", Toast.LENGTH_SHORT).show();
                //if the text in EditText is empty or longer than 20
                String eTxt = editText.getText().toString();
                checkTxt(eTxt);
            }
        });
        return v;
    }

    public boolean checkTxt(String txt){

        if (txt.trim().equals("")||txt.length()>20){
            return false;
        }
        return true;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PohjaViewModel.class);
    }



}
