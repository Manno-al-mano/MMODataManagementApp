package Logic.Data.Users.Moderation;

import Logic.Data.Users.Osoba;
import Logic.Data.Users.Playerbase.Gracz;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ModeratorCzatu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public ModeratorCzatu(){
    }
    @OneToMany(mappedBy = "moderatorCzatu")
    private List<Gracz> gracze;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    Osoba osoba;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "jezykimoderatorow",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "jezyk_id")
    )
    private List<Jezyk> jezyki;

    public ModeratorCzatu(Osoba osoba) {
        this.osoba = osoba;
        osoba.setModeratorCzatu(this);
        gracze=new ArrayList<>();
        jezyki = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void removeGracz(Gracz gracz){
        gracze.remove(gracz);
        if(gracz.getModeratorCzatu()==null)
            return;
        if(gracz.getModeratorCzatu().equals(this))
        gracz.setModeratorCzatu(null);
    }

    public List<Jezyk> getJezyki() {
        return jezyki;
    }

    public List<Gracz> getGracze() {
        return gracze;
    }

    public void addJezyk(Jezyk jezyk){
        if(!jezyk.getModerators().contains(this)){
           jezyk.addModerator(this);
        }
        if (!jezyki.contains(jezyk)){
            jezyki.add(jezyk);
        }
    }
    public void addGracz(Gracz gracz){
        if(!gracze.contains(gracz)){
            gracze.add(gracz);
        }
        if(!gracz.getModeratorCzatu().equals(this)){
            gracz.setModeratorCzatu(this);
        }
    }

    @Override
    public String toString() {
        return  osoba.toString();
    }
}
