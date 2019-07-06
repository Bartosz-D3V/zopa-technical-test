package service;

import domain.Offer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OfferServiceTest {
  @Test
  @DisplayName("selectOffers should throw error if requested amount is less than £1000")
  void selectOffersShouldThrowErrorIfAmountIsLessThan1000() {
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 999));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 0));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), -1000));
  }

  @Test
  @DisplayName("selectOffers should throw error if requested amount is greater than £15000")
  void selectOffersShouldThrowErrorIfAmountIsGreaterThan15000() {
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 1500.1));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 15001));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 20000));
  }

  @Test
  @DisplayName("selectOffers should throw error if requested amount is not multiplicity of £100")
  void selectOffersShouldThrowErrorIfAmountIsNotMultiplicityOf100() {
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 101));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 150));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 2057));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 2050));
    assertThrows(IllegalArgumentException.class, () -> OfferService.selectOffers(Collections.emptyList(), 5010));
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
}
