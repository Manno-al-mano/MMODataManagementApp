package Logic.DatabaseOperations;

import Constants.PersistenceNames;
import Logic.Data.Game.Characters.*;
import Logic.Data.Game.Items.Artefakt;
import Logic.Data.Game.Items.Bron;
import Logic.Data.Game.Items.KategoriaPrzedmiotu;
import Logic.Data.Game.Items.Przedmiot;
import Logic.Data.Users.Moderation.Jezyk;
import Logic.Data.Users.Moderation.MistrzGry;
import Logic.Data.Users.Moderation.ModeratorCzatu;
import Logic.Data.Users.Moderation.Ranga;
import Logic.Data.Users.Osoba;
import Logic.Data.Users.Playerbase.Gracz;
import Logic.Data.Users.Playerbase.Klan;
import Logic.Data.Users.Playerbase.PrzywodcaKlanu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class TableCreator {
    public static void createTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistenceNames.CREATE);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            fillWithData(em);
            em.getTransaction().commit();
            System.out.println("Connected to the database successfully.");
        } catch (Exception e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }

        em.close();
        emf.close();
    }
    public static void fillWithData(EntityManager em) {
        Osoba osoba1 = new Osoba("Martin", "Peterson", "aaa@mail.com");

        Osoba osoba2 = new Osoba("John", "Smith", "bbb@mail.com");
        Osoba osoba3 = new Osoba("Will", "Kowalsky", "ccc@mail.com");
        Osoba osoba4 = new Osoba("Jan", "Iksinski", "ddd@mail.com");
        Osoba osoba5 = new Osoba("Mariusz", "Kowal", "eee@mail.com");
        Osoba osoba6 = new Osoba("Cthulhu", "Wszechiskra", "fff@mail.com");
        Osoba osoba7 = new Osoba("Tomasz", "Szklarski", "ggg@mail.com");
        Osoba osoba8 = new Osoba("Martha", "Wayne", "hhh@mail.com");

        PrzywodcaKlanu szef1 = new PrzywodcaKlanu("Manno", LocalDate.parse("2018-05-05"), true, osoba2);
        PrzywodcaKlanu szef2 = new PrzywodcaKlanu("TCZ21", LocalDate.parse("2018-05-05"), true, osoba3);
        Gracz gracz1 = new Gracz("Hunter66", LocalDate.parse("2018-05-05"), true, osoba4);
        Gracz gracz2 = new Gracz("Baldur", LocalDate.parse("2018-05-05"), false, osoba5);
        Gracz gracz3 = new Gracz("Skyrimfan12", LocalDate.parse("2018-05-05"), false, osoba7);
        MistrzGry master = new MistrzGry(osoba6, Ranga.glowny);
        MistrzGry mg1 = new MistrzGry(osoba1, Ranga.starszy);
        ModeratorCzatu moderatorCzatu = new ModeratorCzatu(osoba8);


        Jezyk angielski = new Jezyk("Angielski");
        Jezyk polski = new Jezyk("Polski");
        Jezyk grecki = new Jezyk("Grecki");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, 2, 2);
        master.addJezyk(angielski);
        master.addJezyk(polski);
        master.addJezyk(grecki);
        mg1.addJezyk(angielski);
        mg1.addJezyk(polski);
        moderatorCzatu.addJezyk(polski);

        Klan klan1 = new Klan("SCP", "Trzy Strzałki");
        Klan klan2 = new Klan("Sunbros", "Słońce");

        gracz1.setKlan(klan1);
        gracz2.setKlan(klan2);

        gracz1.setRekruter(szef1);
        gracz2.setRekruter(szef2);

        klan1.setPrzywodcaKlanu(szef1);
        klan2.setPrzywodcaKlanu(szef2);

        Postac postac1 = new PostacDPS("Lenz", 26_000, 150, gracz1, 150);
        Postac postac2 = new PostacWsparcia("Vivian", 50, 150, gracz1, 150);
        Postac postac3 = new PostacDPS("Sarevok", 50_000, 150, gracz2, 150);
        Postac postac4 = new PostacWsparcia("Viconia", 8000, 165, gracz2, 150);
        Postac postac5 = new PostacDPS("Kaidan", 6_000, 150, gracz3, 150);
        Postac postac6 = new PostacWsparcia("Inigo", 1000, 1112, gracz3, 150);
        Postac postac7 = new PostacDPS("Magh", 500, 150, szef1, 150);
        Postac postac8 = new PostacWsparcia("Az", 30_000_000, 150, szef1, 150);
        Postac postac9 = new PostacDPS("Caitha", 150_000, 1500, szef2, 150);
        Postac postac10 = new PostacWsparcia("Drask", 10_000, 150, szef2, 150);

        PostacFizyczna aspekt1 = new PostacFizyczna(100);
        PostacMagiczna aspekt2 = new PostacMagiczna(100);
        PostacMagiczna aspekt3 = new PostacMagiczna(400);
        PostacFizyczna aspekt4 = new PostacFizyczna(300);
        PostacFizyczna aspekt5 = new PostacFizyczna(200);
        PostacMagiczna aspekt6 = new PostacMagiczna(111);
        PostacMagiczna aspekt7 = new PostacMagiczna(122);
        PostacFizyczna aspekt8 = new PostacFizyczna(322);
        PostacFizyczna aspekt9 = new PostacFizyczna(555);
        PostacMagiczna aspekt10 = new PostacMagiczna(1000);

        postac1.setAspekt(aspekt1);
        postac2.setAspekt(aspekt2);
        postac3.setAspekt(aspekt3);
        postac4.setAspekt(aspekt4);
        postac5.setAspekt(aspekt5);
        postac6.setAspekt(aspekt6);
        postac7.setAspekt(aspekt7);
        postac8.setAspekt(aspekt8);
        postac9.setAspekt(aspekt9);
        postac10.setAspekt(aspekt10);

        Zaklecie kulaOgnia = new Zaklecie("Rzuca kulę ognia",30);
        Zaklecie leczenie = new Zaklecie("leczy Cel",50);
        Zaklecie palecSmierci = new Zaklecie("Zabija Cel",100);

        aspekt2.addZaklecie(leczenie);
        aspekt2.addZaklecie(kulaOgnia);
        aspekt3.addZaklecie(palecSmierci);
        aspekt3.addZaklecie(leczenie);
        aspekt6.addZaklecie(kulaOgnia);
        aspekt6.addZaklecie(palecSmierci);
        aspekt7.addZaklecie(leczenie);
        aspekt7.addZaklecie(kulaOgnia);
        aspekt10.addZaklecie(palecSmierci);

        Atrybut atrybut1= new Atrybut(NazwaAtrybutu.Sila);
        Atrybut atrybut2= new Atrybut(NazwaAtrybutu.SilaWoli);
        Atrybut atrybut3= new Atrybut(NazwaAtrybutu.Charyzma);
        Atrybut atrybut4= new Atrybut(NazwaAtrybutu.Kondycja);
        Atrybut atrybut5= new Atrybut(NazwaAtrybutu.Zrecznosc);
        Atrybut atrybut6= new Atrybut(NazwaAtrybutu.Inteligencja);

