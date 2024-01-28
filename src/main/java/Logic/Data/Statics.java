package Logic.Data;

import jakarta.persistence.*;

@Entity
public class Statics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    public static int sredniPoziom;
}
