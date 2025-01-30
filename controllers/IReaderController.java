package controllers;

import models.Reader;

public interface IReaderController {
    Reader findReaderByIdPassword(int searchId, String password);
    void addReader(Reader newReader);
}
