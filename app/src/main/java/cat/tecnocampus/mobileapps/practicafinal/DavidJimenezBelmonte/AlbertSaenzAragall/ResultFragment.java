package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultFragment extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_result, container, false);
        return rootView;
    }
}