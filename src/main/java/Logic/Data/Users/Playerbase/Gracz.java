package Logic.Data.Users.Playerbase;

import Logic.Data.Game.Characters.Postac;
import Logic.Data.Users.Moderation.ModeratorCzatu;
import Logic.Data.Users.Osoba;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Gracz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String nick;

    @Temporal(TemporalType.DATE)
    private LocalDate dataZalozeniaKonta;

    @Column(nullable = false)
    private boolean czyMozeUzywacCzat;

    @ManyToOne
    @JoinColumn(name = "moderator_id",nullable = true)
    ModeratorCzatu moderatorCzatu;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "klan_id")
    Klan klan;

    @OneToOne
    private Gracz rekruter;

    @Temporal(TemporalType.DATE)
    private Date dataZmianyCzatu;

    public List<Postac> getPostaci() {
        return postaci;
    }

    @OneToMany(mappedBy = "gracz")
    private List<Postac> postaci;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    Osoba osoba;
    protected Gracz(){}

    public Gracz(String nick, LocalDate dataZalozeniaKonta, boolean czyMozeUzywacCzat, Osoba osoba) {
        this.nick = nick;
        this.dataZalozeniaKonta = dataZalozeniaKonta;
        this.czyMozeUzywacCzat = czyMozeUzywacCzat;
        this.osoba = osoba;
        postaci = new ArrayList<>();
    }

    public void addPostac(Postac postac){
        postaci.add(postac);
    }

    public Long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public LocalDate getDataZalozeniaKonta() {
        return dataZalozeniaKonta;
    }

    public void setDataZalozeniaKonta(LocalDate dataZalozeniaKonta) {
        this.dataZalozeniaKonta = dataZalozeniaKonta;
    }

    public boolean isCzyMozeUzywacCzat() {
        return czyMozeUzywacCzat;
    }

    public void setCzyMozeUzywacCzat() {
        czyMozeUzywacCzat = !czyMozeUzywacCzat;
    }

    public Klan getKlan() {
        return klan;
    }

    public void setKlan(Klan klan) {
        this.klan = klan;
        if(!klan.getGracze().contains(this)){
            klan.addPlayer(this);
        }

    }

    public ModeratorCzatu getModeratorCzatu() {
        return moderatorCzatu;
    }

    public Gracz getRekruter() {
        return rekruter;
    }

    public void setRekruter(Gracz rekruter) {
        this.rekruter = rekruter;
    }

    public Date getDataZmianyCzatu() {
        return dataZmianyCzatu;
    }

    public void setModeratorCzatu(ModeratorCzatu moderatorCzatu) {
        if(this.moderatorCzatu!=null)
        if(this.moderatorCzatu.getGracze().contains(this)){
            this.moderatorCzatu.removeGracz(this);
        }
        this.moderatorCzatu = moderatorCzatu;
        if(moderatorCzatu==null)
            return;
        if(!moderatorCzatu.getGracze().contains(this))
            moderatorCzatu.addGracz(this);
    }

    public void setDataZmianyCzatu(Date dataZmianyCzatu) {
        this.dataZmianyCzatu = dataZmianyCzatu;
    }


}
