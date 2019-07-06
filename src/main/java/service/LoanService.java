package service;

import domain.Offer;

import java.util.Collection;

class LoanService {
  static double calculateMonthlyPayment(final double principal,
                                        final double apy,
                                        final double numOfPayments) {
    LoanService.validatePositive(principal, apy);
    final double monthlyAPR = apyToAPR(apy, 12) / 12;
    return principal * (monthlyAPR + (monthlyAPR / (Math.pow(1 + monthlyAPR, numOfPayments) - 1)));
  }

  static double apyToAPR(final double apy, final int periods) {
    LoanService.validatePositive(apy, periods);
    return periods * (Math.pow((1 + apy), (double) 1 / periods) - 1);
  }

  static double getWeightedLoanRate(final Collection<Offer> offers) {
    LoanService.validatePositive(offers.stream().mapToDouble(Offer::getRate).toArray());
    final double totalInterests = offers.stream().mapToDouble(value -> value.getAvailable() * value.getRate()).sum();
    final double loanAmount = offers.stream().map(Offer::getAvailable).reduce(0d, Double::sum);
    return totalInterests / loanAmount;
  }

  private static void validatePositive(final double... params) {
    for (final double param : params) {
      if (param <= 0) throw new IllegalArgumentException("Parameter has to be positive");
    }
  }
}
