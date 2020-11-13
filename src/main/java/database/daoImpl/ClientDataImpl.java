package database.daoImpl;

import database.dao.ClientDataDao;
import database.entity.ClientData;
import database.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;

public class ClientDataImpl implements ClientDataDao {
    @Override
    public ClientData save(ClientData clientData) {

        Session session = HibernateUtils.oneInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(clientData);
        session.getTransaction().commit();
        session.close();

        return clientData;

    }

    @Override
    public ClientData findByPin(Integer pin) {
        ClientData clientData = null;

        Session session = HibernateUtils.oneInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        clientData = session.createQuery("FROM ClientData WHERE pin =:pin", ClientData.class)
                .setParameter("pin", pin)
                .getSingleResult();

        session.getTransaction().commit();
        session.close();

        return clientData;
    }

    @Override
    public List<ClientData> findAll() {

        Session session = HibernateUtils.oneInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<ClientData> allClientsFromDatabase = session.createQuery("from ClientData", ClientData.class).list();
        session.getTransaction().commit();
        session.close();

        return allClientsFromDatabase;
    }


    @Override
    public void updateClient() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id klienta.");
        Integer id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Podaj nowe nazwisko.");
        String newSurname = scanner.nextLine();

        ClientData clientData;
        Session session = HibernateUtils.oneInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        clientData = session.createQuery("from ClientData where id =:id", ClientData.class)
                .setParameter("id", id)
                .getSingleResult();

        clientData.setSurname(newSurname);
        session.saveOrUpdate(clientData);
        session.getTransaction().commit();
        session.close();

    }


    @Override
    public void deleteById(Integer id) {

        Session session = HibernateUtils.oneInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.createQuery("DELETE ClientData WHERE id=:id")
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public ClientData findById(Integer id) {

        ClientData clientData = null;

        Session session = HibernateUtils.oneInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            clientData = session.createQuery("FROM ClientData WHERE id =:id", ClientData.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (NoResultException e) {

        }

        session.getTransaction().commit();
        session.close();

        return clientData;
    }


}