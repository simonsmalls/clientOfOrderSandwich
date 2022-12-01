package be.abis.exercise;

import be.abis.exercise.model.Company;
import be.abis.exercise.repository.CompanyRepository;
import be.abis.exercise.repository.FileCompanyRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReadCompanies {

	@Test
	void numberOfCompaniesInFileIs5(){
		
		CompanyRepository cr = new FileCompanyRepository();
		List<Company> comps = cr.getCompanies();

		assertEquals(5,comps.size());

	}

}
