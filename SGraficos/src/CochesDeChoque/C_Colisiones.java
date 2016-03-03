/*******************************************************************************
 *
 * Asignatura:  Sistemas Gráficos -Prácticas
 * Tema:        Parque de atracciones (Noria, Tiovivo, Coches de choque,La Barca)
 * Curso :      2013 - 2014
 * Universidad: UGR
 * @author      Alicia Lara
 * @author      Marlene Vásquez
 * @version     1.0
 *
 ******************************************************************************/

package CochesDeChoque;

import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOnElapsedTime;


public class C_Colisiones extends Behavior  {

    private boolean inCollision = false;
     private WakeupCondition timeOut;
    private C_EstructuraMovil group;
    private C_EstructuraMovil group2;
    private WakeupOnCollisionEntry wEnter;
    private WakeupOnCollisionExit wExit;

    public C_Colisiones(C_EstructuraMovil bg) {
        group = bg;
        inCollision = false;
    }

    @Override
    public void initialize() {
 
        timeOut = new WakeupOnElapsedTime(60);
        wakeupOn(timeOut);
    }

    @Override
    public void processStimulus(Enumeration criteria)
{
    double rad = 0;
int l =group._bounds.length;
        inCollision = !inCollision;
        if (inCollision) {
          
                     group.redraw();
            
           
  //wakeupOn( timeOut );
            System.out.println("user data de Objeto  colision: "+ group.getName());
           
        }
       
    }

}