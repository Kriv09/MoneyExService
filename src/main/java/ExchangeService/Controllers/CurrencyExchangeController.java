package ExchangeService.Controllers;


import ExchangeService.Models.CurrencyExchangeRate;
import ExchangeService.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class CurrencyExchangeController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyExchangeController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping("/currencies/addNewExchangeRate")
    public String addNewExchangeRate(Model model)
    {
        model.addAttribute("AllCurrencies",currencyService.findAllCurrencies());
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
        model.addAttribute("ExchangeRate", currencyExchangeRate);
        return "addNewExchangeRate";
    }

    @PostMapping("/currencies/addNewExchangeRate")
    public String createNewExchangeRate(@ModelAttribute("ExchangeRate") CurrencyExchangeRate currencyExchangeRate)
    {

        if(currencyExchangeRate.getSourceCurrencyCode().equals(currencyExchangeRate.getDestCurrencyCode()))
            return "cant-equal-code";

        Long SourceId = currencyService.findByCode(currencyExchangeRate.getSourceCurrencyCode()).getId();
        Long DestId = currencyService.findByCode(currencyExchangeRate.getDestCurrencyCode()).getId();

        //Data Insertion
        try(Connection conn = DriverManager.getConnection(CurrencyService.DB_URL, CurrencyService.USER, CurrencyService.PASS);
            Statement stmt = conn.createStatement();
        ) {
            // Execute a query

            String sql = "INSERT INTO exchanges(SourceCurrencyId,DestCurrencyId,Conversion) VALUES (" + SourceId + ", " + DestId + ", " + currencyExchangeRate.getConversion().toString() + ");";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error at: Insertion ExchangeRate into exchanges");
        }
        return "redirect:/currencies";
    }
}
