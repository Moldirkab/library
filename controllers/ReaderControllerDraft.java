package controllersDraft;

import controllersDraft.interfaces.IReaderControllerDraft;
import modelsDraft.ReaderDraft;
import repositoriesDraft.interfaces.IReaderRepositoryDraft;

public class ReaderControllerDraft implements IReaderControllerDraft {
    IReaderRepositoryDraft repo;
    public ReaderControllerDraft(IReaderRepositoryDraft repo) {
        this.repo = repo;
    }
    public ReaderDraft findReaderByIdPassword(int searchId, String password){
        return repo.findReaderByIdPassword(searchId, password);
    }

    public void addReader(ReaderDraft newReader) {
        repo.addReader(newReader);
    }
}
