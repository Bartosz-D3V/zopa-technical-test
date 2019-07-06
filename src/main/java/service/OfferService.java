package service;

import domain.Offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferService {
  public static List<Offer> selectOffers(final List<Offer> availableOffers, final double amount) {
    OfferService.validateAmount(amount);
    if (availableOffers.stream().map(Offer::getAvailable).reduce(0d, Double::sum) < amount) return null;
    Collections.sort(availableOffers);

    final List<Offer> offers = new ArrayList<>();
    double amountToBorrow = amount;
    for (Offer offer : availableOffers) {
      final double available = offer.getAvailable();
      if (available < amountToBorrow) {
        offers.add(offer);
        amountToBorrow -= available;
      } else if (available > amountToBorrow) {
        offers.add(new Offer(offer.getLender(), offer.getRate(), amountToBorrow));
        offer.setAvailable(available - amountToBorrow);
        break;
      } else break;
    }
    return offers;
  }

  private static void validateAmount(final double amount) {
    if (amount < 1000 || amount > 15000) {
      throw new IllegalArgumentException("Requested amount has to be between £1000 and £15000");
    }
    if (amount % 100 != 0) {
      throw new IllegalArgumentException("Requested amount must be multiplicity of £100");
    }
  }
}
