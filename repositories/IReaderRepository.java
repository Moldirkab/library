package repositories;

import models.Reader;

public interface IReaderRepository {
    Reader findReaderByIdPassword(int id, String password);
    void addReader(Reader reader);
}
