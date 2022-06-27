package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FormFragment extends Fragment {

    View rootView;
    EditText nameField;
    EditText surnameField;
    EditText yearField;

    String name;
    String surname;
    String year;

    private ResultFragment resultFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_form, container, false);

        int orientation = getResources().getConfiguration().orientation;

        Button submitBtn = rootView.findViewById(R.id.SubmitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultFragment = new ResultFragment();

                nameField = rootView.findViewById(R.id.NombreField);
                name = nameField.getText().toString();

                surnameField = rootView.findViewById(R.id.ApellidosField);
                surname = surnameField.getText().toString();

                yearField = rootView.findViewById(R.id.AñoField);
                year = yearField.getText().toString();

                if(checkFields(name, surname, year)){

                    Bundle bundle = new Bundle();
                    bundle.putString("id", year.substring(3));
                    resultFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                        fragmentTransaction.replace(R.id.resultContainer, resultFragment);
                        fragmentTransaction.commit();
                    }else{
                        fragmentTransaction.replace(R.id.fragmentContainer, resultFragment);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

        return rootView;
    }

    private boolean checkFields(String name, String surname, String year){
        return checkName(name) && checkSurname(surname) && checkYear(year);
    }

    private boolean checkName(String word) {
        if(word.length() < 1){
            nameField.setText("");
            nameField.setError(getText(R.string.ErrorNombre1));
            return false;
        }
        char[] chars = word.toCharArray();
        for(char c : chars){
            if (Character.isDigit(c)){
                nameField.setText("");
                nameField.setError(getText(R.string.ErrorNombre2));
                return false;
            }
        }
        return true;
    }

    private boolean checkSurname(String word) {
        if(word.length() < 1){
            surnameField.setText("");
            surnameField.setError(getText(R.string.ErrorApellido1));
            return false;
        }
        char[] chars = word.toCharArray();
        for(char c : chars){
            if (Character.isDigit(c)){
                surnameField.setText("");
                surnameField.setError(getText(R.string.ErrorApellido2));
                return false;
            }
        }
        return true;
    }

    private boolean checkYear(String year) {
        if(year.length() < 1){
            yearField.setText("");
            yearField.setError(getText(R.string.ErrorAño1));
            return false;
        }

        if(!(year.matches("[0-9]+"))){
            yearField.setText("");
            yearField.setError(getText(R.string.ErrorAño2));
            return false;
        }

        int yearInt = Integer.parseInt(year);

        if(yearInt > 2022 || yearInt < 1900){
            yearField.setText("");
            yearField.setError(getText(R.string.ErrorAño3));
            return false;
        }

        return true;
    }
}