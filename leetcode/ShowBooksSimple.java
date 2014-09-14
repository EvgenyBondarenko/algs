import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jbon on 5/13/14.
 */
public class ShowBooksSimple {

    private static final String RETRIEVE_ALL_BOOKS_SQL = "" +
            "select b.isbn, b.title, a.name, b.publisher, b.year \n" +
            "from Book b, Author a\n" +
            "where b.isbn = a.isbn\n" +
            "order by b.isbn, a.rank\n" +
            "LIMIT ? OFFSET ?;";

    void showBooks(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(RETRIEVE_ALL_BOOKS_SQL)) {
            boolean exhausted = false;
            int limit = 1000;
            int offset = 0;
            ps.setFetchSize(limit); //not necessarily needed, but might be helpful for the driver (depends on the driver)
            while (!exhausted) {
                ps.setInt(1, limit);
                ps.setInt(2, offset);
                ResultSet resultSet = ps.executeQuery();
                int rowsCount = 0;
                String isbn = null;
                String publisher = null;
                int year = 0;
                while (resultSet.next()) {
                    rowsCount++;
                    if (resultSet.getString(1).equals(isbn)) {
                        System.out.println("\t" + resultSet.getString(3));
                    } else {
                        if (publisher != null) {
                            System.out.println(publisher + ", " + year);
                        }
                        System.out.println(resultSet.getString(2));
                        System.out.println("\t" + resultSet.getString(3));
                        publisher = resultSet.getString(4);
                        year = resultSet.getInt(5);
                        isbn = resultSet.getString(1);
                    }
                }
                if (rowsCount < limit) {
                    exhausted = true;
                } else {
                    offset += limit;
                }
            }
        }
    }
}
