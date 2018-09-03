package jw.kingdom.hall.kingdomtimer.data.record;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
abstract class NameParser {
    static String getParsedName(String raw, @NotNull MeetingTask task) {
        String parsed = raw;

        Date date = new Date();
        parsed = parsed.replace("{dluga_data}", new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(date));
        parsed = parsed.replace("{data}", new SimpleDateFormat("yyyy.MM.dd").format(date));
        parsed = parsed.replace("{rok}", new SimpleDateFormat("yyyy").format(date));
        parsed = parsed.replace("{miesiac}", new SimpleDateFormat("MM").format(date));
        parsed = parsed.replace("{dzien}", new SimpleDateFormat("dd").format(date));
        parsed = parsed.replace("{godzina}", new SimpleDateFormat("HH").format(date));
        parsed = parsed.replace("{minuta}", new SimpleDateFormat("mm").format(date));
        parsed = parsed.replace("{sekunda}", new SimpleDateFormat("ss").format(date));

        parsed = parsed.replace("{blok}", getNameOfType(task.getType()));
        parsed = parsed.replace("{punkt}", task.getName());

        return parsed;
    }

    private static CharSequence getNameOfType(MeetingTask.Type type) {
        switch (type) {
            case UNKNOWN:
                return "???";
            case NONE:
                return "-";
            case TREASURES:
                return "Skarby";
            case MINISTRY:
                return "Ulepszajmy";
            case LIVING:
                return "TrybŻycia";
            case LECTURE:
                return "Wykład";
            case WATCHTOWER:
                return "Strażnica";
            case OVERSEER:
                return "Obsługa";
            case OTHER:
                return "???";
        }
        return "???";
    }
}
