package ExchangeService.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangePair {
    private String SourceCurrencyCode;
    private String DestCurrencyCode;
    private Double SourceCurrencyAmount;
    private Double ResultExchanging;
}
