package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowCharactersInfo extends AppCompatActivity {

    private ArrayList<StarWarCharacter> starWarCharactersList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_show_characters_info);
        recyclerView = findViewById(R.id.characters_recycler);
        starWarCharactersList = new ArrayList<>();

        setStarWarsCharacterInfo();
        setAdapter();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(starWarCharactersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setStarWarsCharacterInfo() {
        //aquí irá la API
        starWarCharactersList.add(new StarWarCharacter("Darth Vader", "3", "Male"));
        starWarCharactersList.add(new StarWarCharacter("R2D2", "1904", "Robot"));
        starWarCharactersList.add(new StarWarCharacter("Leia", "27", "Female"));
    }
}