postac1.addAtrybut(atrybut1,3);
postac1.addAtrybut(atrybut2,3);
postac1.addAtrybut(atrybut3,3);
postac1.addAtrybut(atrybut4,3);
postac1.addAtrybut(atrybut5,3);
postac1.addAtrybut(atrybut6,3);
postac2.addAtrybut(atrybut1,4);
postac2.addAtrybut(atrybut2,4);
postac2.addAtrybut(atrybut3,4);
postac2.addAtrybut(atrybut4,4);
postac2.addAtrybut(atrybut5,4);
postac2.addAtrybut(atrybut6,4);

        Przedmiot przedmiot1 = new Przedmiot("Lina", 10, KategoriaPrzedmiotu.Codzienny);
        Przedmiot przedmiot2 = new Przedmiot("Kryształ many", 500,KategoriaPrzedmiotu.Magiczny);
        Przedmiot przedmiot3 = new Przedmiot("Chronotron", 100000,KategoriaPrzedmiotu.Magiczny);

        postac3.addPrzedmiot(przedmiot1, 5);
        postac3.addPrzedmiot(przedmiot2, 5);
        postac4.addPrzedmiot(przedmiot1, 10);
        postac5.addPrzedmiot(przedmiot2, 3);
        postac6.addPrzedmiot(przedmiot3, 1);
        postac7.addPrzedmiot(przedmiot1, 3);

        Bron bron1 = new Bron("Miecz",120,KategoriaPrzedmiotu.Bojowy,10,10);
        Bron bron2 = new Bron("Sztylet",30,KategoriaPrzedmiotu.Bojowy,5,30);

        postac8.addPrzedmiot(bron1,1);
        postac9.addPrzedmiot(bron2,1);
        postac10.addPrzedmiot(bron2,2);

        Artefakt artefakt1 = new Artefakt("Egida Pokoju",500000, KategoriaPrzedmiotu.Magiczny, 50, 15, "Ziemia");
        Artefakt artefakt2 = new Artefakt("Ostatni Tkacz Magii",750000, KategoriaPrzedmiotu.Magiczny, 50, 15, "Eter");
        Artefakt artefakt3 = new Artefakt("Dominium Progenitora",1000000, KategoriaPrzedmiotu.Magiczny, 50, 15, "Eter");

        aspekt1.addArtefakt(artefakt1);
        aspekt1.addArtefakt(artefakt2);
        aspekt4.addArtefakt(artefakt3);

        em.persist(bron1);
        em.persist(bron2);
        em.persist(przedmiot1);
        em.persist(przedmiot2);
        em.persist(przedmiot3);
        em.persist(atrybut1);
        em.persist(atrybut2);
        em.persist(atrybut3);
        em.persist(atrybut4);
        em.persist(atrybut5);
        em.persist(atrybut6);
        em.persist(aspekt1);
        em.persist(aspekt2);
        em.persist(aspekt3);
        em.persist(aspekt4);
        em.persist(aspekt5);
        em.persist(aspekt6);
        em.persist(aspekt7);
        em.persist(aspekt8);
        em.persist(aspekt9);
        em.persist(aspekt10);
        em.persist(postac1);
        em.persist(postac2);
        em.persist(postac3);
        em.persist(postac4);
        em.persist(postac5);
        em.persist(postac6);
        em.persist(postac7);
        em.persist(postac8);
        em.persist(postac9);
        em.persist(postac10);
        em.persist(master);
        em.persist(mg1);
        em.persist(moderatorCzatu);

    }



    public static void main(String[] args) {
        createTable();
    }
        }



