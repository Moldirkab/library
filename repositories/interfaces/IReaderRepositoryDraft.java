package repositoriesDraft.interfaces;

import modelsDraft.ReaderDraft;

public interface IReaderRepositoryDraft {
    ReaderDraft findReaderByIdPassword(int id, String password);
    void addReader(ReaderDraft reader);
}
