package sda.jdbc.zadania.zadanie3;

public class Zadanie3 {
    public static void main(String[] args) {
        final DAOKsiazka daoKsiazka = new DAOKsiazka();

        //1
        daoKsiazka.getAllBooks();

        //2
        daoKsiazka.newBooks("Tytul 1");
        daoKsiazka.newBooks("Tytul 2");
        daoKsiazka.newBooks("Tytul 3");
        daoKsiazka.newBooks("Tytul 4");
        daoKsiazka.getAllBooks();

        //3
        daoKsiazka.updateById(daoKsiazka.getLastId(), "naklad wyczerpany");
        daoKsiazka.getAllBooks();

        //4
        daoKsiazka.deleteById(daoKsiazka.getLastId());
        daoKsiazka.getAllBooks();
    }
}
