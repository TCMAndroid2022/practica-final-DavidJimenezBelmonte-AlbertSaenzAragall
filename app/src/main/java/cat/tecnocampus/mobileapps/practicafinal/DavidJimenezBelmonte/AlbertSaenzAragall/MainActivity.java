package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall.ShowCharactersInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FormFragment formFragment;
    private String name;
    private String gender;
    private String birthYear;

    private static ArrayList<StarWarCharacter> starWarCharacterArrayList;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        starWarCharacterArrayList = new ArrayList<StarWarCharacter>();

        requestQueue = Volley.newRequestQueue(this);

        for(int i = 1; i <= 10; i++) getStarWarsCharactersAPI(i);

        fragmentManager = getSupportFragmentManager();
        addFormFragment();
    }

    private void getStarWarsCharactersAPI(int id) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://swapi.dev/api/people/" + id + "/", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        name = response.getString("name");
                        gender = response.getString("gender");
                        birthYear = response.getString("birth_year");

                        Log.d("ID:", String.valueOf(id));
                        Log.d("Nombre:", name);
                        Log.d("Genero:", gender);
                        Log.d("Año:", birthYear);
                        Log.d("*", "****************");

                        starWarCharacterArrayList.add(new StarWarCharacter(name, birthYear, gender));

                    } catch (JSONException ex) {
                        Log.d("SwA", "Error parsing json array");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {Log.d("SwA", "Error in request");}
            });
            requestQueue.add(jsonObjectRequest);
    }

    private void addFormFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        formFragment = new FormFragment();

        fragmentTransaction.replace(R.id.fragmentContainer, formFragment);
        fragmentTransaction.commit();
    }

    public static ArrayList<StarWarCharacter> getStarWarCharacterArrayList() {
        return starWarCharacterArrayList;
    }
}