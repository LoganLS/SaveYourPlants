package com.example.saveyourplants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class ProfilFragment extends Fragment {

    DatabaseHelper db;
    SessionManager session;
    TextView hello_username;
    Button button_deconnexion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        // SqLite database handler
        db=new DatabaseHelper(getActivity());

        // session manager
        session = new SessionManager(getActivity());

        //TEXT VIEW USERNAME
        hello_username=(TextView) rootView.findViewById(R.id.username);
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(SessionManager.KEY_USERNAME);
        hello_username.setText(username);

        //BUTTON DECONNEXION
        button_deconnexion=(Button) rootView.findViewById(R.id.button_deconnexion);
        button_deconnexion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                session = new SessionManager(getActivity());
                session.logoutUser();
            }
        });


        return rootView;
    }

}
