package cartelera.um.cartelera.entities;


import cartelera.um.cartelera.interfaces.SettingType;

/**
 * Created by cyazky on 11/19/17.
 */

public class SettingActivityLauncher extends Setting implements SettingType {
    private int nextActivity;

    public SettingActivityLauncher(String title, int icon, int settingType, int nextActivity){
        super(title,icon,settingType);
        this.nextActivity = nextActivity;
    }

    public int getNextActivity() {
        return nextActivity;
    }

    public void setNextActivity(final int nextActivity) {
        this.nextActivity = nextActivity;
    }

    @Override
    public int getSettingType() {
        return ACTIVITY_LAUNCHER;
    }
}
