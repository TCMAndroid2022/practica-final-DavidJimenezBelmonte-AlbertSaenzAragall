package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ResultFragment extends Fragment {

    View rootView;
    ImageView resultImg;
    private String id = "-1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_result, container, false);

        resultImg = rootView.findViewById(R.id.Result_Img);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        //pillar numero de fragment 1
        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
        //pillar info pj en indice k llegue -1

        //cambiar imagen por la de firebase, el numero es el mismo

        return rootView;
    }

    private void applyImage(String id) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl("gs://practica-final-a1f1e.appspot.com/images/img1.jpg");

        Glide.with(this).load(gsReference).into(resultImg);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.actionmenu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.action_check){
            Intent intent = new Intent(getActivity(), ShowCharactersInfo.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}