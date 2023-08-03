package ExchangeService.Services;


import ExchangeService.Models.Currency;
import ExchangeService.Models.CurrencyExchangeRate;
import ExchangeService.Repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class CurrencyService {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/currencies";
    public static final String USER = "root";
    public static final String PASS = "Nazar123";

    private final CurrencyRepository currnecyRepository;

    @Autowired

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currnecyRepository = currencyRepository;
    }

    public List<Currency> findAllCurrencies()
    {
        List<Currency> currencies = currnecyRepository.findAll();
        return currencies;
    }

    public Currency saveCurrency(Currency currency)
    {
        return currnecyRepository.save(currency);
    }

    public Currency findByCode(String code)
    {
        return currnecyRepository.findByCode(code);
    }

    public List<CurrencyExchangeRate> findAllExchangeRates()
    {

        List<CurrencyExchangeRate> allExRates = new LinkedList<>();
        Connection con= null;
        Statement stmt= null;

        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectAllQuery = "SELECT * FROM exchanges;";
            ResultSet rs=stmt.executeQuery(selectAllQuery);
            while (rs.next())
            {
                allExRates.add(new CurrencyExchangeRate( currnecyRepository.findById(rs.getLong("SourceCurrencyId")).get().getCode(),
                        currnecyRepository.findById(rs.getLong("DestCurrencyId")).get().getCode(),
                        rs.getDouble("Conversion")));
            }
        } catch (SQLException e) {
            System.err.println("Error at: Getting Ex Rates from exchanges table");
        }
        return allExRates;
    }

}
