package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FormFragment extends Fragment {

    private View rootView;
    private EditText nameField;
    private EditText surnameField;
    private EditText yearField;

    private String name;
    private String surname;
    private String year;

    private SharedPreferences prefs;

    private ResultFragment resultFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_form, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getText(R.string.Formulario));

        nameField = rootView.findViewById(R.id.NombreField);
        surnameField = rootView.findViewById(R.id.ApellidosField);
        yearField = rootView.findViewById(R.id.AñoField);
        loadPreferences();

        int orientation = getResources().getConfiguration().orientation;

        Button submitBtn = rootView.findViewById(R.id.SubmitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultFragment = new ResultFragment();

                name = nameField.getText().toString();
                surname = surnameField.getText().toString();
                year = yearField.getText().toString();

                if(checkFields(name, surname, year)){

                    savePreferences();

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

    private void savePreferences() {
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("name", name);
        editor.putString("surname", surname);
        editor.putString("year", year);
        editor.commit();
    }

    private void loadPreferences() {
        prefs = getContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        nameField.setText(prefs.getString("name", ""));
        surnameField.setText(prefs.getString("surname", ""));
        yearField.setText(prefs.getString("year", ""));
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