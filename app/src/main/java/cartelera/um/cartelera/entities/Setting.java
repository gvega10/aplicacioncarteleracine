package cartelera.um.cartelera.entities;

/**
 * Created by cyazky on 11/19/17.
 */

public abstract class Setting {
    private String title;
    private int icon;
    private int settingType;


    public Setting(String title, int icon, int settingType){
        this.title = title;
        this.icon = icon;
        this.settingType = settingType;
    }

    public Setting(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(final int icon) {
        this.icon = icon;
    }

    public int getSettingType() {
        return settingType;
    }

    public void setSettingType(final int settingType) {
        this.settingType = settingType;
    }
}