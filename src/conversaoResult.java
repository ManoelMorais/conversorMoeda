public class conversaoResult {

    private final double conversionRate;
    private final double convertedAmount;

    public conversaoResult(double conversionRate, double convertedAmount) {
        this.conversionRate = conversionRate;
        this.convertedAmount = convertedAmount;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }
}
