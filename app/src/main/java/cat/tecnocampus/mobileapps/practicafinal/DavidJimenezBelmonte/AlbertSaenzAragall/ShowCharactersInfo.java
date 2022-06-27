package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ShowCharactersInfo extends AppCompatActivity {

    private ArrayList<StarWarCharacter> starWarCharactersList;
    private RecyclerView recyclerView;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_show_characters_info);
        recyclerView = findViewById(R.id.characters_recycler);
        starWarCharactersList = new ArrayList<>();
        getSupportActionBar().setTitle(getText(R.string.ListaEj));

        setStarWarsCharacterInfo();
        setAdapter();
    }

    @Override
    public boolean onSupportNavigateUp() {
         dialog = new AlertDialog.Builder(this).setTitle(R.string.Salir)
                .setMessage(R.string.AlertaSalir).setPositiveButton(R.string.Ok, null)
                .setNegativeButton(R.string.Cancelar, null).show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return true;
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(starWarCharactersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setStarWarsCharacterInfo() {
        starWarCharactersList.addAll(MainActivity.getStarWarCharacterArrayList());
    }
}