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
            }
        });

        return rootView;
    }

    private boolean checkFields(String name, String surname, String year){
        return checkName(name) && checkSurname(surname) && checkYear(year);
    }

    private boolean checkName(String word) {
        if(word == ""){
            nameField.setText("");
            nameField.setError("El nombre no puede estar vacío");
            return false;
        }
        char[] chars = word.toCharArray();
        for(char c : chars){
            if (Character.isDigit(c)){
                nameField.setText("");
                nameField.setError("Aqui hay k mirar como meterlo con el @string para los idiomas");
                return false;
            }
        }
        return true;
    }

    private boolean checkSurname(String word) {
        if(word == ""){
            nameField.setText("");
            nameField.setError("El apellido no puede estar vacío");
            return false;
        }
        char[] chars = word.toCharArray();
        for(char c : chars){
            if (Character.isDigit(c)){
                surnameField.setText("");
                surnameField.setError("Aqui hay k mirar como meterlo con el @string para los idiomas");
                return false;
            }
        }
        return true;
    }

    private boolean checkYear(String year) {
        if(year == ""){
            yearField.setText("");
            yearField.setError("El año no puede estar vacío");
            return false;
        }

        if(!(year.matches("[0-9]+"))){
            yearField.setText("");
            yearField.setError("El año debería estar sólo compuesto por números");
            return false;
        }

        int yearInt = Integer.parseInt(year);

        if(yearInt > 2022 || yearInt < 1900){
            yearField.setText("");
            yearField.setError("El año debe ser un número entre 1900 y 2022");
            return false;
        }

        return true;
    }
}