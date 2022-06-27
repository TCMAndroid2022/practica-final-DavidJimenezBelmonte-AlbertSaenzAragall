package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ResultFragment extends Fragment {

    private View rootView;
    private ImageView resultImg;
    private String id = "-1";
    private StarWarCharacter starWarCharacter;
    private TextView characterName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater, container);

        resultImg = rootView.findViewById(R.id.Img_Result);
        characterName = rootView.findViewById(R.id.characterName);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getText(R.string.Resultado));

        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
        if(id.equals("0")){
            id = "10";
        }
        starWarCharacter = MainActivity.getCharacterFromList(Integer.parseInt(id) - 1);
        characterName.setText(starWarCharacter.getName());

        applyImage(id);

        return rootView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            rootView = inflater.inflate(R.layout.fragment_result_landscape, container, false);
        }else{
            rootView = inflater.inflate(R.layout.fragment_result, container, false);
        }
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