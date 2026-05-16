package mate.academy.hibernate.relations;

import java.util.List;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Country usa = new Country("USA");
        CountryService countryService = new CountryServiceImpl((CountryDao) sessionFactory);
        countryService.add(usa);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        ActorService actorService = new ActorServiceImpl((ActorDao) sessionFactory);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        MovieService movieService = new MovieServiceImpl((MovieDao) sessionFactory);
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
