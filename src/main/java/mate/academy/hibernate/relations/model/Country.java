package mate.academy.hibernate.relations.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "countries")
public class Country implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Actor> actorSet = new HashSet<>();

    public Set<Actor> getActorSet() {
        return actorSet;
    }

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Country clone() {
        try {
            return (Country) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't make clone of " + this, e);
        }
    }

    @Override
    public String toString() {
        return "Country{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
