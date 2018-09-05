/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.meeting;

import javafx.beans.value.ObservableValue;
import jw.kingdom.hall.kingdomtimer.model.utils.observable.ObservableField;

public class Task {
    private final ObservableField<String> name = new ObservableField<>("");
    private ObservableValue<String> vcalue;
}
