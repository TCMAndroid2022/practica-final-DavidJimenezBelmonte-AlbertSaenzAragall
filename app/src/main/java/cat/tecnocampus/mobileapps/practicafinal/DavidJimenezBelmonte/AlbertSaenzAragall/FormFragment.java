package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormFragment extends Fragment {

    View rootView;
    EditText nameField;
    EditText surnameField;
    EditText yearField;

    String name;
    String surname;
    String year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_form, container, false);

        Button submitBtn = rootView.findViewById(R.id.SubmitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nameField = rootView.findViewById(R.id.NombreField);
                name = nameField.getText().toString();

                surnameField = rootView.findViewById(R.id.ApellidosField);
                surname = surnameField.getText().toString();

                yearField = rootView.findViewById(R.id.AñoField);
                year = yearField.getText().toString();

                if(checkFields(name, surname, year)){
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, new ResultFragment());
                    fragmentTransaction.commit();
                }
                else
                {
                    Toast toast = Toast.makeText(getContext(), "ALGO HA IDO MAL", Toast.LENGTH_SHORT);
                    toast.show();
                    //AlertDialog?
                }
            }
        });

        return rootView;
    }

    private boolean checkFields(String name, String surname, String year){
        return checkNames(name) && checkNames(surname) && checkYear(year);
    }

    private boolean checkNames(String word) {
        char[] chars = word.toCharArray();
        for(char c : chars){
            if (Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    private boolean checkYear(String year) {
        if(!(year.matches("[0-9]+"))) return false;

        int yearInt = Integer.parseInt(year);

        if(yearInt > 2022 || yearInt < 1900) return false;

        return true;
    }
}