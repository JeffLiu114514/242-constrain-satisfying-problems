package CSPs;

import Constraints.JobSchedulingConstraint;
import Variables.JobSchedulingVariable;

import java.util.ArrayList;

public class JobSchedulingCSP{
    private ArrayList<JobSchedulingVariable> variables;
    private ArrayList<JobSchedulingConstraint> constraints;

    public JobSchedulingCSP(ArrayList<JobSchedulingVariable> variables, ArrayList<JobSchedulingConstraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
    }

    public JobSchedulingVariable nextUnassignedVariable() {
        for (JobSchedulingVariable variable : variables) {
            if (variable.getAssignment() == -1) return variable;
        }
        return null;
    }

    public boolean checkConsistency(JobSchedulingVariable variable, int assignment) {
        for (JobSchedulingConstraint constraint: constraints) {
            if (constraint.getVar1().equals(variable) || constraint.getVar2().equals(variable)) { //constraint involves this variable
                if (constraint.getType().equals(JobSchedulingConstraint.JobSchedulingConstraintType.Precedence)) {//precedence constraint
                    if (constraint.getVar1().equals(variable)) {//variable before the other one
                        if (constraint.getVar2().getAssignment() != -1) {//if the other variable has been assigned a starting time
                            if ((assignment + variable.getWorkingPeriod()) > constraint.getVar2().getAssignment()) return false; //the assigned starting time is after the other variable, constraint violation
                        }
                    } else {//variable after the other one
                        if (constraint.getVar1().getAssignment() != -1) {//if the other variable has been assigned a starting time
                            if (assignment < (constraint.getVar1().getAssignment() + constraint.getVar1().getWorkingPeriod())) return false; //the assigned starting time is before the other variable, constraint violation
                        }
                    }
                }
                else if (constraint.getType().equals(JobSchedulingConstraint.JobSchedulingConstraintType.Disjoint)) {
                    if (constraint.getVar1().equals(variable)) {
                        if (constraint.getVar2().getAssignment()!= -1) {
                            int startingTime2 = constraint.getVar2().getAssignment();
                            int endingTime2 = constraint.getVar2().getAssignment() + constraint.getVar2().getWorkingPeriod();
                            int startingTime1 = assignment;
                            int endingTime1 = assignment + variable.getWorkingPeriod();
                            if (startingTime1 < startingTime2) {//Do var1 first
                                if (endingTime1 > startingTime2) return false; //var2 starts before var1 ends
                            } else if (startingTime1 > startingTime2) { //Do var2 first
                                if (endingTime2 > startingTime1) return false; //var1 starts before var2 ends
                            } else {return false;} //Starting time the same, false.
                        }
                    } else {
                        if (constraint.getVar1().getAssignment() != -1) {
                            int startingTime2 = assignment;
                            int endingTime2 = assignment + variable.getWorkingPeriod();
                            int startingTime1 = constraint.getVar1().getAssignment();
                            int endingTime1 = constraint.getVar1().getAssignment()+constraint.getVar1().getWorkingPeriod();
                            if (startingTime1 < startingTime2) {//Do var1 first
                                if (endingTime1 > startingTime2) return false; //var2 starts before var1 ends
                            } else if (startingTime1 > startingTime2) { //Do var2 first
                                if (endingTime2 > startingTime1) return false; //var1 starts before var2 ends
                            } else {return false;} //Starting time the same, false.
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean assignmentCompleted(){
        for (JobSchedulingVariable variable: variables) {
            if(variable.getAssignment() == -1) return false;
        }
        return true;
    }

}

