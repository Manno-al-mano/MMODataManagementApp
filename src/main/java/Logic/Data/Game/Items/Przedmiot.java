package Logic.Data.Game.Items;

import Logic.Data.Game.Characters.Atrybut;
import Logic.Data.Game.Characters.AtrybutyPostaci;
import Logic.Data.Game.Characters.Postac;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Przedmiot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String nazwa;
    @Column(nullable = false)
    private int cena;
    protected Przedmiot(){}
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KategoriaPrzedmiotu kategoria;

    @OneToMany(mappedBy = "przedmiot")
    private List<PrzedmiotWEkwipunku> postaci;

    public Przedmiot(String name, int price, KategoriaPrzedmiotu kategoriaPrzedmiotu){
        nazwa=name;
        cena=price;
        kategoria = kategoriaPrzedmiotu;
        postaci = new ArrayList<>();
    }

    public String getNazwa() {
        return nazwa;
    }

    public void addPostac(Postac postac, int wrt){
        PrzedmiotWEkwipunku pwe = new PrzedmiotWEkwipunku(postac,this,wrt);
        postaci.add(pwe);
        if(!postac.getPrzedmioty().contains(pwe))
            postac.addPrzedmiot(pwe);

    }
    public void addPostac(PrzedmiotWEkwipunku pwe){
        postaci.add(pwe);
    }

    public List<PrzedmiotWEkwipunku> getPostaci() {
        return postaci;
    }
}
