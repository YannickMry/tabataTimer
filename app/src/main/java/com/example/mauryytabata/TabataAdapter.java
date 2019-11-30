package com.example.mauryytabata;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mauryytabata.db.Tabata;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TabataAdapter extends RecyclerView.Adapter<TabataAdapter.TabataHolder> {

    private List<Tabata> tabatas = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TabataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabata_item, parent, false);
        return new TabataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TabataHolder holder, int position) {
        Tabata currentTabata = tabatas.get(position);
        holder.itemName.setText(currentTabata.getName());
        holder.itemPreparation.setText("Préparation : " + String.valueOf(currentTabata.getPreparation()));
        holder.itemTravail.setText("Travail : " + String.valueOf(currentTabata.getTravail()));
        holder.itemRepos.setText("Repos : " + String.valueOf(currentTabata.getRepos()));
        holder.itemReposLong.setText("Repos long : " + String.valueOf(currentTabata.getReposLong()));
        holder.itemSerie.setText("Nombre de série : " + String.valueOf(currentTabata.getSerie()));
        holder.itemRepetition.setText("Nombre de répétition : " + String.valueOf(currentTabata.getRepetition()));
        holder.itemBtnEdit.setTag(currentTabata);
    }

    @Override
    public int getItemCount() {
        return tabatas.size();
    }

    public void setTabatas(List<Tabata> tabatas){
        this.tabatas = tabatas;
        notifyDataSetChanged();
    }

    public Tabata getTabataAtPosition(int position){
        return tabatas.get(position);
    }

    class TabataHolder extends RecyclerView.ViewHolder{
        private TextView itemName;
        private TextView itemPreparation;
        private TextView itemTravail;
        private TextView itemRepos;
        private TextView itemReposLong;
        private TextView itemSerie;
        private TextView itemRepetition;

        private ImageView itemBtnEdit;

        public TabataHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPreparation = itemView.findViewById(R.id.item_preparation);
            itemTravail = itemView.findViewById(R.id.item_travail);
            itemRepos = itemView.findViewById(R.id.item_repos);
            itemReposLong = itemView.findViewById(R.id.item_repos_long);
            itemSerie = itemView.findViewById(R.id.item_serie);
            itemRepetition = itemView.findViewById(R.id.item_repetition);
            itemBtnEdit = itemView.findViewById(R.id.btn_edit_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(tabatas.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Tabata tabata);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
