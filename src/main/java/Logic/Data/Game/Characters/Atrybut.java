package Logic.Data.Game.Characters;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Atrybut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nazwa", unique = true)
    private NazwaAtrybutu nazwa;



@OneToMany(mappedBy = "atrybut")
    private List<AtrybutyPostaci> postaci;

    public Atrybut(NazwaAtrybutu nazwaAtrybutu) {
        postaci = new ArrayList<>();
        nazwa = nazwaAtrybutu;
    }

    public List<AtrybutyPostaci> getPostaci() {
        return postaci;
    }

    public void addPostac(Postac postac, int wrt) {
        AtrybutyPostaci atrybutPostaci = new AtrybutyPostaci(this, postac, wrt);
        postaci.add(atrybutPostaci);
        if (postac.getAtrybuty().contains(atrybutPostaci))
            postac.addAtrybut(this, wrt);

    }
    private Atrybut(){}

}



