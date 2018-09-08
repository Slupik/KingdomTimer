package jw.kingdom.hall.kingdomtimer.entity.monitor;

import java.awt.*;

/**
 * All rights reserved & copyright ©
 */
public interface Monitor {

    Rectangle getBounds();
    String getID();
    Type getType();

    boolean isMain();
    void setMain(boolean main);

    int getPlace();
    void setPlace(int place);

    enum Type {
        UNKNOWN(-2),
        OTHER(-1),
        RASTER_SCREEN(0),
        PRINTER(1),
        IMAGE_BUFFER(2);

        private final int ID;

        Type(int id) {
            ID = id;
        }

        public static Type getById(int id) {
            for(Type type:values()) {
                if(type.ID == id) {
                    return type;
                }
            }
            return UNKNOWN;
        }
    }
}
