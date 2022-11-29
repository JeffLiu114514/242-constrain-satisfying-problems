package Variables;

import Domains.JobSchedulingDomain;

import java.util.ArrayList;

public class JobSchedulingVariable {
    private String name;
    private int workingPeriod;
    private JobSchedulingDomain domain;
    private int assignment; //starting time

    public JobSchedulingVariable(String jobName) {
        this.name = jobName;
        this.assignment = -1; //not assigned
    }

    public void initializeDomain() {
        ArrayList<Integer> domainList = new ArrayList<>();
        for (int i = 0; i < domain.getTmax(); i++) {
            domainList.add(i);
        }
        this.domain.setDomain(domainList);
    }

    public JobSchedulingDomain getJobSchedulingDomain() {
        return domain;
    }

    public void setDomain(JobSchedulingDomain domain) {
        this.domain = domain;
    }

    public int getAssignment() {return assignment;}

    public void assign(int assignment) {this.assignment = assignment;}

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public int getWorkingPeriod() {return workingPeriod;}

    public void setWorkingPeriod(int workingPeriod) {
        this.workingPeriod = workingPeriod;
    }
}
