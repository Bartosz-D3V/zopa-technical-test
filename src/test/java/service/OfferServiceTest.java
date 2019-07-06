package service;

import domain.Offer;
import domain.Quote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OfferServiceTest {
  @ParameterizedTest
  @MethodSource("negativeAmountProvider")
  @DisplayName("selectOffers should throw error if requested amount is less than £1000")
  void selectOffersShouldThrowErrorIfAmountIsLessThan1000(final double amount) {
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), amount));
  }

  private static Stream<Arguments> negativeAmountProvider() {
    return Stream.of(
      Arguments.of(999),
      Arguments.of(0),
      Arguments.of(-1000)
    );
  }

  @ParameterizedTest
  @MethodSource("tooBigAmountProvider")
  @DisplayName("selectOffers should throw error if requested amount is greater than £15000")
  void selectOffersShouldThrowErrorIfAmountIsGreaterThan15000(final double amount) {
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), amount));
  }

  private static Stream<Arguments> tooBigAmountProvider() {
    return Stream.of(
      Arguments.of(1500.1),
      Arguments.of(15001),
      Arguments.of(20000)
    );
  }

  @ParameterizedTest
  @MethodSource("incorrectMultiplicationAmountProvider")
  @DisplayName("selectOffers should throw error if requested amount is not multiplicity of £100")
  void selectOffersShouldThrowErrorIfAmountIsNotMultiplicityOf100(final double amount) {
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), amount));
  }

  private static Stream<Arguments> incorrectMultiplicationAmountProvider() {
    return Stream.of(
      Arguments.of(101),
      Arguments.of(150),
      Arguments.of(2057),
      Arguments.of(2050),
      Arguments.of(5010)
    );
  }

  @Test
  @DisplayName("selectOffers should return null if there is no available quote")
  void selectOffersShouldReturnNullIfNoAvailableOffer() {
    final Offer offer1 = new Offer(null, 0.07, 100);
    final Offer offer2 = new Offer(null, 0.17, 1000);
    List<Offer> offers = Arrays.asList(offer1, offer2);

    assertNull(OfferService.selectOffers(offers, 3000));
  }

  @Test
  @DisplayName("selectOffers result should return Offers fulfilling the requested amount")
  void selectOffersResultShouldReturnOffersFulfillingTheAmount() {
    final Offer offer1 = new Offer("Bob", 0.07, 100);
    final Offer offer2 = new Offer("John", 0.17, 346);
    final Offer offer3 = new Offer("Mark", 0.01, 212);
    final Offer offer4 = new Offer("Paul", 0.05, 765);
    final Offer offer5 = new Offer("Joe", 0.05, 654);
    List<Offer> offers = Arrays.asList(offer1, offer2, offer3, offer4, offer5);

    List<Offer> actualOffers = OfferService.selectOffers(offers, 1000);

    assertNotNull(actualOffers);
    assertEquals(3, actualOffers.size());
    assertEquals(offer3, actualOffers.get(0));
    assertEquals(offer4, actualOffers.get(1));
    assertEquals("Joe", actualOffers.get(2).getLender());
    assertEquals(23, actualOffers.get(2).getAvailable());
    assertEquals(0.05, actualOffers.get(2).getRate());
    assertEquals(631, offer5.getAvailable());
  }

  @Test
  @DisplayName("getQuote should return quote from offers")
  void getQuoteShouldReturnQuoteFromOffers() {
    final Offer offer1 = new Offer("Jane", 0.069, 480);
    final Offer offer2 = new Offer("Fred", 0.071, 520);

    final Quote quote = OfferService.getQuote(Arrays.asList(offer1, offer2));

    assertNotNull(quote);
    assertEquals(1000, quote.getTotalAmount());
    assertEquals(0.07003999999999999, quote.getRate());
    assertEquals(30.78059417320818, quote.getMonthlyRepayment());
    assertEquals(1108.1013902354944, quote.getTotalRepayment());
  }
}
