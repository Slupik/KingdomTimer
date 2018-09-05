/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.observable.list;

import jw.kingdom.hall.kingdomtimer.model.observable.ChangeListener;
import jw.kingdom.hall.kingdomtimer.model.observable.Observable;

public interface ListChangeListener<O extends Observable, T> extends ChangeListener {
    void onChange(O observableObject, T actualList);
}
