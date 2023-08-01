package ExchangeService.Services;


import ExchangeService.Models.Currency;
import ExchangeService.Repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

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


}
