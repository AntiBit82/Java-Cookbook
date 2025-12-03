package at.antibit82.builderpattern;

/**
 * Database connection configuration using Builder Pattern.
 * Immutability of the DbConnection class is the big benefit of this pattern.
 */
public class DbConnection {
    public enum DbType {
        MYSQL,
        POSTGRESQL,
        ORACLE,
        SQLSERVER
    }

    private final DbType dbType;
    private final String host;
    private final String port;
    private final String username;
    private final String password;

    private DbConnection(Builder builder) {
        this.dbType = builder.dbType;
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
    }

    public DbType getDbType() {
        return dbType;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "DbConnection{" +
                "dbType=" + getDbType() +
                ", host='" + getHost() + '\'' +
                ", port='" + getPort() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }

    public static class Builder {
        private DbType dbType;
        private String host;
        private String port;
        private String username;
        private String password;

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setDbType(DbType dbType) {
            this.dbType = dbType;
            return this;
        }

        public Builder setPort(String port) {
            this.port = port;
            return this;
        }

        public Builder setCredentials(String username, String password) {
            this.username = username;
            this.password = password;
            return this;
        }

        public DbConnection build() {
            if(dbType == null) {
                throw new IllegalStateException("Database type was not set!");
            }
            if(host == null) {
                throw new IllegalStateException("Host was not set!");
            }
            if(port == null) {
                throw new IllegalStateException("Port was not set!");
            }
            if(username == null || password == null) {
                throw new IllegalStateException("Credentials were not set!");
            }
            return new DbConnection(this);
        }
    }
}
