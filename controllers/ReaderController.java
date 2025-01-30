package controllers;

import models.Reader;
import repositories.IReaderRepository;

public class ReaderController implements IReaderController{
    IReaderRepository repo;
    public ReaderController(IReaderRepository repo) {
        this.repo = repo;
    }
    public Reader findReaderByIdPassword(int searchId, String password){
        return repo.findReaderByIdPassword(searchId, password);
    }

    public void addReader(Reader newReader) {
        repo.addReader(newReader);
    }
}
