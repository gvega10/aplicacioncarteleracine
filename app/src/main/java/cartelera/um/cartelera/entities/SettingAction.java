package cartelera.um.cartelera.entities;


import cartelera.um.cartelera.interfaces.SettingType;

/**
 * Created by cyazky on 11/19/17.
 */

public class SettingAction extends Setting implements SettingType {
    private int action;


    public SettingAction(String title, int icon, int settingType, int action){
        super(title,icon,settingType);
        this.action = action;
    }

    public SettingAction(){

    }

    public int getAction() {
        return action;
    }

    public void setAction(final int action) {
        this.action = action;
    }

    @Override
    public int getSettingType() {
        return ACTION;
    }
}
