package ru.tkachenko.ecare.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "contracts_options",
    joinColumns = {@JoinColumn(name = "contract_id")},
    inverseJoinColumns = {@JoinColumn(name = "option_id")})
    private Set<Option> optionSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Column(name = "status")
    private boolean status;

    public Contract(){}

    public Contract(int id, int number, Set<Option> optionSet, Client client, Tariff tariff, boolean status) {
        this.id = id;
        this.number = number;
        this.optionSet = optionSet;
        this.client = client;
        this.tariff = tariff;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Option> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(Set<Option> optionSet) {
        this.optionSet = optionSet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
