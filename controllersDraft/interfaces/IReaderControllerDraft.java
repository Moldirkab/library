package controllersDraft.interfaces;

import modelsDraft.ReaderDraft;

public interface IReaderControllerDraft {
    ReaderDraft findReaderByLoginPassword(String login, String password);
    void addReader(ReaderDraft newReader);
}
