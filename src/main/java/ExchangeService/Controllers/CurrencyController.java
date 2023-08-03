package ExchangeService.Controllers;


import ExchangeService.Models.Currency;
import ExchangeService.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
