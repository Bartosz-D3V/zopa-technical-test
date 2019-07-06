package domain;

public class Quote {
  private double totalAmount;
  private double rate;
  private double monthlyRepayment;
  private double totalRepayment;

  private Quote(double totalAmount, double rate, double monthlyRepayment, double totalRepayment) {
    this.totalAmount = totalAmount;
    this.rate = rate;
    this.monthlyRepayment = monthlyRepayment;
    this.totalRepayment = totalRepayment;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public double getRate() {
    return rate;
  }

  public double getMonthlyRepayment() {
    return monthlyRepayment;
  }

  public double getTotalRepayment() {
    return totalRepayment;
  }

  public static class QuoteBuilder {
    private double totalAmount;
    private double rate;
    private double monthlyRepayment;
    private double totalRepayment;

    public QuoteBuilder setTotalAmount(double totalAmount) {
      this.totalAmount = totalAmount;
      return this;
    }

    public QuoteBuilder setRate(double rate) {
      this.rate = rate;
      return this;
    }

    public QuoteBuilder setMonthlyRepayment(double monthlyRepayment) {
      this.monthlyRepayment = monthlyRepayment;
      return this;
    }

    public QuoteBuilder setTotalRepayment(double totalRepayment) {
      this.totalRepayment = totalRepayment;
      return this;
    }

    public Quote build() {
      return new Quote(totalAmount, rate, monthlyRepayment, totalRepayment);
    }
  }
}
