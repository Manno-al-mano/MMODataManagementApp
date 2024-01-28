package Logic.Data.Game.Items;

import Logic.Data.Game.Characters.PostacFizyczna;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Artefakt extends Bron{

    @Column(nullable = false)
    private String zywiol;

    @ManyToOne
    @JoinColumn(name = "postac_fizyczna_id")
    private PostacFizyczna postacFizyczna;
    private Artefakt(){}
    public PostacFizyczna getPostacFizyczna() {
        return postacFizyczna;
    }
    public void setPostacFizyczna(PostacFizyczna postacFizyczna){
       this.postacFizyczna=postacFizyczna;
       if (!this.postacFizyczna.getArtefakty().containsKey(nazwa))
           postacFizyczna.addArtefakt(this);
    }






    public Artefakt(String name, int price, KategoriaPrzedmiotu kategoriaPrzedmiotu, int dmg, int as, String element) {
        super(name, price, kategoriaPrzedmiotu, dmg, as);
        zywiol = element;
    }
}
