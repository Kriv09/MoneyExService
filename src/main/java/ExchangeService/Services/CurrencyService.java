package ExchangeService.Services;


import ExchangeService.Exceptions.ExchangeRateNotFoundException;
import ExchangeService.Models.Currency;
import ExchangeService.Models.CurrencyExchangePair;
import ExchangeService.Models.CurrencyExchangeRate;
import ExchangeService.Models.UserExchanges;
import ExchangeService.Repositories.CurrencyRepository;
import org.apache.catalina.User;
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

    public boolean ExistExchangeRateBetween(Long FirstCurrencyId, Long SecondCurrencyId)
    {
        Connection con= null;
        Statement stmt= null;
        boolean ExistRecord = false;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectAllQuery = "SELECT * FROM exchanges WHERE SourceCurrencyId = " + FirstCurrencyId + " AND DestCurrencyId = " + SecondCurrencyId + ";" ;
            ResultSet rs=stmt.executeQuery(selectAllQuery);
            ExistRecord = rs.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return ExistRecord;
    }

    public Long getIdByCode(String Code)
    {
        Connection con= null;
        Statement stmt= null;
        Long resultId = -1L;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectQuery = "SELECT Id FROM currency WHERE Code = '" + Code + "';";
            ResultSet rs=stmt.executeQuery(selectQuery);
            if(rs.next()) {
                resultId = rs.getLong("Id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultId;

    }

    public Double getConversion(Long firstCurrencyId, Long SecondCurrencyId)
            throws ExchangeRateNotFoundException
    {
        Double Conversion = 0.0;
        Connection con= null;
        Statement stmt= null;

        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectQuery = "SELECT Conversion FROM exchanges WHERE SourceCurrencyId = " + firstCurrencyId + " and DestCurrencyId = " + SecondCurrencyId + ";";
            ResultSet rs= stmt.executeQuery(selectQuery);
            if(rs.next())
                Conversion = rs.getDouble("Conversion");
            if(Conversion == 0.0)
                throw new ExchangeRateNotFoundException();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Conversion;
    }

    public void saveCurrencyExchangedPair(CurrencyExchangePair currencyExchangePair)
    {
        Connection con= null;
        Statement stmt= null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String InsertingQuery = "INSERT INTO userexchanges(currencyPair,SourceCurrencyAmount,ResultExchanging) VALUES('"
                    + currencyExchangePair.getSourceCurrencyCode() + "/" + currencyExchangePair.getDestCurrencyCode() + "', "
                    + currencyExchangePair.getSourceCurrencyAmount() + ", " + currencyExchangePair.getResultExchanging() + ");";
            stmt.executeUpdate(InsertingQuery);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<UserExchanges> findAllUserExchanges()
    {
        List<UserExchanges> allUserExchanges = new LinkedList<>();
        Connection con= null;
        Statement stmt= null;
        Long resultId = -1L;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            String selectAllQuery = "SELECT * FROM userexchanges";
            ResultSet rs=stmt.executeQuery(selectAllQuery);
            while(rs.next()) {
                allUserExchanges.add(
                        new UserExchanges(
                                rs.getString("currencyPair"),
                                rs.getDouble("SourceCurrencyAmount"),
                                rs.getDouble("ResultExchanging")
                        )
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return allUserExchanges;
    }

}
