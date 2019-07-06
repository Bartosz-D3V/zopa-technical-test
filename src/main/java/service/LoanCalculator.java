package service;

public class LoanCalculator {
  public static double calculateMonthlyPayment(final double principal,
                                               final double apy,
                                               final double numOfPayments) {
    LoanCalculator.validatePositive(principal, apy);
    final double monthlyAPR = apyToAPR(apy, 12) / 12;
    return principal * (monthlyAPR + (monthlyAPR / (Math.pow(1 + monthlyAPR, numOfPayments) - 1)));
  }

  private static void validatePositive(final double... params) {
    for (double param : params) {
      if (param <= 0) throw new IllegalArgumentException("Parameter has to be positive");
    }
  }

  public static double apyToAPR(double apy, int periods) {
    LoanCalculator.validatePositive(apy, periods);
    return periods * (Math.pow((1 + apy), (double) 1 / periods) - 1);
  }
}
