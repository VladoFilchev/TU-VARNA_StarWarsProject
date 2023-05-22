package models;

import enums.CelestialType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
public class CelestialObject {
    @XmlElement
    private String name;
    @XmlElement
    private CelestialType celestialType;
    @XmlTransient
    private Set<Jedi> jedis;

    public CelestialObject() {
        this.jedis = new HashSet<>();
    }

    public CelestialObject(String name,CelestialType celestialType) {
        this.name = name;
        this.celestialType=celestialType;
        this.jedis = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement

    public Set<Jedi> getJedis() {
        return jedis;
    }

    public void setJedis(Set<Jedi> jedis) {
        this.jedis = jedis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialObject celestialObject = (CelestialObject) o;
        return name.equals(celestialObject.name) && jedis.equals(celestialObject.jedis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jedis);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", jedis=" + jedis +
                '}';
    }

    public boolean addJedi(Jedi jedi) {
        return jedis.add(jedi);
    }

    public boolean removeJedi(Jedi jedi) {
        return jedis.remove(jedi);
    }

}
