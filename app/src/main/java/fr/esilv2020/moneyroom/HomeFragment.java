package fr.esilv2020.moneyroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {
    Button btnLogout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnLogout = (Button) view.findViewById(R.id.btnLogout);

       btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth;
                FirebaseAuth.AuthStateListener authStateListener;
                FirebaseAuth.getInstance().signOut();
                Intent home = new Intent(getActivity(),MainActivity.class);
                startActivity(home);
                getActivity().finish();
            }
        });
        return view;
    }
}
