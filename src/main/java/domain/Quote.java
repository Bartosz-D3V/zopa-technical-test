package domain;

import java.text.NumberFormat;
import java.util.Locale;

public final class Quote {
  private final double totalAmount;
  private final double rate;
  private final double monthlyRepayment;
  private final double totalRepayment;

  private Quote(final double totalAmount, final double rate, final double monthlyRepayment, final double totalRepayment) {
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

  @Override
  public String toString() {
    final NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.UK);
    currencyInstance.setGroupingUsed(false);
    final NumberFormat percentageInstance = NumberFormat.getPercentInstance(Locale.UK);
    percentageInstance.setMinimumFractionDigits(1);
    percentageInstance.setMaximumFractionDigits(1);
    return new StringBuilder()
      .append("Requested amount: ")
      .append(currencyInstance.format(getTotalAmount()))
      .append(System.lineSeparator())
      .append("Annual Interest Rate: ")
      .append(percentageInstance.format(getRate()))
      .append(System.lineSeparator())
      .append("Monthly repayment: ")
      .append(currencyInstance.format(getMonthlyRepayment()))
      .append(System.lineSeparator())
      .append("Total repayment: ")
      .append(currencyInstance.format(getTotalRepayment()))
      .toString();
  }

  public static class QuoteBuilder {
    private double totalAmount;
    private double rate;
    private double monthlyRepayment;
    private double totalRepayment;

    public QuoteBuilder setTotalAmount(final double totalAmount) {
      this.totalAmount = totalAmount;
      return this;
    }

    public QuoteBuilder setRate(final double rate) {
      this.rate = rate;
      return this;
    }

    public QuoteBuilder setMonthlyRepayment(final double monthlyRepayment) {
      this.monthlyRepayment = monthlyRepayment;
      return this;
    }

    public QuoteBuilder setTotalRepayment(final double totalRepayment) {
      this.totalRepayment = totalRepayment;
      return this;
    }

    public Quote build() {
      return new Quote(totalAmount, rate, monthlyRepayment, totalRepayment);
    }
  }
}
