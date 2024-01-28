package Logic.Data.Users.Moderation;

import Logic.Data.Game.Items.PrzedmiotWEkwipunku;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Jezyk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jezyk_id;



    @Column(nullable = false)
    String nazwa;

    @ManyToMany(mappedBy = "jezyki")
    List<ModeratorCzatu> moderators;

    private Jezyk(){}
    public Jezyk(String nazwa) {
        this.nazwa = nazwa;
        moderators = new ArrayList<>();
    }

    public List<ModeratorCzatu> getModerators() {
        return moderators;
    }

    public void addModerator(ModeratorCzatu moderatorCzatu){
        if(!moderators.contains(moderatorCzatu)){
            moderators.add(moderatorCzatu);
        }
        if(!moderatorCzatu.getJezyki().contains(this)){
            moderatorCzatu.addJezyk(this);
        }
    }

}
