import CSPs.JobSchedulingCSP;
import CSPs.MapColoringCSP;
import Constraints.JobSchedulingConstraint;
import Domains.JobSchedulingDomain;
import Domains.MapColoringDomain;
import Searches.JobSchedulingSearch;
import Searches.MapColoringSearch;
import Variables.JobSchedulingVariable;
import Variables.JobSchedulingVariablePlusConstraint;
import Variables.MapColoringVariable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String filename = "C:\\Users\\23566\\Desktop\\CSC242\\Project\\Project2\\Project2LatestVersion\\Project2\\src\\job1.txt";
                //args[0];
        try {
            File file =new File(filename);
            Scanner fileScanner = new Scanner(file);
            String firstLine = fileScanner.nextLine();
            if (firstLine.contains("Map")) {
                ArrayList<MapColoringVariable> variables = readMapColoring(fileScanner);
                MapColoringCSP mapColoringCSP = new MapColoringCSP(variables);
                MapColoringSearch mapColoringSearch = new MapColoringSearch();
                mapColoringSearch.BacktrackingSearch(mapColoringCSP);
                mapColoringSearch.printSolution();
            } else if (firstLine.contains("Job")) {
                JobSchedulingVariablePlusConstraint variablePlusConstraint = readJobShop(fileScanner);
                JobSchedulingCSP jobSchedulingCSP = new JobSchedulingCSP(variablePlusConstraint.getVariables(), variablePlusConstraint.getConstraints());
                JobSchedulingSearch jobSchedulingSearch = new JobSchedulingSearch();
                jobSchedulingSearch.BacktrackingSearch(jobSchedulingCSP);
                jobSchedulingSearch.printSolution();
            } else {
                System.out.println("Can't solve that problem.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println(e);
            //System.out.println("Invalid file!");
            System.exit(0);
        }
    }

    private static ArrayList<MapColoringVariable> readMapColoring(Scanner scanner) {
        ArrayList<MapColoringVariable> variables = new ArrayList<>();
        List<String> variableNames = Arrays.asList(scanner.nextLine().split(" "));
        for (String name : variableNames) {
            variables.add(new MapColoringVariable(name));
        }
        String[] neighborLine;
        for (MapColoringVariable variable : variables) {
            neighborLine = scanner.nextLine().split(" ");
            if (neighborLine.length > 1) {
                ArrayList<MapColoringVariable> neighbors = new ArrayList<>();
                for (int i = 1; i < neighborLine.length; i++) {
                    neighbors.add(variables.get(findVariableByNameColoring(variables, neighborLine[i])));
                }
                variable.SetNeighbors(neighbors);
            }
        }
        String[] domain = scanner.nextLine().split(" ");
        MapColoringDomain mapColoringDomain = new MapColoringDomain(domain);
        for (MapColoringVariable variable : variables) {
            variable.setDomain(mapColoringDomain);
        }
        //modify domain by constraints
        while (scanner.hasNextLine()) {
            String[] unaryConstraints = scanner.nextLine().split(" ");
            if (unaryConstraints[1].equals("!=")) {
                String[] modifiedDomain = new String[domain.length - 1];
                for (int i = 0, j=0; i < domain.length; i++) { // delete the color from domain
                    if (!domain[i].equals(unaryConstraints[2])) {
                        modifiedDomain[j++] = domain[i];
                    }
                }
                Integer variableWithConstraint = findVariableByNameColoring(variables,unaryConstraints[0]);
                if (variableWithConstraint != -1) {
                    variables.get(variableWithConstraint).setDomain(new MapColoringDomain(modifiedDomain));
                }

            } else {
                Integer variableWithConstraint = findVariableByNameColoring(variables,unaryConstraints[0]);
                String[] modifiedDomain = {unaryConstraints[2]};
                if (variableWithConstraint != -1) {
                    variables.get(variableWithConstraint).setDomain(new MapColoringDomain(modifiedDomain));
                }
            }
        }
        return variables;
    }

    private static Integer findVariableByNameColoring(ArrayList<MapColoringVariable> variables, String name) {
        for (MapColoringVariable variable: variables) {
            if (variable.getName().equals(name)) return variables.indexOf(variable);
        }
        return -1;
    }

    private static Integer findVariableByNameJob(ArrayList<JobSchedulingVariable> variables, String name) {
        for (JobSchedulingVariable variable: variables) {
            if (variable.getName().equals(name)) return variables.indexOf(variable);
        }
        return -1;
    }


    private static JobSchedulingVariablePlusConstraint readJobShop(Scanner scanner) {
        ArrayList<JobSchedulingVariable> variables = new ArrayList<>();
        ArrayList<JobSchedulingConstraint> constraints = new ArrayList<>();
        List<String> variableNames = Arrays.asList(scanner.nextLine().split(" "));
        for (String name : variableNames) {
            variables.add(new JobSchedulingVariable(name));
        }
        String[] timeRequired;
        for (JobSchedulingVariable variable: variables) {
            timeRequired = scanner.nextLine().split(" ");
            variable.setWorkingPeriod(Integer.parseInt(timeRequired[1]));
        }
        int Tmax = Integer.parseInt(scanner.nextLine());
        JobSchedulingDomain jobSchedulingDomain = new JobSchedulingDomain(Tmax);
        for (JobSchedulingVariable variable: variables) {
            variable.setDomain(jobSchedulingDomain);
            variable.initializeDomain();
        }

        while (scanner.hasNextLine()) {
            String[] ConstraintLines = scanner.nextLine().split(" ");
            if(ConstraintLines[1].equals("before")){
                Integer indexVar1 = findVariableByNameJob(variables, ConstraintLines[0]);
                Integer indexVar2 = findVariableByNameJob(variables, ConstraintLines[2]);
                if ((indexVar1 != -1) || (indexVar2 != -1)){
                    JobSchedulingConstraint precedenceConstraint = new JobSchedulingConstraint(variables.get(indexVar1),
                            variables.get(indexVar2), JobSchedulingConstraint.JobSchedulingConstraintType.Precedence);
                    constraints.add(precedenceConstraint);
                }
            }else if (ConstraintLines[1].equals("disjoint")){
                Integer indexVar1 = findVariableByNameJob(variables, ConstraintLines[0]);
                Integer indexVar2 = findVariableByNameJob(variables, ConstraintLines[2]);
                if ((indexVar1 != -1) || (indexVar2 != -1)){
                    JobSchedulingConstraint disjointConstraint = new JobSchedulingConstraint(variables.get(indexVar1),
                            variables.get(indexVar2), JobSchedulingConstraint.JobSchedulingConstraintType.Disjoint);
                    constraints.add(disjointConstraint);
                }
            }

        }
        return new JobSchedulingVariablePlusConstraint(variables, constraints);
    }

}