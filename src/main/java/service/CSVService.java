package service;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVService {
  public <T> List<T> readObjects(File file, Class<T> clazz) throws IOException {
    return new CsvToBeanBuilder<T>(new BufferedReader(new FileReader(file)))
      .withType(clazz)
      .withIgnoreLeadingWhiteSpace(true)
      .build()
      .parse();
  }
}
