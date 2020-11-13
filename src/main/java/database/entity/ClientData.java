package database.entity;
import application.Account;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Clients_Data")
public class ClientData {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer pin;

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @Column(unique = true)
    private Long pesel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy ="clientData")
    private List<AccountData> accountList;

    public ClientData () {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public List<AccountData> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountData> accountList) {
        this.accountList = accountList;
    }


    @Override
    public String toString() {
        return "ClientData{" +
                "id=" + id +
                ", pin=" + pin +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel=" + pesel +
                // ", accountList=" + accountList +
                '}';
    }
}
