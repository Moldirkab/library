package controllersDraft;

import controllersDraft.interfaces.IReaderControllerDraft;
import modelsDraft.ReaderDraft;
import repositoriesDraft.interfaces.IReaderRepositoryDraft;

public class ReaderControllerDraft implements IReaderControllerDraft {
    IReaderRepositoryDraft repo;
    public ReaderControllerDraft(IReaderRepositoryDraft repo) {
        this.repo = repo;
    }
    public ReaderDraft findReaderByLoginPassword(String login, String password){
        return repo.findReaderByLoginPassword(login, password);
    }

    public void addReader(ReaderDraft newReader) {
        repo.addReader(newReader);
    }
}