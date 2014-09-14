import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jbon on 5/13/14.
 */
public class ShowBooks {

    private static final String RETRIEVE_ALL_BOOKS_SQL = "" +
            "select b.title, GROUP_CONCAT('\\t', a.name ORDER BY a.rank SEPARATOR '\\n') as authors, b.publisher, b.year \n" +
            "from Book b, Author a\n" +
            "where b.isbn = a.isbn\n" +
            "group by a.isbn, b.title, b.publisher, b.year\n" +
            "order by b.title\n" +
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
                while (resultSet.next()) {
                    rowsCount++;
                    System.out.println(resultSet.getString(1));
                    System.out.println(resultSet.getString(2));
                    System.out.print(resultSet.getString(3));
                    System.out.print(", ");
                    System.out.println(resultSet.getInt(4));
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
