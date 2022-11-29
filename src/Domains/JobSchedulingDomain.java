package Domains;

import java.util.ArrayList;

public class JobSchedulingDomain {
    private int Tmax;
    private ArrayList<Integer> domain = new ArrayList<>();

    public JobSchedulingDomain(int Tmax) {
        this.Tmax = Tmax;
    }

    public int getTmax() {return Tmax;}

    public ArrayList<Integer> getDomain() {
        return domain;
    }

    public void setDomain(ArrayList<Integer> domain) {
        this.domain = domain;
    }
}
