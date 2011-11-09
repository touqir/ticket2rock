/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2006-2011
 *  Holisticon AG
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
package de.ejb3buch.ticket2rock;

import java.io.File;

import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entitylistener.NewEntityListener;
import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;

public class SystestUtils {
	
	public static final JavaArchive getArchiveWithPersistenceUnit(){
	return ShrinkWrap
			.create(JavaArchive.class, "test.jar")
	.addPackages(true, Konzert.class.getPackage())
	.addClass(NewEntityListener.class)
	.addClass(KapazitaetErschoepftException.class)
	.addAsManifestResource(
			new File("src/main/resources/META-INF/persistence.xml"),
			ArchivePaths.create("persistence.xml"))
	.addAsManifestResource(
			new File("src/main/resources/META-INF/orm.xml"),
			ArchivePaths.create("orm.xml"))
	.addAsResource(new File("src/main/resources/import.sql"),
			ArchivePaths.create("import.sql"));
	}

}
