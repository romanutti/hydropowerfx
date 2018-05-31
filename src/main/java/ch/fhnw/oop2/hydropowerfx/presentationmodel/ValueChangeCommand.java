package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.Property;

/**
 * @author Dieter Holz
 */
public class ValueChangeCommand<T> implements Command {
    private final RootPM    rootPM;
    private final Property<T> property;
    private final T           oldValue;
    private final T           newValue;

    public ValueChangeCommand(RootPM rootPM, Property<T> property, T oldValue, T newValue) {
        this.rootPM   = rootPM;
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public void undo() {
        rootPM.setPropertyValueWithoutUndoSupport(property, oldValue);
    }

    public void redo() {
        rootPM.setPropertyValueWithoutUndoSupport(property, newValue);
    }
}