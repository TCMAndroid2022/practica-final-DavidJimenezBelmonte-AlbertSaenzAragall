package cat.tecnocampus.mobileapps.practicafinal.DavidJimenezBelmonte.AlbertSaenzAragall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<StarWarCharacter> starWarCharacterList;

    public RecyclerAdapter(ArrayList<StarWarCharacter> starWarCharacterList){
        this.starWarCharacterList = starWarCharacterList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView characterName, gender, birth_year;

        public MyViewHolder(final View view){
            super(view);
            characterName = view.findViewById(R.id.name_character_api);
            gender = view.findViewById(R.id.gender_api);
            birth_year = view.findViewById(R.id.birth_year_api);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.characters_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String characterName = starWarCharacterList.get(position).getName();
        holder.characterName.setText(characterName);
        String gender = starWarCharacterList.get(position).getGender();
        holder.gender.setText(gender);
        String birth = starWarCharacterList.get(position).getAge();
        holder.birth_year.setText(birth);
    }

    @Override
    public int getItemCount() {
        return starWarCharacterList.size();
    }
}
