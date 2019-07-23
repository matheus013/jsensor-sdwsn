package projects.SDWSN.statics;

import projects.SDWSN.service.Action;
import projects.SDWSN.service.Ambient;
import projects.SDWSN.service.TypeSense;

import static projects.SDWSN.service.Ambient.*;

public class SenseEnumSingleton {

    public static TypeSense[] typeSenses = {TypeSense.TEMPERATURE, TypeSense.PRESSURE,
            TypeSense.LUMINOSITY, TypeSense.HUMIDITY};
    public static Ambient[] environments = {A, B, C, D, E};
    public static Action[] actions = {Action.UP, Action.DOWN, Action.ON, Action.OFF, Action.GET};


}
