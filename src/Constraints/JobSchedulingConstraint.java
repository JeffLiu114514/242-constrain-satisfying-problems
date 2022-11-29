package Constraints;

import Variables.JobSchedulingVariable;

public class JobSchedulingConstraint {

    private JobSchedulingVariable var1, var2;
    private JobSchedulingConstraintType type;

    public JobSchedulingConstraint(JobSchedulingVariable var1, JobSchedulingVariable var2, JobSchedulingConstraintType type) {
        this.var1 = var1;
        this.var2 = var2;
        this.type = type;
    }

    public enum JobSchedulingConstraintType {
        Precedence,
        Disjoint
    }

    public JobSchedulingVariable getVar1() {
        return var1;
    }

    public JobSchedulingVariable getVar2() {
        return var2;
    }

    public JobSchedulingConstraintType getType() {
        return type;
    }

}
