package ExchangeService.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExchanges {
    private String CurrencyPair;
    private Double SourceCurrencyAmount;
    private Double ResultExchanging;
}
