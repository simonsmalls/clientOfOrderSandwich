package be.abis.cllientsandwich.model;

import be.abis.cllientsandwich.exception.NullInputException;
import be.abis.cllientsandwich.exception.PersonAlreadyInSessionException;
import be.abis.cllientsandwich.exception.PersonNotInSessionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Person> personList= new ArrayList<>();

    public Session(String name) {
        this.name = name;
    }

    public Session(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return name;
    }


    // business

    public void addPerson(Person p) throws PersonAlreadyInSessionException, NullInputException {
        if(p==null)throw new NullInputException("input is null");
        if(personList.contains(p)) throw new PersonAlreadyInSessionException("person is already in the session");
        if(personList.stream().map(per->per.getName()).anyMatch(x->x.equals(p.getName()))){
            p.setFirstName(p.getName()+"2");
            System.out.println("2 is added to "+p.getName()+" because there are 2 people with the same name");
        }
        personList.add(p);

    }

    public void removePerson(Person p) throws PersonNotInSessionException, NullInputException {
        if(p==null)throw new NullInputException("input is null");
        if (!personList.contains(p)) throw new PersonNotInSessionException(p.getName()+" is not in this session");
        personList.remove(p);

    }


    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
