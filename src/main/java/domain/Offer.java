package domain;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public final class Offer implements Comparable<Offer> {
  @CsvBindByName(column = "Lender", required = true)
  private String lender;

  @CsvBindByName(column = "Rate", required = true)
  private double rate;

  @CsvBindByName(column = "Available", required = true)
  private double available;

  public Offer(final String lender, final double rate, final double available) {
    this.lender = lender;
    this.rate = rate;
    this.available = available;
  }

  public Offer() {
  }

  public String getLender() {
    return lender;
  }

  public void setLender(final String lender) {
    this.lender = lender;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(final double rate) {
    this.rate = rate;
  }

  public double getAvailable() {
    return available;
  }

  public void setAvailable(final double available) {
    this.available = available;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Offer)) return false;
    Offer offer = (Offer) o;
    return Double.compare(offer.getRate(), getRate()) == 0 &&
      Double.compare(offer.getAvailable(), getAvailable()) == 0 &&
      Objects.equals(getLender(), offer.getLender());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLender(), getRate(), getAvailable());
  }

  @Override
  public int compareTo(Offer anotherOffer) {
    return Double.compare(getRate(), anotherOffer.getRate());
  }
}
