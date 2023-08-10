package ExchangeService.Exceptions;

public class ExchangeRateNotFoundException extends Exception{

    private static final String  DEFAULT_MESSAGE = "Between that 2 currencies exchange can not be found";

    public ExchangeRateNotFoundException(String message)
    {
        super(message);
    }

    public ExchangeRateNotFoundException()
    { super(DEFAULT_MESSAGE); }
}
