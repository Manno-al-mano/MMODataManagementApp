package Logic.Data.Users.Playerbase;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Klan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String nazwa;
    @Column(nullable = false)
    private String symbol;

    @OneToMany(mappedBy = "klan")
    private List<Gracz> gracze;

    @OneToOne
    @JoinColumn(name = "przywodcaKlanu")
    private PrzywodcaKlanu przywodcaKlanu;

    public Klan(String nazwa, String symbol) {
        this.nazwa = nazwa;
        this.symbol = symbol;
        gracze = new ArrayList<>();
    }
    private Klan(){}

    public List<Gracz> getGracze() {
        return gracze;
    }
    public void addPlayer(Gracz gracz){
      if(gracze.contains(gracz))  {
          return;
      }
      gracze.add(gracz);
      if(gracz.getKlan()==null){
          gracz.setKlan(this);
      return;
      }
      if(!gracz.getKlan().equals(this)){
          gracz.setKlan(this);
      }

    }

    public void setPrzywodcaKlanu(PrzywodcaKlanu przywodcaKlanu) {
        this.przywodcaKlanu = przywodcaKlanu;
        if(przywodcaKlanu.getKlan()==null){
            przywodcaKlanu.setKlan(this);
            return;
        }
        if(!przywodcaKlanu.getKlan().equals(this)){
           przywodcaKlanu.setKlan(this);
        }
    }
}
