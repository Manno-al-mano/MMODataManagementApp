package Logic.Data.Game.Characters;

import Logic.Data.Users.Playerbase.Gracz;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PostacWsparcia extends Postac{

    @Column(nullable = false)
    private int wyleczoneObrazenia;

    public PostacWsparcia(String imie, int exp, int hp, Gracz gracz,int wo) {
        super(imie, exp, hp, gracz);
        wyleczoneObrazenia=wo;
    }
    private PostacWsparcia(){}

    @Override
    public  void  policzWynik(){
        System.out.println("Wynik Postaci to: "+getDoswiadczenie()+wyleczoneObrazenia*20);
    }

    public int getWyleczoneObrazenia() {
        return wyleczoneObrazenia;
    }

    public void setWyleczoneObrazenia(int wyleczoneObrazenia) {
        this.wyleczoneObrazenia = wyleczoneObrazenia;
    }
}
