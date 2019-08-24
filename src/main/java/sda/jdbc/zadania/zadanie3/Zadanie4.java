package sda.jdbc.zadania.zadanie3;

public class Zadanie4 {
    public static void main(String[] args) {
        DAOKsiazka daoKsiazka = new DAOKsiazka();
        DAOAutor daoAutor = new DAOAutor();
        DAOKsiazkaAutor daoKsiazkaAutor = new DAOKsiazkaAutor();

        //1
        daoKsiazka.newBooks("Nowa ksiazka");
        int idNowejKsiazki = daoKsiazka.getLastId();

        //2
        daoAutor.newAuthor("Nowy", "Autor");
        int idNowegoAutora = daoAutor.getLastId();
        daoKsiazkaAutor.newKsiazkaAutor(idNowejKsiazki, idNowegoAutora);

        //3
        daoAutor.getBooksById(idNowegoAutora);

        //4
        System.out.println(daoAutor.getBooksByTitle(9, "Nowa ksiazka"));
    }
}
