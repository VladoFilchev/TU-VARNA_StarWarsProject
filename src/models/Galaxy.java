package models;

import models.CelestialObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class Galaxy {
    @XmlTransient
    private Set<CelestialObject> celestialObjectSet;

    public Galaxy() {
        this.celestialObjectSet = new HashSet<>();
    }

    public boolean addCelestialObject(CelestialObject celestialObject) {
        return celestialObjectSet.add(celestialObject);
    }

    public boolean removeCelestialObject(CelestialObject celestialObject) {
        return celestialObjectSet.remove(celestialObject);
    }

    @XmlElement(name = "CelestialObject")
    public Set<CelestialObject> getCelestialObjectSet() {
        return celestialObjectSet;
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "celestialObjectSet=" + celestialObjectSet +
                '}';
    }
}
