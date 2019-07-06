package service;

import com.opencsv.bean.CsvToBeanBuilder;
import domain.Offer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVService {
  public static List<Offer> readOffers(File file) throws IOException {
    return new CsvToBeanBuilder(new BufferedReader(new FileReader(file)))
      .withType(Offer.class)
      .withIgnoreLeadingWhiteSpace(true)
      .build()
      .parse();
  }
}
