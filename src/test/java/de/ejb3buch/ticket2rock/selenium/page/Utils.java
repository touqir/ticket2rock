package de.ejb3buch.ticket2rock.selenium.page;

public final class Utils {
	
	private Utils(){
		//hide
	}

	public static int getRandomNumber(int max) {
		return (int) (Math.random() * (max + 1));
	}

}
