package service;

import domain.Offer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoanServiceTest {

  @ParameterizedTest
  @MethodSource("totalMonthlyRepaymentProvider")
  @DisplayName("calculateMonthlyPayment method should return total repayment amount")
  void calculateCompoundInterestsShouldReturnTotalRepaymentAmount(final double expected, final double principal, final double apy) {
    assertEquals(expected, LoanService.calculateMonthlyPayment(principal, apy, 36));
  }

  private static Stream<Arguments> totalMonthlyRepaymentProvider() {
    return Stream.of(
      Arguments.of(30.778878826815863, 1000, 0.07),
      Arguments.of(70.12022809711509, 2000, 0.17),
      Arguments.of(141.0306505971817, 5000, 0.01),
      Arguments.of(525.9017107283631, 15000, 0.17)
    );
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if APY is negative")
  void calculateMonthlyPaymentShouldThrowErrorIfAPYIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.calculateMonthlyPayment(1000, -1, 1));
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if APY is zero")
  void calculateMonthlyPaymentShouldThrowErrorIfAPYIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.calculateMonthlyPayment(1000, 0, 1));
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if principal is negative")
  void calculateMonthlyPaymentShouldThrowErrorIfPrincipalIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.calculateMonthlyPayment(-1000, 1, 1));
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if principal is zero")
  void calculateMonthlyPaymentShouldThrowErrorIfPrincipalIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.calculateMonthlyPayment(0, 1, 1));
  }

  @ParameterizedTest
  @MethodSource("apyAprProvider")
  @DisplayName("apyToAPR should convert Annual Percentage Yield to Annual Percentage Rate")
  void apyToAPR(final double expected, final double apy) {
    assertEquals(expected, LoanService.apyToAPR(apy, 12));
  }

  private static Stream<Arguments> apyAprProvider() {
    return Stream.of(
      Arguments.of(0.01981897562304269, 0.02),
      Arguments.of(0.06784974464886329, 0.07),
      Arguments.of(0.09568968514684517, 0.1),
      Arguments.of(0.14057900303824056, 0.15)
    );
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if APY is negative")
  void apyToAPRShouldThrowErrorIfAPYIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.apyToAPR(-10, 1));
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if APY is zero")
  void apyToAPRShouldThrowErrorIfAPYIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.apyToAPR(0, 1));
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if periods is negative")
  void apyToAPRShouldThrowErrorIfPeriodsIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.apyToAPR(-10, 1));
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if periods is zero")
  void apyToAPRShouldThrowErrorIfPeriodsIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanService.apyToAPR(0, 1));
  }

  @Test
  @DisplayName("getWeightedLoanRate should return weighted rate")
  void getWeightedLoanRateShouldReturnWeightedRate() {
    final Offer offer1 = new Offer(null, 0.068, 20000);
    final Offer offer2 = new Offer(null, 0.079, 10000);
    final Offer offer3 = new Offer(null, 0.0541, 10000);

    assertEquals(0.067275, LoanService.getWeightedLoanRate(Arrays.asList(offer1, offer2, offer3)));
  }

  @Test
  @DisplayName("getWeightedLoanRate should throw error if any rate is negative")
  void getWeightedLoanRateShouldThrowErrorIfAnyRateIsNegative() {
    final Offer offer1 = new Offer(null, 0.068, 20000);
    final Offer offer2 = new Offer(null, -0.079, 10000);
    final Offer offer3 = new Offer(null, 0.0541, 10000);

    assertThrows(IllegalArgumentException.class, () -> LoanService.getWeightedLoanRate(Arrays.asList(offer1, offer2, offer3)));
  }

  @Test
  @DisplayName("getWeightedLoanRate should throw error if any rate is zero")
  void getWeightedLoanRateShouldThrowErrorIfAnyRateIsZero() {
    final Offer offer1 = new Offer(null, 0.068, 20000);
    final Offer offer2 = new Offer(null, 0, 10000);
    final Offer offer3 = new Offer(null, 0.0541, 10000);

    assertThrows(IllegalArgumentException.class, () -> LoanService.getWeightedLoanRate(Arrays.asList(offer1, offer2, offer3)));
  }
}
