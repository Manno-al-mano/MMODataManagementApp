package Logic.Data.Game.Characters;

import Logic.Data.Users.Playerbase.Gracz;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PostacDPS extends Postac{


    @Column(nullable = false)
    private int zadaneObrazenia;

    private PostacDPS(){}
    public PostacDPS(String imie, int exp, int hp, Gracz gracz, int zo) {
        super(imie, exp, hp, gracz);
        zadaneObrazenia=zo;
    }
}
