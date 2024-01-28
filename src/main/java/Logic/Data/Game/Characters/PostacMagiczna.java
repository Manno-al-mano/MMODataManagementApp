package Logic.Data.Game.Characters;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PostacMagiczna extends Aspekt{
    @Column(nullable = false)
    private int mana;

    public PostacMagiczna(int mana){
        this.mana=mana;
        zaklecia = new ArrayList<>();
    }

    public void addZaklecie(Zaklecie zakl){
    zaklecia.add(zakl);
    if (!zakl.getPostaci().contains(this)){
            zakl.addPostac(this);
        }

    }
    private PostacMagiczna(){}
    public void setMana(int mana) {
        this.mana = mana;
    }

    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(
            name = "ZakleciaPostaci",
            joinColumns = @JoinColumn(name = "postac_id"),
            inverseJoinColumns = @JoinColumn(name = "zaklecie_id")
    )
    List<Zaklecie> zaklecia;

    public List<Zaklecie> getZaklecia() {
        return zaklecia;
    }
}
