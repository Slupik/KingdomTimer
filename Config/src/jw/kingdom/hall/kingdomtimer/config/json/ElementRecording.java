package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * All rights reserved & copyright ©
 */
class ElementRecording extends ConfigElement {
    @SerializedName("sciezka")
    @Nullable
    @Expose
    private String path;

    @SerializedName("autopilot")
    @Nullable
    @Expose
    private String autopilot;

    @SerializedName("rozbij_na_dzialy")
    @Nullable
    @Expose
    private String autoSeparate;

    @SerializedName("nazwa_backupu")
    @Nullable
    @Expose
    private String rawFileNameBackup;

    @SerializedName("nazwa_backupu_przy_rozbiciu")
    @Nullable
    @Expose
    private String rawFileNameBackupGroups;

    @SerializedName("nazwa_ostateczna")
    @Nullable
    @Expose
    private String rawFileNameFinal;

    @SerializedName("nazwa_ostateczna_przy_rozbiciu")
    @Nullable
    @Expose
    private String rawFileNameFinalGroups;

    public String getPath() {
        if(isCallingParent(path)) {
            return parent.getRecordDestPath();
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getAutopilot() throws DataParseException {
        if(isCallingParent(autopilot, ConfigFieldType.BOOLEAN)) {
            return parent.isEnabledAutopilot();
        }
        return toBoolean(autopilot);
    }

    public void setAutopilot(boolean autopilot) {
        this.autopilot = Boolean.toString(autopilot);
    }

    public boolean getAutoSeparate() throws DataParseException {
        if(isCallingParent(autoSeparate, ConfigFieldType.BOOLEAN)) {
            return parent.isAutoSeparate();
        }
        return toBoolean(autoSeparate);
    }

    public void setAutoSeparate(boolean isEnabled) {
        this.autoSeparate = Boolean.toString(isEnabled);
    }

    public String getRawFileNameBackup() {
        if(isCallingParent(rawFileNameBackup, ConfigFieldType.INTEGER)) {
            return parent.getRawFileNameBackup();
        }
        return rawFileNameBackup;
    }

    public void setRawFileNameBackup(String rawFileNameBackup) {
        this.rawFileNameBackup = rawFileNameBackup;
    }

    public String getRawFileNameBackupGroups() {
        if(isCallingParent(rawFileNameBackupGroups, ConfigFieldType.INTEGER)) {
            return parent.getRawFileNameBackupGroups();
        }
        return rawFileNameBackupGroups;
    }

    public void setRawFileNameBackupGroups(String rawFileNameBackupGroups) {
        this.rawFileNameBackupGroups = rawFileNameBackupGroups;
    }

    public String getRawFileNameFinal() {
        if(isCallingParent(rawFileNameFinal, ConfigFieldType.INTEGER)) {
            return parent.getRawFileNameFinal();
        }
        return rawFileNameFinal;
    }

    public void setRawFileNameFinal(String rawFileNameFinal) {
        this.rawFileNameFinal = rawFileNameFinal;
    }

    public String getRawFileNameFinalGroups() {
        if(isCallingParent(rawFileNameFinalGroups, ConfigFieldType.INTEGER)) {
            return parent.getRawFileNameFinalGroups();
        }
        return rawFileNameFinalGroups;
    }

    public void setRawFileNameFinalGroups(String rawFileNameFinalGroups) {
        this.rawFileNameFinalGroups = rawFileNameFinalGroups;
    }
}
