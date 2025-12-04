import at.antibit82.builderpattern.DbConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTests {

    @Test
    public void test_valid_dbconnection_creation() {
        DbConnection connection = new DbConnection.Builder()
            .setDbType(DbConnection.DbType.MYSQL)
            .setHost("localhost")
            .setPort("3306")
            .setCredentials("admin", "adminpass")
        .build();

        assertEquals(DbConnection.DbType.MYSQL, connection.getDbType());
        assertEquals("localhost", connection.getHost());
        assertEquals("3306", connection.getPort());
        assertEquals("admin", connection.getUsername());
        assertEquals("adminpass", connection.getPassword());
    }

    @ParameterizedTest
    @MethodSource("missingPropertyTestCases")
    public void test_missing_property_throws_exception(
            DbConnection.Builder builder,
            String expectedMessage) {

        Exception exception = assertThrows(IllegalStateException.class, builder::build);
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> missingPropertyTestCases() {
    return Stream.of(
        Arguments.of(
            new DbConnection.Builder()
                .setHost("localhost")
                .setPort("3306")
                .setCredentials("admin", "adminpass"),
            "Database type was not set!"),
        Arguments.of(
            new DbConnection.Builder()
                .setDbType(DbConnection.DbType.MYSQL)
                .setPort("3306")
                .setCredentials("admin", "adminpass"),
            "Host was not set!"),
        Arguments.of(
            new DbConnection.Builder()
                .setDbType(DbConnection.DbType.MYSQL)
                .setHost("localhost")
                .setCredentials("admin", "adminpass"),
            "Port was not set!"),
        Arguments.of(
            new DbConnection.Builder()
                .setDbType(DbConnection.DbType.MYSQL)
                .setHost("localhost")
                .setPort("3306"),
            "Credentials were not set!"));
    }
}
