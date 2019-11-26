package com.example.mauryytabata.db;

public class Tabata {

    private int id;
    private String name;
    private int preparation;
    private int serie;
    private int repetition;
    private int travail;
    private int repos;
    private int reposLong;
    private static final String[] tabataStep = {"preparation", "serie", "repetition", "travail", "repos", "repos_long"};

    public Tabata() {
    }

    public Tabata(String name, int preparation, int serie, int repetition, int travail, int repos, int reposLong) {
        this.name = name;
        this.preparation = preparation;
        this.serie = serie;
        this.repetition = repetition;
        this.travail = travail;
        this.repos = repos;
        this.reposLong = reposLong;
    }

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
            setRepos(reposLong - 1);
            number = getRepos();
        }
        if (tag == tabataStep[5] && this.reposLong > 1) {
            setReposLong(reposLong - 1);
            number = getReposLong();
        }
        return number;
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
}
