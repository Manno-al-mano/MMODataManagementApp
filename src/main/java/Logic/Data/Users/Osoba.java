package Logic.Data.Users;

import Logic.Data.Users.Moderation.ModeratorCzatu;
import Logic.Data.Users.Playerbase.Gracz;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Osoba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imie;

    @Column(nullable = false)
    private String nazwisko;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(mappedBy = "osoba")
    Gracz gracz;

    @OneToOne(mappedBy = "osoba")
    ModeratorCzatu moderatorCzatu;

    private Osoba(){}
    public Osoba(String imie, String nazwisko, String email) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gracz getGracz() {
        return gracz;
    }

    public void setGracz(Gracz gracz) {
        this.gracz = gracz;
    }

    public ModeratorCzatu getModeratorCzatu() {
        return moderatorCzatu;
    }

    public void setModeratorCzatu(ModeratorCzatu moderatorCzatu) {
        this.moderatorCzatu = moderatorCzatu;
    }

    @Override
    public String toString() {
        return
                 imie + '\'' +
                " " + nazwisko + '\'' +
                " " + email + '\'' ;
    }
}
