package com.github.viktigpetterr.sudokusolver.javafx;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * Describes an extended form of TextField which is a class in JavaFX.
 * OneNumberTextField allow user to enter one symbol of 1-9.
 *
 * @author viktigpetterr
 */
public class OneNumberTextField extends TextField {

    private static final String DARK_STYLE = "-fx-background-color: #8c8c8c;";

    public OneNumberTextField(boolean styled) {
        this.setMaxSize(40, 40);
        this.setMinSize(40, 40);
        this.setAlignment(Pos.CENTER);
        if (styled) {
            this.setStyle(DARK_STYLE);
        }
    }

    public void setNumber(String number) {
        this.textProperty().set(number);
    }

    /**
     * Overrides replaceText(...) in TextInputControl.
     *
     * @param start - The starting index in the range, inclusive. This must be >=
     *              0 and < the end.
     * @param end   - The ending index in the range, exclusive. This is one-past
     *              the last character to delete (consistent with the String
     *              manipulation methods). This must be > the start, and <= the
     *              length of the text.
     * @param text  - The text that is to replace the range. This must not be
     *              null.
     */
    public void replaceText(int start, int end, String text) {
        if (matches(text)) {
            super.replaceText(start, end, text);
        }
    }

    /**
     * Overrides replaceSelection(...) in TextInputControl.
     *
     * @param text - Any form of String.
     */
    public void replaceSelection(String text) {
        if (matches(text)) {
            super.replaceSelection(text);
        }
    }

    /**
     * Examine whether String text is empty, size < 1 and if String method:
     * matches("[1-9]"). If all conditions are met true is returned, otherwise
     * false.
     *
     * @param text - Any form of String.
     * @return boolean: true or false.
     */
    private boolean matches(String text) {
        return text.isEmpty() || (getText().length() < 1) && text.matches("[1-9]");
    }

}