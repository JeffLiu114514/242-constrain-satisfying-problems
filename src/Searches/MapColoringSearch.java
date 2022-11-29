package Searches;

import CSPs.MapColoringCSP;
import Variables.MapColoringVariable;

import java.util.ArrayList;

public class MapColoringSearch {
    private ArrayList<MapColoringVariable> solution = new ArrayList<>();

    public ArrayList<MapColoringVariable> BacktrackingSearch(MapColoringCSP csp) {
        if (Backtrack(solution, csp)) {
            return solution;
        }
        return new ArrayList<>();
    }

    public boolean Backtrack(ArrayList<MapColoringVariable> solution, MapColoringCSP csp) {
        if (csp.assignmentCompleted()) return true;
        MapColoringVariable currentVariable = csp.nextUnassignedVariable();
        for (String color : currentVariable.getDomain().domain) {
            if (csp.checkConsistency(currentVariable, color)) {
                currentVariable.assign(color);
                solution.add(currentVariable);
                if (Backtrack(solution, csp)) {
                    return true;
                } else {
                    solution.remove(solution.size() - 1);
                }
            }
        }
        return false;
    }

    public void printSolution(){
        for (MapColoringVariable variable: solution) {
            System.out.print(variable.getName() + "  " + variable.getAssignment());
            System.out.println();
        }
    }


}
