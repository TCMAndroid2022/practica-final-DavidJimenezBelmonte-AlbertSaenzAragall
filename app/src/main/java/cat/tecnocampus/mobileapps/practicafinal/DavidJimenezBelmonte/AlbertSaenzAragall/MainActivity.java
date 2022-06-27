package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FormFragment formFragment;
    ResultFragment resultFragment;

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
        initList();
        starWarCharacterArrayList.add(new StarWarCharacter("Hmm... Seems like the Force is still deciding", "1", "male"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        requestQueue = Volley.newRequestQueue(this);

        for(int i = 1; i <= 10; i++) getStarWarsCharactersAPI(i);

        fragmentManager = getSupportFragmentManager();

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            createLandscapeScreen();
        }else{
            addFormFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionmenu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.action_check){
            Intent intent = new Intent(this, ShowCharactersInfo.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createLandscapeScreen() {
        fragmentTransaction = fragmentManager.beginTransaction();
        formFragment = new FormFragment();
        fragmentTransaction.replace(R.id.formContainer, formFragment);
        fragmentTransaction.commit();

        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", "11");
        resultFragment.setArguments(bundle);
        fragmentTransaction2.replace(R.id.resultContainer, resultFragment);
        fragmentTransaction2.commit();
    }

    private void getStarWarsCharactersAPI(int id) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://swapi.dev/api/people/" + id + "/", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        name = response.getString("name");
                        gender = response.getString("gender");
                        birthYear = response.getString("birth_year");

                        starWarCharacterArrayList.set(id - 1, new StarWarCharacter(name, birthYear, gender));

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

    public static StarWarCharacter getCharacterFromList(int id){
        return starWarCharacterArrayList.get(id);
    }

    private void initList(){
        for(int i = 0 ; i <10; i++){
            starWarCharacterArrayList.add(i, new StarWarCharacter("Jabba the Hutt", "1994", "boloncho"));
        }
    }
}