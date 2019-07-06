import domain.Offer;
import domain.Quote;
import service.CSVService;
import service.OfferService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
  public static void main(final String[] args) throws IOException {
    if (args.length < 2) throw new RuntimeException("Please provide location of the CSV file and requested amount");
    final String csvFileLocation = args[0];
    final double amount = Double.valueOf(args[1]);

    final List<Offer> offers = new CSVService().readObjects(new File(csvFileLocation), Offer.class);
    final List<Offer> selectedOffers = OfferService.selectOffers(offers, amount);
    if (selectedOffers == null) {
      System.out.println("We're sorry, we cannot provide you a quote for now.");
    } else {
      final Quote quote = OfferService.getQuote(selectedOffers);
      System.out.println(quote.toString());
    }
  }
}
