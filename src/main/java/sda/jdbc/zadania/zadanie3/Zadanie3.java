package sda.jdbc.zadania.zadanie3;

public class Zadanie3 {
    public static void main(String[] args) {
        final DataAccessObject dataAccessObject = new DataAccessObject();

        //1
        dataAccessObject.getAllBooks();

        //2
        dataAccessObject.newBooks("Tytul 1");
        dataAccessObject.newBooks("Tytul 2");
        dataAccessObject.newBooks("Tytul 3");
        dataAccessObject.newBooks("Tytul 4");
        dataAccessObject.getAllBooks();

        //3
        dataAccessObject.updateById(dataAccessObject.getLastId(), "naklad wyczerpany");
        dataAccessObject.getAllBooks();

        //4
        dataAccessObject.deleteById(dataAccessObject.getLastId());
        dataAccessObject.getAllBooks();
    }
}
