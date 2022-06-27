package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ResultFragment extends Fragment {

    View rootView;
    ImageView resultImg;
    private String id = "-1";
    StarWarCharacter starWarCharacter;
    TextView characterName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            rootView = inflater.inflate(R.layout.fragment_result_landscape, container, false);
        }else{
            rootView = inflater.inflate(R.layout.fragment_result, container, false);
        }
        resultImg = rootView.findViewById(R.id.Img_Result);
        characterName = rootView.findViewById(R.id.characterName);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getText(R.string.Resultado));

        //pillar numero de fragment 1
        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
        //pillar info pj en indice k llegue -1
        if(id.equals("0")){
            id = "10";
        }
        starWarCharacter = MainActivity.getCharacterFromList(Integer.parseInt(id) - 1);
        characterName.setText(starWarCharacter.getName());
        //cambiar imagen por la de firebase, el numero es el mismo
        applyImage(id);

        return rootView;
    }

    private void applyImage(String id) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = null;

        switch (id){
            case "11":
                gsReference = storage.getReferenceFromUrl("gs://practica-final-a1f1e.appspot.com/images/img-11.png");
                break;
            case "10":
                gsReference = storage.getReferenceFromUrl("gs://practica-final-a1f1e.appspot.com/images/img-10.jpg");
                break;
            default:
                gsReference = storage.getReferenceFromUrl("gs://practica-final-a1f1e.appspot.com/images/img" + id + ".jpg");
                break;
        }
        Glide.with(this).load(gsReference).into(resultImg);
    }
}