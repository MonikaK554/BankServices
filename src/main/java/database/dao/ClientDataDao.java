package database.dao;

import database.entity.ClientData;

import java.util.List;

public interface ClientDataDao {

    ClientData save(ClientData clientData);
    ClientData findByPin (Integer pin);
    ClientData findById (Integer id);
    List<ClientData> findAll();
    void updateClient ();
    void deleteById (Integer id);



}