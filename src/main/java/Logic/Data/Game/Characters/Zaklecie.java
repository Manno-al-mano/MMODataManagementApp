package Logic.Data.Game.Characters;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Zaklecie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String opis;

    @Column(nullable = false)
    private int koszt;

    @ManyToMany(mappedBy = "zaklecia")
    private List<PostacMagiczna> postaci;

    public void addPostac(PostacMagiczna postac){
        postaci.add(postac);
        if (!postac.getZaklecia().contains(this)){
            postac.addZaklecie(this);
        }
    }
    public Zaklecie(String opis,int koszt  ){
        this.opis=opis;
        this.koszt=koszt;
        postaci = new ArrayList<>();
    }
    private Zaklecie(){}
    public List<PostacMagiczna> getPostaci() {
        return postaci;
    }
}
