package Logic.Data.Users.Moderation;

import Logic.Data.Users.Osoba;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class MistrzGry extends ModeratorCzatu{


    @Enumerated(EnumType.STRING)
    @Column(name = "ranga")
    private Ranga ranga;
    public MistrzGry(){}

    public MistrzGry(Osoba osoba,Ranga ranga) {
        super(osoba);
        this.ranga=ranga;
    }


    public Ranga getRanga() {
        return ranga;
    }

    @Override
    public String toString() {
        return osoba.toString()+" "+ranga;
    }
}
