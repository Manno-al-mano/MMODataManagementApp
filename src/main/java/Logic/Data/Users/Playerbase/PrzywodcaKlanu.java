package Logic.Data.Users.Playerbase;

import Logic.Data.Users.Osoba;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class PrzywodcaKlanu extends Gracz{

    @OneToOne(mappedBy = "przywodcaKlanu")
    private Klan klan;


    @Temporal(TemporalType.DATE)
    private LocalDate dataZostania;

   public PrzywodcaKlanu(String nick, LocalDate dataZalozeniaKonta, boolean czyMozeUzywacCzat, Osoba osoba) {
        super(nick, dataZalozeniaKonta, czyMozeUzywacCzat, osoba);
    }
    private PrzywodcaKlanu() {
    }
    public void setDataZostania(LocalDate dataZostania) {
        this.dataZostania = dataZostania;
    }

    @Override
    public void setKlan(Klan klan) {
        this.klan = klan;
        dataZostania = LocalDate.now();
    }

    @Override
    public Klan getKlan() {
        return klan;
    }
}
