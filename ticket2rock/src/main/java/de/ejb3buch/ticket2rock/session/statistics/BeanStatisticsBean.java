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
package de.ejb3buch.ticket2rock.session.statistics;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;
import javax.interceptor.ExcludeDefaultInterceptors;

/**
 * Diese Bean führt mittels statische Felder eine Statistik von Methodenaufrufen,
 * deren Dauer und die Benutzung von Klassen. 
 */


@Singleton
@ExcludeDefaultInterceptors
public class BeanStatisticsBean implements BeanStatisticsLocal,
		BeanStatistics {

	Map<Class, Integer> classUsage = new HashMap<Class, Integer>();

	Map<Method, Integer> methodUsage = new HashMap<Method, Integer>();

	Map<Method, Long> methodDuration = new HashMap<Method, Long>();


	@SuppressWarnings("rawtypes")
	public Map<Class, Integer> getClassUsage() {
		return classUsage;
	}

	public Map<Method, Integer> getMethodUsage() {
		return methodUsage;
	}

	public Map<Method, Long> getMethodTotalDuration() {
		return methodDuration;
	}

	public void reportNewObject(Object object) {
		if (!classUsage.containsKey(object.getClass())) {
			// First object of the given class
			classUsage.put(object.getClass(), 1);
		} else {
			// Other objects of this class already exist
			Integer currentCount = classUsage.get(object
					.getClass());
			currentCount++;
			classUsage
					.put(object.getClass(), currentCount);
		}
	}

	public void reportMethodCall(Method method) {
		if (!methodUsage.containsKey(method)) {
			// First object of the given class
			methodUsage.put(method, 1);
		} else {
			// Other objects of this class already exist
			Integer currentCount = methodUsage.get(method);
			currentCount++;
			methodUsage.put(method, currentCount);
			methodUsage.put(method, currentCount++);
		}
	}

	public void reportMethodDuration(Method method, long duration) {
		if (!methodDuration.containsKey(method)) {
			// First object of the given class
			methodDuration.put(method, duration);
		} else {
			// Other objects of this class already exist
			Long totalDuration = methodDuration
					.get(method);
			totalDuration += duration;
			methodDuration.put(method, totalDuration);
		}
	}
}
