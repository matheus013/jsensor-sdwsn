package projects.SDWSN.statics;

import projects.SDWSN.service.Ambient;
import projects.SDWSN.service.TypeSense;

import static projects.SDWSN.service.Ambient.*;

public class EnumSingleton {

    public static TypeSense[] typeSenses = {TypeSense.TEMPERATURE, TypeSense.PRESSURE,
            TypeSense.LUMINOSITY, TypeSense.HUMIDITY};
    public static Ambient[] environments = {A, B, C, D, E};


}
