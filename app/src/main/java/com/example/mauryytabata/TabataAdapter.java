package com.example.mauryytabata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mauryytabata.db.Tabata;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TabataAdapter extends RecyclerView.Adapter<TabataAdapter.TabataHolder> {

    private List<Tabata> tabatas = new ArrayList<>();

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
        holder.itemPreparation.setText(String.valueOf(currentTabata.getPreparation()));
    }

    @Override
    public int getItemCount() {
        return tabatas.size();
    }

    public void setTabatas(List<Tabata> tabatas){
        this.tabatas = tabatas;
        notifyDataSetChanged();
    }

    class TabataHolder extends RecyclerView.ViewHolder{
        private TextView itemName;
        private TextView itemPreparation;

        public TabataHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPreparation = itemView.findViewById(R.id.item_preparation);
        }
    }
}
