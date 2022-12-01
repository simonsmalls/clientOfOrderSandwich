package be.abis.exercise.repository;

import be.abis.exercise.exception.NoCourseException;
import be.abis.exercise.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class SessionRepositoryImpl implements SessionRepository {

    List<Session> sessionList=new ArrayList<>();
    @Autowired
    PersonRepository personRepository;


    public SessionRepositoryImpl() {

    }

    @PostConstruct
    public void init(){
        try (BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\javacourses\\sessions.csv"))) {
            String line;
            sessionList=new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                Session session =readIn(values);
                sessionList.add(session);

            }

        } catch (IOException | NoCourseException e) {
            System.out.println(e.getMessage());
        }
    }


    public Session readIn(String[] values) throws NoCourseException {
        Course[] courses=Course.values();
        Session session=null;
        if (values[0].equals("PublicSession")){
            Course course=null;


            for(Course c:courses){
                if(c.getTitle().equals(values[1])) course=c;
            }
            if (course==null) throw new NoCourseException("no course");

            String day =values[2].substring(8,10);
            String month=values[2].substring(5,7);
            String year=values[2].substring(0,4);
            //System.out.println(day+month+year);
            LocalDate start = LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
            Address address=new Address(values[4], values[5], values[6], values[7], values[8], values[9]);
            Company company=new Company(values[3],address);
            Person person = new Person(values[10],values[11]);
            List<Person> personList= personRepository.FileToPersonList(values[12]);

            session=new PublicSession(course,start,company,person);
            for (Person p:personList)((PublicSession)session).addEnrolment(p);

        }
        if (values[0].equals("CompanySession")){
            Course course=null;

            for(Course c:courses){
                if(c.getTitle().equals(values[1])) course=c;
            }
            if (course==null) throw new NoCourseException("no course");

            String day =values[2].substring(8,10);
            String month=values[2].substring(5,7);
            String year=values[2].substring(0,4);
            //System.out.println(day+month+year);
            LocalDate start = LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
            Address address=new Address(values[4], values[5], values[6], values[7], values[8], values[9]);
            Company company=new Company(values[3],address);
            Person person = new Person(values[10],values[11]);
            int i=9;

            Address address1=new Address(values[4+i], values[5+i], values[6+i], values[7+i], values[8+i], values[9+i]);
            Company company1=new Company(values[3+i],address1);
            if (values[19].equals("null")){
                session=new CompanySession(course,start,company,person,company1);
            }else {
                int a=Integer.parseInt(values[19]);
                session=new CompanySession(course,start,company,person,company1,a);
            }

        }


        return session;


    }

    public List<Session> findMonth(int month, int year){
        return sessionList.stream().filter(s->s.getDate().getMonth()==Month.of(month) && s.getDate().getYear()==year).collect(Collectors.toList());


    }
    public List<Session> findLocation(Company company){
        return sessionList.stream().filter(s->s.getLocation().equals(company)).collect(Collectors.toList());
    }

    public List<Session> getSessionList() {
        return sessionList;
    }
}
