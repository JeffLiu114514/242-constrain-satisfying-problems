package Variables;

import Domains.MapColoringDomain;

import java.util.ArrayList;
public class MapColoringVariable {
    private String name; //name of the area
    private ArrayList<MapColoringVariable> Neighbors;
    private MapColoringDomain domain; //domain of colors
    private String assignment;

    public MapColoringVariable(String Name) {
        this.name = Name;
    }

    public String getName(){return name;}
    public ArrayList<MapColoringVariable> getNeighbors(){return Neighbors;}
    public void SetName(String Name){this.name = Name;}
    public void SetNeighbors(ArrayList<MapColoringVariable> Neighbors){this.Neighbors = Neighbors;}
    public void assign(String color) {this.assignment = color;}
    public String getAssignment() {return assignment;}
    public void setDomain(MapColoringDomain domain) {this.domain = domain;}
    public MapColoringDomain getDomain() {return domain;}
}