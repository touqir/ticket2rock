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
