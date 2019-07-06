package service;

import domain.Offer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVServiceTest {
  @Test
  @DisplayName("readOffers should return list of offers from provided path to CSV file")
  void readOffersShouldReturnListOfOffers() throws IOException {
    final ClassLoader classLoader = CSVServiceTest.class.getClassLoader();
    final URL resource = classLoader.getResource("MarketDataForExercise.csv");
    assertNotNull(resource);
    final List<Offer> offers = new CSVService().readObjects(new File(resource.getFile()), Offer.class);

    assertEquals(7, offers.size());

    final Offer offer1 = offers.get(0);
    assertEquals("Bob", offer1.getLender());
    assertEquals(0.075, offer1.getRate());
    assertEquals(640, offer1.getAvailable());

    final Offer offer2 = offers.get(1);
    assertEquals("Jane", offer2.getLender());
    assertEquals(0.069, offer2.getRate());
    assertEquals(480, offer2.getAvailable());

    final Offer offer3 = offers.get(2);
    assertEquals("Fred", offer3.getLender());
    assertEquals(0.071, offer3.getRate());
    assertEquals(520, offer3.getAvailable());

    final Offer offer4 = offers.get(3);
    assertEquals("Mary", offer4.getLender());
    assertEquals(0.104, offer4.getRate());
    assertEquals(170, offer4.getAvailable());

    final Offer offer5 = offers.get(4);
    assertEquals("John", offer5.getLender());
    assertEquals(0.081, offer5.getRate());
    assertEquals(320, offer5.getAvailable());

    final Offer offer6 = offers.get(5);
    assertEquals("Dave", offer6.getLender());
    assertEquals(0.074, offer6.getRate());
    assertEquals(140, offer6.getAvailable());

    final Offer offer7 = offers.get(6);
    assertEquals("Angela", offer7.getLender());
    assertEquals(0.071, offer7.getRate());
    assertEquals(60, offer7.getAvailable());
  }

  @Test
  @DisplayName("readOffers should throw error if file does not exist")
  void readOffersShouldThrowErrorIfFileDoesNotExist() {
    assertThrows(IOException.class, () -> new CSVService().readObjects(new File(""), Offer.class));
  }
}
