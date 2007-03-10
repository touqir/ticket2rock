package de.ejb3buch.ticket2rock.session.demo;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Stateless;

import de.ejb3buch.ticket2rock.entity.Ticketbestellung;
import de.ejb3buch.ticket2rock.session.demo.DiscountCalculator;

public @Stateless
class DiscountCalculatorBean implements DiscountCalculator
{
    static BigDecimal STD_PRICE = new BigDecimal("50.0");

    public BigDecimal calculateDiscountedPrice(Collection<Ticketbestellung> bestellungen)
    {
        BigDecimal result = new BigDecimal(0);

        for (Ticketbestellung a : bestellungen)
        {
            BigDecimal items = new BigDecimal(a.getAnzahl());
            
            BigDecimal thisOrder = items.multiply(STD_PRICE);
            System.out.println("thisorder " + thisOrder);
            result = result.add( thisOrder);
        }

        return result;
    }
}
