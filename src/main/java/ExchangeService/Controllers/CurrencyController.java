package ExchangeService.Controllers;


import ExchangeService.Models.Currency;
import ExchangeService.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping("/currencies")
    public String currencies(Model model)
    {
        model.addAttribute("currencies",currencyService.findAllCurrencies());
        return "AllCurrencies";
    }

    @GetMapping("/currencies/addNewCurrency")
    public String createCurrency(Model Model)
    {
        Currency currency = new Currency();
        Model.addAttribute("currency",currency);
        return "currency-create";
    }

    @PostMapping("/currencies/addNewCurrency")
    public String saveCurrency(@ModelAttribute("currency") Currency currency)
    {
        currencyService.saveCurrency(currency);
        return "redirect:/currencies";
    }
}
