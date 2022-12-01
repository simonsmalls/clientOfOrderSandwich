package be.abis.cllientsandwich.service;

import be.abis.cllientsandwich.model.FinanceModel;
import be.abis.cllientsandwich.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Month;

@Service
public class FinancialManagerImp implements FinancialManager{


    @Autowired
    private RestTemplate rt;

    String baseUrl="http://localhost:8080/ordersandwich/api/finance";
    @Override
    public double getMonthlyPrice(Shop shop, Month month, int year) {

        FinanceModel model = new FinanceModel();
        model.setShop(shop);
        model.setMonth(month);
        model.setYear(year);

        return 0;
    }

    @Override
    public int getAmountOfSandwichesOrdered(Shop shop, Month month, int year) {
        FinanceModel model = new FinanceModel();
        model.setShop(shop);
        model.setMonth(month);
        model.setYear(year);
        return 0;
    }

    @Override
    public double averagePriceSandwich(Shop shop, Month month, int year) {
        FinanceModel model = new FinanceModel();
        model.setShop(shop);
        model.setMonth(month);
        model.setYear(year);
        return 0;
    }
}
