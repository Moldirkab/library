package repositoriesDraft.interfaces;

import modelsDraft.ReaderDraft;

public interface IReaderRepositoryDraft {
    ReaderDraft findReaderByLoginPassword(String login, String password);
    void addReader(ReaderDraft reader);
}
