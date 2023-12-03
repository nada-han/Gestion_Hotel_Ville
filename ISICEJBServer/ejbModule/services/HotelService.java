package services;

import java.util.List;

import dao.hotel.IDaoRemote;
import dao.hotel.IDaoLocale;
import entities.Hotel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Stateless(name = "nada")
public class HotelService implements IDaoRemote<Hotel>, IDaoLocale<Hotel>{
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
    @Transactional
    public Hotel create(Hotel o) {
        em.persist(o);
        return o;
    }

    @Override
    @Transactional
    public boolean delete(Hotel o) {
        // Check if the entity is null
        if (o == null) {
            // Handle the error or throw an exception
            return false; // Or throw an exception
        }

        // Check if the entity is managed
        if (!em.contains(o)) {
            // If not managed, merge it into the persistence context
            o = em.merge(o);
        }

        em.remove(o);
        em.flush(); // Ajoute cette ligne pour forcer la suppression immédiate

        return true;
    }


    @Override
    @Transactional
    public Hotel update(Hotel updatedHotel) {
        // Check if the entity is managed
        if (!em.contains(updatedHotel)) {
            // If not managed, merge it into the persistence context
            updatedHotel = em.merge(updatedHotel);
        }

        // Update the existing entity with the changes
        updatedHotel = em.merge(updatedHotel);
        return updatedHotel;
    }

    @Override
    public Hotel findById(int id) {
        return em.find(Hotel.class, id);
    }

    @Override
    public List<Hotel> findAll() {
        Query query = em.createQuery("select h from Hotel h");
        return query.getResultList();
    }

}
