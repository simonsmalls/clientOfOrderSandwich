package be.abis.exercise.repository;

import be.abis.exercise.exception.EmailException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FilePersonRepository implements PersonRepository {

    private List<Person> personList=new ArrayList<>();
    //private static final FilePersonRepository filePersonRepository=new FilePersonRepository();
    Logger l2 = LogManager.getLogger("Console");
    Logger l = LogManager.getLogger("exceptionLogger");

    public FilePersonRepository() {
        try (BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\javacourses\\persons.csv"))) {
            String line;
            personList=new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                Person person=readIn(values);
                personList.add(person);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void writeAllPersonsToFile() {

        Address address=new Address("loui","9","0100","leuv","be","BE");
        Company company=new Company("abis",address);

        Person p1= null;
        Person p2=null;
        try {
            p1 = new Person("simon","haas", LocalDate.of(1997,9,3),"email@","test",company);
            p2=new Person("marcel","hassel",LocalDate.now(),"email","moeilijkpssaa");
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

        Person p3=new Person("jana","heil");

        personList.add(p1);
        personList.add(p2);
        personList.add(p3);


        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        StringBuilder sb=new StringBuilder();



        try {
            fw = new FileWriter("c:\\temp\\Javacourses\\persons.csv", false);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            for (Person person:personList){
                sb=new StringBuilder();
                sb.append(person.getPersonNumber());
                sb.append(";");
                sb.append(person.getFirstName());
                sb.append(";");
                sb.append(person.getLastName());
                sb.append(";");

                if (person.getBirthDate()!=null){
                    sb.append(person.getBirthDate());
                    sb.append(";");

                }else {
                    sb.append("null;");
                }
                if (person.getEmail()!=null){
                    sb.append(person.getEmail());
                    sb.append(";");
                }else {
                    sb.append("null;");
                }
                if (person.getPassword()!=null){
                    sb.append(person.getPassword());
                    sb.append(";");

                }else {
                    sb.append("null;");
                }

                if(person.getCompany()!=null){
                    sb.append(person.getCompany().getName());
                    sb.append(";");
                    if(person.getCompany().getAddress()!=null){
                        sb.append(person.getCompany().getAddress().getStreet());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getNr());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getZipCode());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getTown());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getCountry());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getCountryCode());
                        sb.append(";");


                    }else{
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");

                    }
                }else{
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                }
                pw.println(sb);

            }


            pw.flush(); } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            }
            catch (IOException io) {}



        }


    }

    public List<Person> FileToPersonList(String path){
        List<Person> personList2=new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                Person person=readIn(values);
                personList2.add(person);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return personList2;
    }

    public void writePersonToFile(Path path,Person person){
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        StringBuilder sb=new StringBuilder();
        try {
            fw = new FileWriter("c:\\temp\\Javacourses\\persons2.csv", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);


                sb=new StringBuilder();
                sb.append(person.getPersonNumber());
                sb.append(";");
                sb.append(person.getFirstName());
                sb.append(";");
                sb.append(person.getLastName());
                sb.append(";");

                if (person.getBirthDate()!=null){
                    sb.append(person.getBirthDate());
                    sb.append(";");

                }else {
                    sb.append("null;");
                }
                if (person.getEmail()!=null){
                    sb.append(person.getEmail());
                    sb.append(";");
                }else {
                    sb.append("null;");
                }
                if (person.getPassword()!=null){
                    sb.append(person.getPassword());
                    sb.append(";");

                }else {
                    sb.append("null;");
                }

                if(person.getCompany()!=null){
                    sb.append(person.getCompany().getName());
                    sb.append(";");
                    if(person.getCompany().getAddress()!=null){
                        sb.append(person.getCompany().getAddress().getStreet());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getNr());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getZipCode());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getTown());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getCountry());
                        sb.append(";");
                        sb.append(person.getCompany().getAddress().getCountryCode());
                        sb.append(";");


                    }else{
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");
                        sb.append("null;");

                    }
                }else{
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                    sb.append("null;");
                }
                pw.println(sb);




            pw.flush(); } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            }
            catch (IOException io) {}



        }

    }

    public Person readInString(String line){
        String[] values = line.split(";");
        Person person=readIn(values);
        return person;

    }

    public Person readIn(String[] values){
        Company company=null;
        Address address=null;
        String email = null;
        String password=null;
        LocalDate birth=null;


        if (!values[11].equals("null")){
            address=new Address(values[7],values[8],values[9],values[10],values[11],values[12]);
            company=new Company(values[6],address);
        }else if(!values[6].equals("null")){
            company=new Company(values[6]);
        }
        if(!values[5].equals("null")){
            password=values[5];

        }
        if(!values[4].equals("null")){
            email  =values[4];

        }
        if(!values[3].equals("null")){
            String day =values[3].substring(8,10);
            String month=values[3].substring(5,7);
            String year=values[3].substring(0,4);
            //System.out.println(day+month+year);
            birth = LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));

        }
        Person person=null;
        try {

            if (company != null && email != null) {
                person = new Person(values[1], values[2], birth, email, password, company);

            } else if (company != null && email == null) {
                person = new Person(values[1], values[2], company);
            } else if (company == null && email != null) {
                person = new Person(values[1], values[2], birth, email, password);
            } else person = new Person(values[1], values[2]);

            person.setPersonNumber(Integer.parseInt(values[0]));
        }catch (EmailException e){

        }

        return person;

    }


    public void addPerson(Person p){
        personList.add(p);
        l2.info(p.getFirstName()+ " is added");
    }

    public Person findPerson(int id) throws PersonNotFoundException {
        List<Person> result = personList.stream().filter(s->s.getPersonNumber()==id).collect(Collectors.toList());
        if(result.size()<1) {
            l.error("person not found");
            throw new PersonNotFoundException("person not found");
        }


        return result.get(0);
    }

    public Person findPerson(String email,String pass) throws PersonNotFoundException {
        for(Person p:personList){
            if(p.getPassword()==null) {

                p.setPassword("");
            }
        }

        List<Person> result = personList.stream().filter(s->s.getEmail()!=null && s.getEmail().equals(email) && s.getPassword().equals(pass)).collect(Collectors.toList());
        if(result.size()<1) throw new PersonNotFoundException("person not found");


        return result.get(0);
    }

    public List<Person> removePersonsWithoutCompany(){
        List<Person> result = personList.stream().filter(s->s.getCompany()!=null).collect(Collectors.toList());
        return result;


    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
