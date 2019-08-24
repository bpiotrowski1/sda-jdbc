package sda.jdbc.zadania.zadanie3;

public class Zadanie4 {
    public static void main(String[] args) {
        DAOKsiazka daoKsiazka = new DAOKsiazka();
        DAOAutor daoAutor = new DAOAutor();
        DAOKsiazkaAutor daoKsiazkaAutor = new DAOKsiazkaAutor();

        //1
        int idNowejKsiazki = daoKsiazka.newBooks("Nowa ksiazka");

        //2
        int idNowegoAutora = daoAutor.newAuthor("Nowy", "Autor");
        daoKsiazkaAutor.newKsiazkaAutor(idNowejKsiazki, idNowegoAutora);

        //3
        daoAutor.getBooksById(idNowegoAutora);

        //4
        System.out.println(daoAutor.getBooksByTitle(9, "Nowa ksiazka"));
    }
}
