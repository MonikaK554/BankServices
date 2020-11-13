package database.dao;

import database.entity.AccountData;
import java.util.List;

public interface AccountDataDao {

    void save(AccountData accountData);
    AccountData findByClientIdAnAccountId (Integer clientId, Integer accountId);
    List<AccountData> findAll();
    void deleteByAccountId (Integer accountId);
    void deleteAllAccountsWhileDeletingClient (Integer clientId);
}
