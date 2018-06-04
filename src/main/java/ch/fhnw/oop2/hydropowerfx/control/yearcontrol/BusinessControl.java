package ch.fhnw.oop2.hydropowerfx.control.yearcontrol;

import java.util.Calendar;
import java.util.regex.Pattern;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class BusinessControl extends Control {
    private static RootPM rootPM;

    private static final PseudoClass MANDATORY_CLASS = PseudoClass.getPseudoClass("mandatory");
    // MR: Invalidation disabled, as we use our own
    // private static final PseudoClass INVALID_CLASS   = PseudoClass.getPseudoClass("invalid");
    private static final PseudoClass CONVERTIBLE_CLASS = PseudoClass.getPseudoClass("convertible");


    private static final String INTEGER_REGEX    = "[\\d]{4,4}";
    private static final String CALC_REGEX = "\\d+\\s*[\\+-]\\s*\\d+";
    private static final Pattern INTEGER_PATTERN = Pattern.compile(INTEGER_REGEX);

    private final IntegerProperty value = new SimpleIntegerProperty();
    private final StringProperty userFacingText = new SimpleStringProperty();

    private final Calendar today = Calendar.getInstance();

    private final BooleanProperty mandatory = new SimpleBooleanProperty() {
        @Override
        protected void invalidated() {
            pseudoClassStateChanged(MANDATORY_CLASS, get());
        }
    };

    private final BooleanProperty invalid = new SimpleBooleanProperty(false) {
        /* MR: Invalidation disabled, as we use our own

        @Override
        protected void invalidated() {
            pseudoClassStateChanged(INVALID_CLASS, get());
        }*/
    };

    private final BooleanProperty convertible = new SimpleBooleanProperty(false){
        /* MR: Invalidation disabled, as we use our own
        @Override
        protected void invalidated()
        {
            pseudoClassStateChanged(CONVERTIBLE_CLASS, get());
        }*/
    };

    private final BooleanProperty readOnly     = new SimpleBooleanProperty();
    private final StringProperty  errorMessage = new SimpleStringProperty();


    public BusinessControl(RootPM model) {
        this.rootPM = model;
        initializeSelf();
        addValueChangeListener();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new BusinessSkin(this);
    }

    public void reset() {
        setUserFacingText(convertToString(getValue()));
    }

    public void increase() {
        setValue(getValue() + 1);
    }

    public void decrease() {
        setValue(getValue() - 1);
    }

    private void initializeSelf() {
         getStyleClass().add("business-control");

         setUserFacingText(convertToString(getValue()));
    }

    private void addValueChangeListener() {
        userFacingText.addListener((observable, oldValue, userInput) -> {
            if (isMandatory() && (userInput == null || userInput.isEmpty())) {
                setInvalid(true);
                setConvertible(false);
                setErrorMessage("Mandatory Field");
                return;
            }

            if (isInteger(userInput)) {
                setInvalid(false);
                setConvertible(false);
                setErrorMessage(null);
                setValue(convertToInt(userInput));
            } else if(userInput.matches(CALC_REGEX) || userInput.equals("now")) {
                setInvalid(false);
                setConvertible(true);
            } else {
                setInvalid(true);
                setErrorMessage("Not an Integer");
            }
        });

        valueProperty().addListener((observable, oldValue, newValue) -> {
            setUserFacingText(convertToString(newValue.intValue()));
        });
    }

    public void convertUserInput() {
        if(getUserFacingText().equals("now"))
        {
            setValue(today.get(Calendar.YEAR));
        } else if(getUserFacingText().matches(CALC_REGEX))
        {
            String[] numbers;
            if(getUserFacingText().contains("+"))
            {
                numbers = getUserFacingText().split("\\+");
                int calc = Integer.valueOf(numbers[0].trim()) + Integer.valueOf(numbers[1].trim());
                setValue(calc);
            } else {
                numbers = getUserFacingText().split("-");
                setValue(Integer.valueOf(numbers[0]) - Integer.valueOf(numbers[1]));
            }
        }
    }

    private boolean isInteger(String userInput) {
        return INTEGER_PATTERN.matcher(userInput).matches();
    }

    private int convertToInt(String userInput) {
        return Integer.parseInt(userInput);
    }

    private String convertToString(int newValue) {
        return String.valueOf(newValue);
    }

    // all the getters and setters

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public boolean isReadOnly() {
        return readOnly.get();
    }

    public BooleanProperty readOnlyProperty() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly.set(readOnly);
    }

    public boolean isMandatory() {
        return mandatory.get();
    }

    public BooleanProperty mandatoryProperty() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory.set(mandatory);
    }

    public boolean getInvalid() {
        return invalid.get();
    }

    public BooleanProperty invalidProperty() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid.set(invalid);
        // MR: Input validation adapted
        this.rootPM.setInvalidInputEntered(true);
    }

    public String getErrorMessage() {
        return errorMessage.get();
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.set(errorMessage);
    }

    public String getUserFacingText() {
        return userFacingText.get();
    }

    public StringProperty userFacingTextProperty() {
        return userFacingText;
    }

    public void setUserFacingText(String userFacingText) {
        this.userFacingText.set(userFacingText);
    }

    public boolean isInvalid() {
        return invalid.get();
    }

    public boolean isConvertible()
    {
        return convertible.get();
    }

    public BooleanProperty convertibleProperty()
    {
        return convertible;
    }

    public void setConvertible(boolean convertible)
    {
        this.convertible.set(convertible);
    }
}
