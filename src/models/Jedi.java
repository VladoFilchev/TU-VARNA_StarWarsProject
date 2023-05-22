package models;

import enums.Ranks;
import enums.SaberColors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Jedi {
    @XmlElement
    private String name;
    @XmlElement
    private int age;
    @XmlElement
    private Ranks rank;
    @XmlElement
    private SaberColors saberColor;
    @XmlElement
    private double power;

    public Jedi() {

    }

    public Jedi(String name, int age, Ranks rank, SaberColors saberColor, double power) {
        this.name = name;
        this.age = age;
        this.rank = rank;
        this.saberColor = saberColor;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public SaberColors getSaberColor() {
        return saberColor;
    }

    public void setSaberColor(SaberColors saberColor) {
        this.saberColor = saberColor;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jedi jedi = (Jedi) o;
        return name.equals(jedi.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "models.Jedi{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", rank=" + rank +
                ", saberColor='" + saberColor + '\'' +
                ", power=" + power +
                '}';
    }
}
