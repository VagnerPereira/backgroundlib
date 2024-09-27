package com.example.backgroundlib.modelsBanco;

public class GameData {

    public int level;
    public float barraFome;
    public float barraSede;
    public float barraBanheiro;
    public float barraSono;

    public GameData(int level, float barraFome, float barraSede, float barraBanheiro, float barraSono) {
        this.level = level;
        this.barraFome = barraFome;
        this.barraSede = barraSede;
        this.barraBanheiro = barraBanheiro;
        this.barraSono = barraSono;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getBarraFome() {
        return barraFome;
    }

    public void setBarraFome(float barraFome) {
        this.barraFome = barraFome;
    }

    public float getBarraSede() {
        return barraSede;
    }

    public void setBarraSede(float barraSede) {
        this.barraSede = barraSede;
    }

    public float getBarraBanheiro() {
        return barraBanheiro;
    }

    public void setBarraBanheiro(float barraBanheiro) {
        this.barraBanheiro = barraBanheiro;
    }

    public float getBarraSono() {
        return barraSono;
    }

    public void setBarraSono(float barraSono) {
        this.barraSono = barraSono;
    }
}
