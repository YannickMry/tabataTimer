package com.example.mauryytabata.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;

@Entity(tableName = "tabata_table")
public class Tabata implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "preparation")
    private int preparation;

    @ColumnInfo(name = "serie")
    private int serie;

    @ColumnInfo(name = "repetition")
    private int repetition;

    @ColumnInfo(name = "travail")
    private int travail;

    @ColumnInfo(name = "repos")
    private int repos;

    @ColumnInfo(name = "repos_long")
    private int reposLong;

    @Ignore
    private static final String[] tabataStep = {"preparation", "serie", "repetition", "travail", "repos", "repos_long"};

    @Ignore
    private HashMap<String, String> tabataName;

    @Ignore
    private HashMap<String, String> tabataColor;

    @Ignore
    private ArrayList<Integer> tabataTimer;

    @Ignore
    private ArrayList<String> tabataSequence;

    @Ignore
    public Tabata (){}

    public Tabata(String name, int preparation, int serie, int repetition, int travail, int repos, int reposLong) {
        this.name = name;
        this.preparation = preparation;
        this.serie = serie;
        this.repetition = repetition;
        this.travail = travail;
        this.repos = repos;
        this.reposLong = reposLong;
    }

    protected Tabata(Parcel in) {
        id = in.readInt();
        name = in.readString();
        preparation = in.readInt();
        serie = in.readInt();
        repetition = in.readInt();
        travail = in.readInt();
        repos = in.readInt();
        reposLong = in.readInt();
    }

    public static final Creator<Tabata> CREATOR = new Creator<Tabata>() {
        @Override
        public Tabata createFromParcel(Parcel in) {
            return new Tabata(in);
        }

        @Override
        public Tabata[] newArray(int size) {
            return new Tabata[size];
        }
    };

    public int add(String tag){
        int number = 0;
        if (tag == tabataStep[0] && this.preparation < 600) {
            setPreparation(preparation + 1);
            number = getPreparation();
        }
        if (tag == tabataStep[1] && this.serie < 600) {
            setSerie(serie + 1);
            number = getSerie();
        }
        if (tag == tabataStep[2] && this.repetition < 600) {
            setRepetition(repetition + 1);
            number = getRepetition();
        }
        if (tag == tabataStep[3] && this.travail < 600) {
            setTravail(travail + 1);
            number = getTravail();
        }
        if (tag == tabataStep[4] && this.repos < 600) {
            setRepos(repos + 1);
            number = getRepos();
        }
        if (tag == tabataStep[5] && this.reposLong < 600) {
            setReposLong(reposLong + 1);
            number = getReposLong();
        }
        return number;
    }

    public int remove(String tag){
        int number = 0;
        if (tag == tabataStep[0] && this.preparation > 1) {
            setPreparation(preparation - 1);
            number = getPreparation();
        }
        if (tag == tabataStep[1] && this.serie > 1) {
            setSerie(serie - 1);
            number = getSerie();
        }
        if (tag == tabataStep[2] && this.repetition > 1) {
            setRepetition(repetition - 1);
            number = getRepetition();
        }
        if (tag == tabataStep[3] && this.travail > 1) {
            setTravail(travail - 1);
            number = getTravail();
        }
        if (tag == tabataStep[4] && this.repos > 1) {
            setRepos(repos - 1);
            number = getRepos();
        }
        if (tag == tabataStep[5] && this.reposLong > 1) {
            setReposLong(reposLong - 1);
            number = getReposLong();
        }
        return number;
    }

    /**
     * Création d'une séance
     */
    public ArrayList<String> createSeance() {

        tabataSequence = new ArrayList<>();
        tabataTimer = new ArrayList<>();

        tabataTimer.add(preparation);
        tabataSequence.add("preparation");
        // Creation du nombre de séquence
        for (int s = 1; s <= serie; s++){
            // Nombre de cycle
            for (int c = 1; c <= repetition; c++){
                tabataTimer.add(travail);
                tabataSequence.add("travail");
                // Si c'est le dernier tours, on ne fait pas de repos
                if (c != repetition){
                    tabataTimer.add(repos);
                    tabataSequence.add("repos");
                }
            }
            // Si c'est le dernier tours, on ne fait pas de repos long
            if (s != serie){
                tabataTimer.add(reposLong);
                tabataSequence.add("repos_long");
            }
        }
        return tabataSequence;
    }

    public String[] getTabataStep(){
        return this.tabataStep;
    }

    public Integer getCurrentTimer(Integer index){ return this.tabataTimer.get(index); }

    public String getCurrentName(String step){
        initTabataName();
        return this.tabataName.get(step);
    }

    public String getCurrentColor(String step) {
        initTabataColor();
        return this.tabataColor.get(step);
    }

    public void initTabataName(){
        this.tabataName = new HashMap<>();
        tabataName.put("preparation", "Préparation");
        tabataName.put("serie", "Série");
        tabataName.put("repetition", "Répétition");
        tabataName.put("travail", "Travail");
        tabataName.put("repos", "Repos");
        tabataName.put("repos_long", "Repos long");
    }

    public void initTabataColor(){
        this.tabataColor = new HashMap<>();
        tabataColor.put("preparation", "#689F38");
        tabataColor.put("travail", "#EC4E4E");
        tabataColor.put("repos", "#F8B809");
        tabataColor.put("repos_long", "#5588C1");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreparation() {
        return preparation;
    }

    public void setPreparation(int preparation) {
        this.preparation = preparation;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public int getTravail() {
        return travail;
    }

    public void setTravail(int travail) {
        this.travail = travail;
    }

    public int getRepos() {
        return repos;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }

    public int getReposLong() {
        return reposLong;
    }

    public void setReposLong(int reposLong) {
        this.reposLong = reposLong;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nom séance : " + this.name + ", Préparation : " + this.preparation + ", Série : " + this.serie + ", Répétition : " + this.repetition + ", Travail : " + this.travail + ", Repos : " + this.repos + ", Repos long : " + this.reposLong;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(preparation);
        dest.writeInt(serie);
        dest.writeInt(repetition);
        dest.writeInt(travail);
        dest.writeInt(repos);
        dest.writeInt(reposLong);
    }
}
