package fr.esilv2020.moneyroom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HealthFragment extends Fragment {

    Button btnMedicalReport;
    Button btnSymptoms;
    Button teleconsult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_health, container, false);

        btnMedicalReport = (Button) v.findViewById(R.id.ficheMedical);
        btnSymptoms = (Button) v.findViewById(R.id.symptomes);
        teleconsult = (Button) v.findViewById(R.id.teleconsult);

        btnMedicalReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicalReport = new Intent(getActivity(),medical_report.class);
                startActivity(medicalReport);
                getActivity().finish();
            }
        });

        btnSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent symptoms = new Intent(getActivity(),symptoms.class);
                startActivity(symptoms);
                getActivity().finish();
            }
        });

        teleconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visio = new Intent(getActivity(),visioConf.class);
                startActivity(visio);
                getActivity().finish();
            }
        });

        return v;
    }
}
