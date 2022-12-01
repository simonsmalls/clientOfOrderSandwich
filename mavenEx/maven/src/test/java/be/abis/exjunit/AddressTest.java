package be.abis.exjunit;


import be.abis.exjunit.model.Address;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {



    @Test
    void belgianZipCodeShouldBeNumeric(){
        Address address=new Address("lou","9","3440","zout","Belg","BE");
        assertTrue(address.isBelgianZipCodeNumeric());

    }

    @Test
    void addToFile(){
        String line=null;
        String line1=null;
        int index1=0;
        File file = new File("c:\\temp\\javacourses\\address.csv");
        file.setWritable(true);
        try (BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\javacourses\\address.csv"))) {

            while ((line = br.readLine()) != null) {
                index1+=1;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Address address=new Address("lou","9","3440","zout","Belg","BE");
        try {
            address.writeToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int index=0;
        try (BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\javacourses\\address.csv"))) {

            while ((line = br.readLine()) != null) {
                line1=line;
                index+=1;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(address.getStreet()+";"+address.getNr()+";"+address.getZipCode()+";"+address.getTown()+";"+address.getCountry()+";"
                +address.getCountryCode()+";1"
                ,line1+(index-index1));


    }


    @Test
    void FileReadable(){
        String line=null;
        String line1=null;

        int index1=0;
        try (BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\javacourses\\address.csv"))) {

            while ((line = br.readLine()) != null) {
                index1+=1;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Address address=new Address("lou","9","3440","zout","Belg","BE");
        File file = new File("c:\\temp\\javacourses\\address.csv");
        file.setReadOnly();

        assertThrows(IOException.class,()->address.writeToFile());

    }

}