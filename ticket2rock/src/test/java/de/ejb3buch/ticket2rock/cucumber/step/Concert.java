package de.ejb3buch.ticket2rock.cucumber.step;

public class Concert {
	String ort, location, datum, interpret, tournee, ticketpreis, aktion;

	public Concert(String ort, String location, String datum, String interpret,
			String tournee, String ticketpreis, String aktion) {
		this.ort = ort;
		this.location = location;
		this.datum = datum;
		this.interpret = interpret;
		this.tournee = tournee;
		this.ticketpreis = ticketpreis;
		this.aktion = aktion;
	}

	@Override
	public String toString() {
		return "Concert [ort=" + ort + ", location=" + location + ", datum="
				+ datum + ", interpret=" + interpret + ", tournee=" + tournee
				+ ", ticketpreis=" + ticketpreis + ", aktion=" + aktion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aktion == null) ? 0 : aktion.hashCode());
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result
				+ ((interpret == null) ? 0 : interpret.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result
				+ ((ticketpreis == null) ? 0 : ticketpreis.hashCode());
		result = prime * result + ((tournee == null) ? 0 : tournee.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concert other = (Concert) obj;
		if (aktion == null) {
			if (other.aktion != null)
				return false;
		} else if (!aktion.equals(other.aktion))
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (interpret == null) {
			if (other.interpret != null)
				return false;
		} else if (!interpret.equals(other.interpret))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		} else if (!ort.equals(other.ort))
			return false;
		if (ticketpreis == null) {
			if (other.ticketpreis != null)
				return false;
		} else if (!ticketpreis.equals(other.ticketpreis))
			return false;
		if (tournee == null) {
			if (other.tournee != null)
				return false;
		} else if (!tournee.equals(other.tournee))
			return false;
		return true;
	}

}