package hw7;

import java.util.Objects;

public class Building implements Comparable<Building>{
    private String name = "";
    private Integer id = -1;
    private Integer x_coordinate = -1;
    private Integer y_coordinate = -1;

    public Building(String name, int id, int x_coordinate, int y_coordinate) {
        this.name = name;
        this.id = id;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getX_coordinate() {
        return x_coordinate;
    }

    public Integer getY_coordinate() {
        return y_coordinate;
    }

    public Boolean isIntersection() {
        return name.equals("");
    }

    public Double calculateDistance(Building b) {
        return Math.sqrt(
                        Math.pow(getX_coordinate() - b.getX_coordinate(), 2.0) +
                        Math.pow(getY_coordinate() - b.getY_coordinate(), 2.0)
                );
    }

    // Calculate the phase, from North, the target will be in
    public Integer calculatePhase(Building b) {
        if (getX_coordinate() <= b.getX_coordinate() && getY_coordinate() <= b.getY_coordinate()) {
            return 4;
        } else if (getX_coordinate() >= b.getX_coordinate() && getY_coordinate() <= b.getY_coordinate()) {
            return 3;
        } else if (getX_coordinate() >= b.getX_coordinate() && getY_coordinate() >= b.getY_coordinate()) {
            return 2;
        } else {
            return 1;
        }
    }

    public Double calculateAngle(Building b) {
        double angle = Math.toDegrees(
                                            Math.atan2(
                                                    Math.abs(getX_coordinate() - b.getX_coordinate()),
                                                    Math.abs(getY_coordinate() - b.getY_coordinate())
                                            ));
        int phase = calculatePhase(b);
        switch(phase) {
            case 2:
                angle = 360 - angle;
                break;
            case 3:
                angle = 180 + angle;
                break;
            case 4:
                angle = 180 - angle;
                break;
            default:
        }

        return angle;
    }

    @Override
    public int compareTo(Building building) {
        int name = getName().compareTo(building.getName());
        if (name == 0) {
            return getId().compareTo(building.getId());
        }

        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building)) return false;
        Building building = (Building) o;
        return getName().equals(building.getName()) &&
                getId().equals(building.getId()) &&
                getX_coordinate().equals(building.getX_coordinate()) &&
                getY_coordinate().equals(building.getY_coordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getX_coordinate(), getY_coordinate());
    }

    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", x_coordinate=" + x_coordinate +
                ", y_coordinate=" + y_coordinate +
                '}';
    }
}
