package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class VisitService {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public void fillDatabase() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Doctor doctor = new Doctor();
        doctor.setName("Jan");
        doctor.setSurname("Kowalski");
        doctor.setDocktorType(DocktorType.DERMATOLOGIST);
        em.persist(doctor);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Anna");
        doctor2.setSurname("Nowak");
        doctor2.setDocktorType(DocktorType.NEUROLOGIST);
        em.persist(doctor2);

        Doctor doctor3 = new Doctor();
        doctor3.setName("Piotr");
        doctor3.setSurname("Kowalski");
        doctor3.setDocktorType(DocktorType.UROLOGIST);
        em.persist(doctor3);

        em.getTransaction().commit();
        em.close();

    }
    public Visit getVisitById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Visit visit = em.find(Visit.class, id);
        em.getTransaction().commit();
        em.close();
        return visit;
    }

    public void saveVisit(Visit visit) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(visit);
        em.getTransaction().commit();
        em.close();
    }

    public void updateVisit(Long id, BufferedReader reader) throws IOException {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Visit visit = em.find(Visit.class, id);
        System.out.println("Podaj o ile dni chcesz przesunac wizyte");
        LocalDateTime visitDate = visit.getVisitDate();
        int days = Integer.parseInt(reader.readLine());
        visit.setVisitDate(visitDate.plusDays(days));
        em.merge(visit);
        System.out.println("Wizyta zostala przesunieta o " + days + " dni");

        em.getTransaction().commit();
        em.close();

    }

    public void deleteVisit(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Visit visit = em.find(Visit.class, id);
        em.remove(visit);
        em.getTransaction().commit();
        em.close();
    }

    public Doctor getDoctorById(long l) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Doctor doctor = em.find(Doctor.class, l);
        em.getTransaction().commit();
        em.close();
        return doctor;
    }

}
