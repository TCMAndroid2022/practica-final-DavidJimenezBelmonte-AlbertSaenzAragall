package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FormFragment extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_form, container, false);

        Button submitBtn = rootView.findViewById(R.id.SubmitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, new ResultFragment());
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}