package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.model.Shop;
import org.springframework.stereotype.Service;

import java.time.Month;
@Service
public interface FinancialManager {
     double getMonthlyPrice(Shop shop, Month month, int year);
     int getAmountOfSandwichesOrdered(Shop shop, Month month,int year);
     //double totalPriceOfSession(Session session);
     //public double dayPriceOfSession(Session session, LocalDate day);
     public double averagePriceSandwich(Shop shop, Month month,int year);
}
