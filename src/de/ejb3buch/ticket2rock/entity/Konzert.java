/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.ejb3buch.ticket2rock.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;

/**
 * Ein Konzert ist eine Veranstaltung, bei dem ein Interpreten einige seiner Songs vorträgt. Ein Konzert findet zu einem
 * Zeitpunkt an einem Veranstaltungsort statt. Ein Konzert besitzt ein Ticketkontingent.
 */

@SqlResultSetMapping(
        name="KonzertMapping",
        entities={@EntityResult(
          entityClass=Konzert.class,
          fields={
            @FieldResult(name="id", column="id"),
            @FieldResult(name="datum", column="datum"),
                  @FieldResult(name="ticketkontingent",
                               column="kontingent"),
                  @FieldResult(name="ticketpreis",
                               column="ticketpreis"),
                  @FieldResult(name="interpret",
                               column="interpret_id"),
                  @FieldResult(name="ort",
                               column="veranstaltungsort_id"),
                  @FieldResult(name="tournee",
                               column="tournee_id"),
                  @FieldResult(
                     name="veranstalter.handelsregisternummer",
                     column="veranstalter_hr_nr"),
                  @FieldResult(
                     name="veranstalter.amtsgericht",
                     column="veranstalter_amtsgericht")
          }
        )},
        columns={@ColumnResult(name="i_name")}
      )
@NamedNativeQueries({
  @NamedNativeQuery(name="getKonzerteNative",
     query="SELECT id, interpret_id, datum, " +
     "veranstalter_hr_nr, veranstalter_amtsgericht, " +
     "veranstaltungsort_id, tournee_id, " +
     "kontingent, ticketpreis FROM konzert",
     resultClass=Konzert.class)
})
@NamedQueries( { @NamedQuery(name = "getKonzertAnzahl", query = "SELECT COUNT(k) FROM Konzert k"),
        @NamedQuery(name = "getKonzerte", query = "FROM Konzert k") })
@Entity
public class Konzert {

    private int id;

    private Interpret interpret;

    private Date datum;

    private Veranstaltungsort ort;

    private Veranstalter veranstalter;

    private Tournee tournee;

    private int ticketkontingent;

    private float ticketpreis;

    @Id
    @TableGenerator(name = "KonzertPKGen", table = "PK_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "KONZERT_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "KonzertPKGen")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Interpret getInterpret() {
        // Bei Konzerten im Rahmen einer Tournee wird der Interpret bei der
        // Tournee gespeichert.
        if (interpret == null && tournee != null) {
            return tournee.getInterpret();
        } else {
            return interpret;
        }
    }

    public void setInterpret(Interpret interpret) {
        this.interpret = interpret;
    }

    @Temporal(TemporalType.DATE)
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @ManyToOne
    // Die JoinColumn muss benannt werden, da das Attribut nur "ort" heißt
    @JoinColumn(name = "VERANSTALTUNGSORT_ID")
    public Veranstaltungsort getOrt() {
        return ort;
    }

    public void setOrt(Veranstaltungsort ort) {
        this.ort = ort;
        if (ort != null) {
            // Das Ticketkontingent wird mit der Kapazität des
            // Veranstaltungsortes initialisiert.
            this.ticketkontingent = ort.getKapazitaet();
        }
    }

    @ManyToOne
    public Veranstalter getVeranstalter() {
        return veranstalter;
    }

    public void setVeranstalter(Veranstalter veranstalter) {
        this.veranstalter = veranstalter;
    }

    @ManyToOne
    public Tournee getTournee() {
        return tournee;
    }

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    @Column(name = "KONTINGENT")
    public int getTicketkontingent() {
        return ticketkontingent;
    }

    // Das Ticketkontingent kann nicht direkt gesetzt werden;
    // es wird der EInfachheit halber über die Kapazität des
    // Veranstaltungsortes bestimmt.
    protected void setTicketkontingent(int ticketkontingent) {
        this.ticketkontingent = ticketkontingent;
    }

    /**
     * Bestellt eine Anzahl von Tickets für dieses Konzert. Prüft die Verfügbarkeit und reduziert das Ticketkontingent
     * entsprechend.
     * 
     * @param anzahl die Anzahl der bestellten Tickets
     * @return das verbleibende Ticketkontingent
     * @throws KapazitaetErschoepftException wenn das Ticketkontingent nicht ausreicht, um die Bestellung auszuführen
     */
    public synchronized int bestelleTickets(int anzahl) throws KapazitaetErschoepftException {
        if (anzahl <= this.ticketkontingent) {
            this.ticketkontingent -= anzahl;
        } else {
            throw new KapazitaetErschoepftException(this, anzahl);
        }
        return this.ticketkontingent;
    }

    /**
     * Storniert eine Anzahl von Tickets für dieses Konzert und erhöht das Ticketkontingent entsprechend.
     * 
     * @param anzahl die Anzahl der stornierten Tickets
     */
    public synchronized void storniereTickets(int anzahl) {
        this.ticketkontingent += anzahl;
        if (this.ort != null && this.ticketkontingent > this.ort.getKapazitaet()) {
            // Das maximale Kontingent ist begrenzt durch die
            // Kapazität des Veranstaltungsorts.
            this.ticketkontingent = this.ort.getKapazitaet();
        }
    }

    public float getTicketpreis() {
        return ticketpreis;
    }

    public void setTicketpreis(float ticketpreis) {
        this.ticketpreis = ticketpreis;
    }

}
