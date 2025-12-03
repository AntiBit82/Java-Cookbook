package at.antibit82.builderpattern;

/**
 * Database connection configuration using Builder Pattern.
 * Immutability of the DbConnection class is the big benefit of this pattern.
 */
public class App {
    public static void main(String[] args) {
    try {
        System.out.println("IllegalStateException expected:");
        DbConnection con = new DbConnection.Builder()
                .setHost("localhost")
                .setPort("3306")
                .setCredentials("admin", "adminpass")
            .build();
    } catch(IllegalStateException e) {
      System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
    }

    System.out.println("Create valid immutable DbConnection object:");
    DbConnection con = new DbConnection.Builder()
            .setDbType(DbConnection.DbType.POSTGRESQL)
            .setHost("localhost")
            .setPort("3306")
            .setCredentials("admin", "adminpass")
        .build();

    System.out.println(con);
  }
}
