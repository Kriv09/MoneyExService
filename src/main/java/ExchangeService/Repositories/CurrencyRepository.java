package ExchangeService.Repositories;


import ExchangeService.Models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {

    Currency findByCode(String code);
}
