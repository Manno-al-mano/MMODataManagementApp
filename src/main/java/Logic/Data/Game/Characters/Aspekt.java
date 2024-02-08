package Logic.Data.Game.Characters;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Aspekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne (cascade = {CascadeType.ALL})
    @MapsId
    @JoinColumn(name = "id")
    Postac postac;

    public Postac getPostac() {
        return postac;
    }
    protected Aspekt(){}
    public void setPostac(Postac postac) {
        this.postac = postac;
        if(postac.getAspekt()==null) {
            this.postac.setAspekt(this);
            return;
        }
        if(!postac.getAspekt().equals(this)){
            this.postac.setAspekt(this);
        }
    }

    @Override
    public String toString() {
        return "Aspekt{}";
    }
}
