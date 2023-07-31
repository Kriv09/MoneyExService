package ExchangeService.Controllers;


import ExchangeService.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
