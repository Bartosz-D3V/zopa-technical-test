package domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Offer {
  private String lender;
  private double rate;
  private BigDecimal available;

  public Offer(final String lender, final double rate, final BigDecimal available) {
    this.lender = lender;
    this.rate = rate;
    this.available = available;
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

  public BigDecimal getAvailable() {
    return available;
  }

  public void setAvailable(final BigDecimal available) {
    this.available = available;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof Offer)) return false;
    final Offer offer = (Offer) o;
    return Double.compare(offer.getRate(), getRate()) == 0 &&
      Objects.equals(getLender(), offer.getLender()) &&
      Objects.equals(getAvailable(), offer.getAvailable());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLender(), getRate(), getAvailable());
  }
}
