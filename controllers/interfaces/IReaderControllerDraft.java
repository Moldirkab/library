package controllersDraft.interfaces;

import modelsDraft.ReaderDraft;

public interface IReaderControllerDraft {
    ReaderDraft findReaderByIdPassword(int searchId, String password);
    void addReader(ReaderDraft newReader);
}
