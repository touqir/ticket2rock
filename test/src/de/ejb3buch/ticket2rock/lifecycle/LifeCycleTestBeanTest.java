package de.ejb3buch.ticket2rock.lifecycle;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

public class LifeCycleTestBeanTest
{
    private JavaEEGloss gloss;
    
    @Before 
    public void umgebungAufbauen()
    {
        gloss = new JavaEEGloss();
    }
    
    @Test
    public void testNachKonstruktion()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
       
    }

    @Test
    public void testVorPassivierung()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
        gloss.beforePassivate(tb);
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.SCHLAFEND);         
    }

    @Test
    public void testNachAktivierung()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
        gloss.afterActivate(tb);
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.AUFGEWACHT);     
    }

    @Test
    public void testVorZerstoerung()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
        gloss.beforeDestroy(tb);
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.WEG);     }

}
