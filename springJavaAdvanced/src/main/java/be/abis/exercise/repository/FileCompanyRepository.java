package be.abis.exercise.repository;

import be.abis.exercise.model.Company;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class FileCompanyRepository implements CompanyRepository {
	
	private ArrayList<Company> companies = new ArrayList<Company>();

	public FileCompanyRepository(){
		try {
			List<String> compStrings = Files.readAllLines(Paths.get("c:\\temp\\javacourses\\companies.txt"));
			for(String s:compStrings){
				companies.add(new Company(s.trim()));
			}
			companies.trimToSize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(ArrayList<Company> companies) {
		this.companies = companies;
	}
	
	@Override
	public void printCompaniesSortedToFile(String file){
		Collections.sort(companies);
		try (PrintWriter pw = new PrintWriter(file)){
		    for (Company c: companies){
		    	pw.println(c);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
