package Variables;

import Constraints.JobSchedulingConstraint;

import java.util.ArrayList;

public class JobSchedulingVariablePlusConstraint {
    private ArrayList<JobSchedulingVariable> variables;
    private ArrayList<JobSchedulingConstraint> constraints;

    public JobSchedulingVariablePlusConstraint(ArrayList<JobSchedulingVariable> variables, ArrayList<JobSchedulingConstraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
    }

    public ArrayList<JobSchedulingVariable> getVariables() {
        return variables;
    }

    public ArrayList<JobSchedulingConstraint> getConstraints() {
        return constraints;
    }
}
