package Logic.Data.Game.Characters;

import jakarta.persistence.*;

@Entity
public class AtrybutyPostaci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postac_id")
    private Postac postac;

    @ManyToOne
    @JoinColumn(name = "atrybut_id")
    private Atrybut atrybut;

    @Column(nullable = false)
    private int wartosc;
    private AtrybutyPostaci(){}
    public AtrybutyPostaci(Atrybut atrybut,Postac postac,int val){
        this.atrybut=atrybut;
        this.postac=postac;
        wartosc=val;
    }

    public Postac getPostac() {
        return postac;
    }

    public int getWartosc() {
        return wartosc;
    }

    public Atrybut getAtrybut() {
        return atrybut;
    }
}
