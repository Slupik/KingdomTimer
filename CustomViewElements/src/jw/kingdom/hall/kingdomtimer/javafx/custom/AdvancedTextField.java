package jw.kingdom.hall.kingdomtimer.javafx.custom;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TextField;

/**
 * Created on 04.12.2016.
 */
public class AdvancedTextField extends TextField {
	private StringProperty restrict = new SimpleStringProperty();

	private IntegerProperty maxLength = new SimpleIntegerProperty(-1);
	private boolean isNumberField;

	public AdvancedTextField() {
		addListener();
	}

	private void addListener() {
		textProperty().addListener(new ChangeListener<String>() {

			private boolean ignore;

			@Override
			public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
				if (ignore)
					return;
				if(s1==null || s1.length()==0){
					ignore = true;
					setText("");
					ignore = false;
					return;
				}

				if (maxLength.get() > -1 && s1.length() > maxLength.get()) {
					ignore = true;
					setText(s1.substring(0, maxLength.get()));
					ignore = false;
				}

				if (restrict.get() != null && !restrict.get().equals("") && !s1.matches(restrict.get() + "*")) {
					ignore = true;
					setText(s);
					ignore = false;
				}

				if(isNumberField && !isNumber(s1.replaceAll("[,]", "."))){
					ignore = true;
					setText(s);
					ignore = false;
				}
			}
		});
	}

	private static boolean isNumber(String s1) {
		if(s1.equals("-")){
			s1 = "-1";
		}
		try {
			Double.parseDouble(s1);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public int getMaxLength() {
		return maxLength.get();
	}

	/*
	 * Sets the max length of the text field.
	 *
	 * @param maxLength The max length.
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength.set(maxLength);
	}

	/*
	 * Sets a regular expression character class which restricts the user input.<br/>
	 * E.g. [0-9] only allows numeric values.
	 *
	 * @param restrict The regular expression.
	 */
	public void setRestrict(String restrict) {
		this.restrict.set(restrict);
	}

	public String getRestrict() {
		return restrict.get();
	}

	public StringProperty restrictProperty() {
		return restrict;
	}

	public IntegerProperty maxLengthProperty() {
		return maxLength;
	}

	public void setToNumberField(boolean toNumber){
		isNumberField = toNumber;
	}

	//Text field implementation
	public String getSaveText(){
		String text = getText();
		if(text == null || text.length()==0){
			if(isNumberField){
				text = "0";
			}
		}
		return text;
	}
	// PENDING_DOC_REVIEW
	/**
	 * Registers an event handler to this node. The handler is called when the
	 * node receives an {@code Event} of the specified type during the bubbling
	 * phase of event delivery.
	 *
	 * @param <T> the specific event class of the handler
	 * @param eventType the type of the events to receive by the handler
	 * @param eventHandler the handler to display
	 * @throws NullPointerException if the event type or handler is null
	 */
	public final <T extends Event> void addTextEventHandler(
			final EventType<T> eventType,
			final EventHandler<? super T> eventHandler) {
		addEventHandler(eventType, eventHandler);
	}
	// PENDING_DOC_REVIEW
	/**
	 * Unregisters a previously registered event handler from this node. One
	 * handler might have been registered for different event types, so the
	 * caller needs to specify the particular event type from which to
	 * unregister the handler.
	 *
	 * @param <T> the specific event class of the handler
	 * @param eventType the event type from which to unregister
	 * @param eventHandler the handler to unregister
	 * @throws NullPointerException if the event type or handler is null
	 */
	public final <T extends Event> void removeTextEventHandler(
			final EventType<T> eventType,
			final EventHandler<? super T> eventHandler) {
		removeEventHandler(eventType, eventHandler);
	}

	// PENDING_DOC_REVIEW
	/**
	 * Registers an event filter to this node. The filter is called when the
	 * node receives an {@code Event} of the specified type during the capturing
	 * phase of event delivery.
	 *
	 * @param <T> the specific event class of the filter
	 * @param eventType the type of the events to receive by the filter
	 * @param eventFilter the filter to display
	 * @throws NullPointerException if the event type or filter is null
	 */
	public final <T extends Event> void addTextEventFilter(
			final EventType<T> eventType,
			final EventHandler<? super T> eventFilter) {
		addEventFilter(eventType, eventFilter);
	}

	// PENDING_DOC_REVIEW
	/**
	 * Unregisters a previously registered event filter from this node. One
	 * filter might have been registered for different event types, so the
	 * caller needs to specify the particular event type from which to
	 * unregister the filter.
	 *
	 * @param <T> the specific event class of the filter
	 * @param eventType the event type from which to unregister
	 * @param eventFilter the filter to unregister
	 * @throws NullPointerException if the event type or filter is null
	 */
	public final <T extends Event> void removeTextEventFilter(
			final EventType<T> eventType,
			final EventHandler<? super T> eventFilter) {
		removeEventFilter(eventType, eventFilter);
	}
}
