package dataDraft;

import java.sql.Connection;

public interface DBDraft {
    Connection getConnection();
    void close();
}
