package ch.fhnw.oop2.hydropowerfx.presentationmodel;

/**
 * @author Dieter Holz
 */
public interface Command {
    void undo();

    void redo();
}