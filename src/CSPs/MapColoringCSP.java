package CSPs;

import Constraints.MapColoringConstraint;
import Domains.MapColoringDomain;
import Variables.MapColoringVariable;

import java.util.ArrayList;

public class MapColoringCSP {
    private ArrayList<MapColoringVariable> variables;
    private ArrayList<MapColoringConstraint> constraints;
    private ArrayList<MapColoringDomain> domains;

    public MapColoringCSP(ArrayList<MapColoringVariable> variables) {
        this.variables = variables;
    }

    public MapColoringVariable nextUnassignedVariable() {
        for (MapColoringVariable variable : variables) {
            if (variable.getAssignment() == null) return variable;
        }
        return null;
    }

    public boolean checkConsistency(MapColoringVariable variable, String color) {
        ArrayList<MapColoringVariable> neighbors = variable.getNeighbors();
        if (neighbors != null) {
            for (MapColoringVariable neighbor : neighbors) {
                if (neighbor.getAssignment() != null) {
                    if (neighbor.getAssignment().equals(color)) return false;
                }
            }
        }
        return true;
    }

    public boolean assignmentCompleted() {
        for (MapColoringVariable variable : variables) {
            if (variable.getAssignment() == null) return false;
        }
        return true;
    }

}
