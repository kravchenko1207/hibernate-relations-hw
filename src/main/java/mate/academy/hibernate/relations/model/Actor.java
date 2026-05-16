package mate.academy.hibernate.relations.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "actors")
public class Actor implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "country_id")
    private Country country;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "actors_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;

    public Actor() {
    }

    public Actor(String name) {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public Actor clone() {
        try {
            Actor actor = (Actor) super.clone();
            if (country != null) {
                actor.setCountry(country.clone());
            }
            return actor;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't make clone of " + this, e);
        }
    }

    @Override
    public String toString() {
        return "Actor{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", country='" + country + '\''
                + '}';
    }
}
