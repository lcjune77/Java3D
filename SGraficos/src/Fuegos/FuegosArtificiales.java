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
package Fuegos;

import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Switch;
import javax.media.j3d.SwitchValueInterpolator;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;


public class FuegosArtificiales extends Objeto3D{    
    
    public FuegosArtificiales(){        
        
        //ALTERNAR 
        Switch cambiador1 = new Switch();
        cambiador1.setCapability(Switch.ALLOW_SWITCH_WRITE);
        cambiador1.setCapability(Switch.ALLOW_SWITCH_READ);
        Switch cambiador2 = new Switch();
        cambiador2.setCapability(Switch.ALLOW_SWITCH_WRITE);
        cambiador2.setCapability(Switch.ALLOW_SWITCH_READ);
        Switch cambiador3 = new Switch();
        cambiador3.setCapability(Switch.ALLOW_SWITCH_WRITE);
        cambiador3.setCapability(Switch.ALLOW_SWITCH_READ);

        //  AMARILLOS 
        BranchGroup pg1 = crearFuego(5f, 5f, 0.0f,  -6.0f, 8.0f, 0.0f);
        //  VERDES 
        BranchGroup pg2 = crearFuego(0.0f, 5f, 0.0f,  0.0f, 10.0f, 3.0f);
        
        //ALTERNAR
        cambiador1.addChild(pg2);
        cambiador1.addChild(pg1);
        
        //MORADOS 
        BranchGroup pg3 = crearFuego(5f, 0.0f, 5f,   6.0f, 7.0f, -2.0f);
       // ROJOS 
        BranchGroup pg4 = crearFuego(5f, 0.0f, 0.0f,  9.0f, 10.0f, 11.0f);
        
        //ALTERNAR
        cambiador2.addChild(pg3);
        cambiador2.addChild(pg4);

        //TURQUESAS 
        BranchGroup pg5 = crearFuego(0.0f, 5f, 5f,  -3.0f, 10.0f, -1.0f);
       //MORADO
        BranchGroup pg6 = crearFuego(5f, 0.0f, 5f,   3.0f, 11.0f, -4.0f);
        
        //ALTERNAR
        cambiador3.addChild(pg5);
        cambiador3.addChild(pg6);
       
        
        SwitchValueInterpolator f1= cambiadorFuegos(cambiador1, 3000);
        SwitchValueInterpolator f2= cambiadorFuegos(cambiador2, 2500);
        SwitchValueInterpolator f3= cambiadorFuegos(cambiador3, 3500);
       
        this.addChild(cambiador1);
        this.addChild(f1);
        this.addChild(cambiador2);
        this.addChild(f2);
        this.addChild(cambiador3);
        this.addChild(f3);
    }
    
    /*
     * Función que crea y posiciona un sistema de partículas simulando fuegos artificiales
     * el color de las partículas: r, g, b
     * Y la posición donde se situa: posX, posY, posZ
     * @return  BranchGroup 
     */
    private BranchGroup crearFuego(float r, float g, float b, float posX, float posY, float posZ){

        BoundingSphere bounds1 = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        BranchGroup pg = new BranchGroup();
       //crea particulas nº 1800
        Particles particulas = new Particles(1800, r, g, b, 3.14f, 3.14f);
        TransformGroup tg = getTransformGroup(new Vector3d(posX,posY,posZ),new Vector3d(1,1,1),0,0,0);
        tg.addChild(particulas);
        pg.addChild(tg );
        Behavior partBeh = particulas.getParticleBeh();
        partBeh.setSchedulingBounds( bounds1 );
        pg.addChild(partBeh);
        
        return pg;
    }
    
    /*
     * Función que devuelve "switchInterpolator" asociado a un switch
     * Cambiará entre uno y otro 
     * @return  SwitchValueInterpolator 
     */
    private SwitchValueInterpolator cambiadorFuegos(Switch cambiar, int tiempo){      
        Alpha alpha = new Alpha(-1,tiempo);

        SwitchValueInterpolator switchInt = new SwitchValueInterpolator (alpha, cambiar);
        switchInt.setSchedulingBounds(new BoundingSphere());
        switchInt.setLastChildIndex(1);
     
        return switchInt;
    }
}
