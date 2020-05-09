package com.example.saveyourplants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.saveyourplants.R;

public class HomeFragment extends Fragment {

    Button button_apporter_conseils;
    Button button_demander_aide;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //APPORTER CONSEILS
        button_apporter_conseils=(Button) rootView.findViewById(R.id.button_apporter_conseils);
        button_apporter_conseils.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new ApporterConseilsFragment()).commit();
            }
        });

        //DEMANDER AIDE
        button_demander_aide=(Button) rootView.findViewById(R.id.button_demander_aide);
        button_demander_aide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new DemanderAideFragment()).commit();
            }
        });
        return rootView;
    }
}
