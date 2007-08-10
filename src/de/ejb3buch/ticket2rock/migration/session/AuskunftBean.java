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

package de.ejb3buch.ticket2rock.migration.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

public class AuskunftBean implements SessionBean {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(AuskunftBean.class);

    private SessionContext ctx = null;

    public void setSessionContext(SessionContext context) {
        ctx = context;
    }

    public void ejbCreate() {
        logger.info("ejbCreate() aufgerufen");
    }

    public void ejbRemove() {
        logger.info("ejbRemove() aufgerufen");
    }

    public void ejbActivate() {
        logger.info("ejbActivate() aufgerufen");
    }

    public void ejbPassivate() {
        logger.info("ejbPassivate() aufgerufen");
    }

    public List sucheKonzerte(String ortsName, Date vonDatum, Date bisDatum) {
        logger.info("I did it");
        logger.debug("SessionContext: " + ctx);
        return new ArrayList();
    }
}
