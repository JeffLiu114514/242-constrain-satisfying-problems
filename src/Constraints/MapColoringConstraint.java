package Constraints;

import Variables.MapColoringVariable;

public class MapColoringConstraint {
    public MapColoringVariable var1, var2;

    public MapColoringConstraint(MapColoringVariable variable1, MapColoringVariable variable2) {
        this.var1 = variable1;
        this.var2 = variable2;
    }
}
