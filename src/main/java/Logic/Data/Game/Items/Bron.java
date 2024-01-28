package Logic.Data.Game.Items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Bron extends Przedmiot {

    @Column(nullable = false)
    private int obrazenia;

    @Column(nullable = false)
    private int szybkoscAtaku;

    protected Bron(){}
    public Bron(String name, int price, KategoriaPrzedmiotu kategoriaPrzedmiotu, int dmg, int as) {
        super(name, price, kategoriaPrzedmiotu);
        obrazenia=dmg;
        szybkoscAtaku=as;
    }
}
