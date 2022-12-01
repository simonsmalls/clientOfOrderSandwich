package be.abis.exercise.repository;

import be.abis.exercise.model.Company;

import java.util.List;

public interface CompanyRepository {
    List<Company> getCompanies();

    void printCompaniesSortedToFile(String file);
}
