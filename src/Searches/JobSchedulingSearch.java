package Searches;

//import CSPs.JobSchedulingCSP.java;

import CSPs.JobSchedulingCSP;
import Variables.JobSchedulingVariable;

import java.util.ArrayList;

public class JobSchedulingSearch {

    private ArrayList<JobSchedulingVariable> solution = new ArrayList<>();

    public ArrayList<JobSchedulingVariable> BacktrackingSearch(JobSchedulingCSP csp) {
        if (Backtrack(solution, csp)) {
            return solution;
        }
        return new ArrayList<>();
    }

    public boolean Backtrack(ArrayList<JobSchedulingVariable> solution, JobSchedulingCSP csp) {
        if (csp.assignmentCompleted()) return true;
        JobSchedulingVariable currentVariable = csp.nextUnassignedVariable();
        for (Integer startingTime: currentVariable.getJobSchedulingDomain().getDomain()) {
            if (csp.checkConsistency(currentVariable, startingTime)) {
                currentVariable.assign(startingTime);
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

    public void printSolution() {
        for (JobSchedulingVariable variable : solution) {
            System.out.print(variable.getName() + "  " + variable.getAssignment());
            System.out.println();
        }
    }

}
