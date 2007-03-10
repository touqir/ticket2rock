package de.ejb3buch.ticket2rock.session.demo;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Remote;

import de.ejb3buch.ticket2rock.entity.Ticketbestellung;

@Remote
public interface DiscountCalculator {
    public BigDecimal calculateDiscountedPrice(Collection <Ticketbestellung> bestellungen);
}
