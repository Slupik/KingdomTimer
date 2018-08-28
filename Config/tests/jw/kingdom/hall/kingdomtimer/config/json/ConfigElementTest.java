package jw.kingdom.hall.kingdomtimer.config.json;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.ConfigUtils;
import jw.kingdom.hall.kingdomtimer.config.TestUtils;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
public class ConfigElementTest extends ConfigElement {

    @Test
    public void isCallingParent() {
        assertTrue(isCallingParent(ConfigUtils.PARENT_VALUE_LINK.toLowerCase(), ConfigFieldType.STRING));
        assertTrue(isCallingParent(ConfigUtils.PARENT_VALUE_LINK.toLowerCase(), ConfigFieldType.BOOLEAN));
        assertTrue(isCallingParent(ConfigUtils.PARENT_VALUE_LINK.toLowerCase(), ConfigFieldType.INTEGER));

        assertTrue(isCallingParent(ConfigUtils.PARENT_VALUE_LINK.toUpperCase(), ConfigFieldType.STRING));
        assertTrue(isCallingParent(ConfigUtils.PARENT_VALUE_LINK.toUpperCase(), ConfigFieldType.BOOLEAN));
        assertTrue(isCallingParent(ConfigUtils.PARENT_VALUE_LINK.toUpperCase(), ConfigFieldType.INTEGER));

        assertFalse(isCallingParent("dqwdqwdqw", ConfigFieldType.STRING));
        assertFalse(isCallingParent("true", ConfigFieldType.BOOLEAN));
        assertFalse(isCallingParent("423423", ConfigFieldType.INTEGER));

        TestUtils.muteErrors();
        assertTrue(isCallingParent("maybe", ConfigFieldType.BOOLEAN));
        assertTrue(isCallingParent("one", ConfigFieldType.INTEGER));
        TestUtils.unmuteErrors();
    }

    @Test
    public void toBoolean() {
        try {
            assertTrue(toBoolean("yes"));
            assertTrue(toBoolean("true"));
            assertTrue(toBoolean("tak"));
            assertTrue(toBoolean("prawda"));
            assertTrue(toBoolean("Yes"));
            assertTrue(toBoolean("True"));
            assertTrue(toBoolean("Tak"));
            assertTrue(toBoolean("Prawda"));
            assertTrue(toBoolean("YES"));
            assertTrue(toBoolean("TRUE"));
            assertTrue(toBoolean("TAK"));
            assertTrue(toBoolean("PRAWDA"));

            assertFalse(toBoolean("no"));
            assertFalse(toBoolean("nope"));
            assertFalse(toBoolean("false"));
            assertFalse(toBoolean("nie"));
            assertFalse(toBoolean("fałsz"));
            assertFalse(toBoolean("No"));
            assertFalse(toBoolean("Nope"));
            assertFalse(toBoolean("False"));
            assertFalse(toBoolean("Nie"));
            assertFalse(toBoolean("Fałsz"));
            assertFalse(toBoolean("NO"));
            assertFalse(toBoolean("NOPE"));
            assertFalse(toBoolean("FALSE"));
            assertFalse(toBoolean("NIE"));
            assertFalse(toBoolean("FAŁSZ"));
        } catch (DataParseException e) {
            e.printStackTrace();
        }

        assertThrows(DataParseException.class, () -> {
            assertTrue(toBoolean("maybe"));
        });
    }

    @Test
    public void toInteger() {
        String goodValue = "34234";
        try {
            assertEquals(34234, toInteger(goodValue));
        } catch (DataParseException e) {
            e.printStackTrace();
        }

        String badValue = "bad";
        assertThrows(DataParseException.class, () -> {
            toInteger(badValue);
        });
    }

    @Override
    public void applyParentConfig(Config parent) {}
}