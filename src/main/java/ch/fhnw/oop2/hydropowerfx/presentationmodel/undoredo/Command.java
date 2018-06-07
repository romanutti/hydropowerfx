package ch.fhnw.oop2.hydropowerfx.presentationmodel.undoredo;

public interface Command {
    void undo();

    void redo();
}