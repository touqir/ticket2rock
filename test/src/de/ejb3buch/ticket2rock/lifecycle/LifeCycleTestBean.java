package de.ejb3buch.ticket2rock.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;

public @Stateless
class LifeCycleTestBean implements LifeCycleTest
{
    public enum Zustand {NICHT_DA, FERTIG, SCHLAFEND, AUFGEWACHT, WEG };
    
    Zustand meinZustand = Zustand.NICHT_DA;

    @PostConstruct
    public void nachKonstruktion()
    {
        meinZustand = Zustand.FERTIG;
    }

    
    @PrePassivate
    public void vorPassivierung()
    {
        meinZustand = Zustand.SCHLAFEND;
        
    }
    
    @PostActivate
    public void nachAktivierung()
    {
        meinZustand = Zustand.AUFGEWACHT;
        
    }
    
    @PreDestroy
    public void vorZerstoerung()
    {
        meinZustand = Zustand.WEG;
    }

    public void hallo()
    {
        
    }
    
    public Zustand getZustand()
    {
        return meinZustand;
    }
}
