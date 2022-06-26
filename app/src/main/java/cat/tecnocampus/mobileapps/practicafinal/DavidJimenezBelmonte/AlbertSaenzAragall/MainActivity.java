package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FormFragment formFragment;
    ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AÑADIR IMAGEN
        /*ImageView firebaseTest = findViewById(R.id.firebase_test);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl("gs://practica-final-a1f1e.appspot.com/images/img1.jpg");

        Glide.with(this).load(gsReference).into(firebaseTest);*/

        fragmentManager = getSupportFragmentManager();
        addFormFragment();
    }

    private void addFormFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        formFragment = new FormFragment();

        fragmentTransaction.replace(R.id.fragmentContainer, formFragment);
        fragmentTransaction.commit();
    }

    private void addResultFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        resultFragment = new ResultFragment();

        fragmentTransaction.replace(R.id.fragmentContainer, resultFragment);
        fragmentTransaction.commit();
    }

}