package ExchangeService.Controllers;


import ExchangeService.Exceptions.ExchangeRateNotFoundException;
import ExchangeService.Models.Currency;
import ExchangeService.Models.CurrencyExchangePair;
import ExchangeService.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;
    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping
    public String currencies(Model model)
    {
        model.addAttribute("AllUserExchanges", currencyService.findAllUserExchanges());
        model.addAttribute("currencies",currencyService.findAllCurrencies());
        model.addAttribute("ExRates",currencyService.findAllExchangeRates());
        return "AllCurrencies";
    }


    @GetMapping("/addNewCurrency")
    public String createCurrency(Model Model)
    {
        Currency currency = new Currency();
        Model.addAttribute("currency",currency);
        return "currency-create";
    }

    @PostMapping("/addNewCurrency")
    public String saveCurrency(@ModelAttribute("currency") Currency currency)
    {
        currencyService.saveCurrency(currency);
        return "redirect:/currencies";
    }

    @GetMapping("/exchange")
    public String exchangeCurrencies(Model model)
    {
        model.addAttribute("AllCurrencies",currencyService.findAllCurrencies());
        CurrencyExchangePair currencyExchangePair = new CurrencyExchangePair();
        model.addAttribute("ExchangePair",currencyExchangePair);
        return "currency-exchange";
    }

    @PostMapping("/exchange")
    public String exchangeCurrency(@ModelAttribute("ExchangePair") CurrencyExchangePair currencyExchangePair)
    {
        Long SrcCurrencyId = currencyService.getIdByCode(currencyExchangePair.getSourceCurrencyCode());
        Long DestCurrencyId = currencyService.getIdByCode(currencyExchangePair.getDestCurrencyCode());
        Double Conversion = 0.0;

        try {
            Conversion = currencyService.getConversion(SrcCurrencyId,DestCurrencyId);
        } catch (ExchangeRateNotFoundException e) {
            System.err.println(e.getMessage());
            return "exchangerate-notexist";
        }
        currencyExchangePair.setResultExchanging(Conversion * currencyExchangePair.getSourceCurrencyAmount());
        currencyService.saveCurrencyExchangedPair(currencyExchangePair);

        return "redirect:/currencies";
    }




}
