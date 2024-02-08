package Logic.Data.Game.Characters;

import Constants.PersistenceNames;
import Logic.Data.Game.Items.Przedmiot;
import Logic.Data.Game.Items.PrzedmiotWEkwipunku;
import Logic.Data.Users.Playerbase.Gracz;
import Logic.DatabaseOperations.DatabaseManager;
import jakarta.persistence.*;
import javafx.geometry.Pos;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Postac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imie;
    @Column(nullable = false)
    private int doswiadczenie;
    @Column(nullable = false)
    private int hp;
    @Column(nullable = false)
    private int lvl;
    @Column (nullable = true)
    private int zloto;

    private static double averageLevel;

    public static double getAverageLevel() {
        averageLevel = DatabaseManager.getInstance().takeAverageLevel();
        return averageLevel;
    }

    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "gracz_id")
    private Gracz gracz;

    @OneToMany(mappedBy = "postac",cascade = {CascadeType.ALL})
    private List<PrzedmiotWEkwipunku> przedmioty;

    @OneToMany(mappedBy = "postac", cascade = CascadeType.ALL)
    private List<AtrybutyPostaci> atrybuty;

    @OneToOne(mappedBy = "postac", cascade = CascadeType.ALL)
   private Aspekt aspekt;


    public Postac(String imie, int exp, int hp, Gracz gracz) {
          this.imie = imie;
        this.doswiadczenie = exp;
        this.hp = hp;
        this.gracz = gracz;
        przedmioty = new ArrayList<>();
        atrybuty = new ArrayList<>();
        calcLevel();
        gracz.addPostac(this);
    }

    public void calcLevel(){
        lvl = (int)Math.log(doswiadczenie);
    }

    public void policzWynik(){
        System.out.println("Wynik Postaci to: "+doswiadczenie);
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getDoswiadczenie() {
        return doswiadczenie;
    }

    public void setDoswiadczenie(int doswiadczenie) {
        this.doswiadczenie = doswiadczenie;
        calcLevel();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLvl() {
        return lvl;
    }




    public int getZloto() {
        return zloto;
    }

    public void setZloto(int zloto) {
        this.zloto = zloto;
    }

    public Gracz getGracz() {
        return gracz;
    }

    public List<PrzedmiotWEkwipunku> getPrzedmioty() {
        return przedmioty;
    }

    public void setPrzedmioty(List<PrzedmiotWEkwipunku> przedmioty) {
        this.przedmioty = przedmioty;
    }

    public List<AtrybutyPostaci> getAtrybuty() {
        return atrybuty;
    }


    public Aspekt getAspekt() {
        return aspekt;
    }

    public void setAspekt(Aspekt aspekt) {
        this.aspekt = aspekt;
        if(aspekt.getPostac()==null) {
            this.aspekt.setPostac(this);
            return;
        }
        if(!aspekt.getPostac().equals(this))
            this.aspekt.setPostac(this);
    }

    public void addAtrybut(Atrybut atrybut, int wrt){
        AtrybutyPostaci ap = new AtrybutyPostaci(atrybut,this,wrt);
        atrybuty.add(ap);
        if(!atrybut.getPostaci().contains(ap))
            atrybut.addPostac(this,wrt);

        }
    protected Postac(){}
    public void addPrzedmiot(Przedmiot przedmiot, int wrt){
        PrzedmiotWEkwipunku pwe = new PrzedmiotWEkwipunku(this,przedmiot,wrt);
        przedmioty.add(pwe);
        if(!przedmiot.getPostaci().contains(pwe))
            przedmiot.addPostac(pwe);

    }
    public void addPrzedmiot(PrzedmiotWEkwipunku pwe) {
        przedmioty.add(pwe);
    }


        @Override
    public String toString() {
        return "id=" + id +
                ", " + imie +
                ", doswiadczenie=" + doswiadczenie +
                ", hp=" + hp +
                ", lvl=" + lvl +
                ", aspekt=" + aspekt;
    }
}
