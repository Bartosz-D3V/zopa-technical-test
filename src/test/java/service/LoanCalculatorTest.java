package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoanCalculatorTest {

  private static Stream<Arguments> totalMonthlyRepaymentProvider() {
    return Stream.of(
      Arguments.of(30.778878826815863, 1000, 0.07),
      Arguments.of(70.12022809711509, 2000, 0.17),
      Arguments.of(141.0306505971817, 5000, 0.01),
      Arguments.of(525.9017107283631, 15000, 0.17)
    );
  }

  @ParameterizedTest
  @MethodSource("totalMonthlyRepaymentProvider")
  @DisplayName("calculateMonthlyPayment method should return total repayment amount")
  void calculateCompoundInterestsShouldReturnTotalRepaymentAmount(final double expected, final double principal, final double apy) {
    assertEquals(expected, LoanCalculator.calculateMonthlyPayment(principal, apy, 36));
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if APY is negative")
  void calculateMonthlyPaymentShouldThrowErrorIfAPYIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.calculateMonthlyPayment(1000, -1, 1));
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if APY is zero")
  void calculateMonthlyPaymentShouldThrowErrorIfAPYIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.calculateMonthlyPayment(1000, 0, 1));
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if principal is negative")
  void calculateMonthlyPaymentShouldThrowErrorIfPrincipalIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.calculateMonthlyPayment(-1000, 1, 1));
  }

  @Test
  @DisplayName("calculateMonthlyPayment method should throw IllegalArgumentException if principal is zero")
  void calculateMonthlyPaymentShouldThrowErrorIfPrincipalIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.calculateMonthlyPayment(0, 1, 1));
  }

  private static Stream<Arguments> apyAprProvider() {
    return Stream.of(
      Arguments.of(0.01981897562304269, 0.02),
      Arguments.of(0.06784974464886329, 0.07),
      Arguments.of(0.09568968514684517, 0.1),
      Arguments.of(0.14057900303824056, 0.15)
    );
  }

  @ParameterizedTest
  @MethodSource("apyAprProvider")
  @DisplayName("apyToAPR should convert Annual Percentage Yield to Annual Percentage Rate")
  void apyToAPR(final double expected, final double apy) {
    assertEquals(expected, LoanCalculator.apyToAPR(apy, 12));
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if APY is negative")
  void apyToAPRShouldThrowErrorIfAPYIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.apyToAPR(-10, 1));
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if APY is zero")
  void apyToAPRShouldThrowErrorIfAPYIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.apyToAPR(0, 1));
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if periods is negative")
  void apyToAPRShouldThrowErrorIfPeriodsIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.apyToAPR(-10, 1));
  }

  @Test
  @DisplayName("apyToAPR method should throw IllegalArgumentException if periods is zero")
  void apyToAPRShouldThrowErrorIfPeriodsIsZero() {
    assertThrows(IllegalArgumentException.class, () -> LoanCalculator.apyToAPR(0, 1));
  }
}
