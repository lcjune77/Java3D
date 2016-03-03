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
 
package TioVivo;

import Utiles.PATH;
import static java.lang.Math.toRadians;
import javax.media.j3d.*;
import javax.vecmath.Vector3d;


public final class T_Caballo_Barra extends BranchGroup{
    T_Caballo caballo ;
    T_Barra barra;
    TransformGroup objTrans;
    public T_Caballo_Barra(int a)
    {
       if(a==0)   caballo  = new T_Caballo(PATH.OBJ + "caballito.obj",0);
       else       caballo  = new T_Caballo(PATH.OBJ + "caballito2.obj",0);
       
        barra    = new T_Barra(PATH.OBJ + "barra_carrusel.obj",0);
        objTrans = new TransformGroup();
        getCaballoBarra( a);
    }

    public void getCaballoBarra(int a)
    {        
        objTrans.addChild(barra.getObjeto(new Vector3d(0,0,0.23), new Vector3d(1.3,1.2,1.3), toRadians(-90), 0, 0) );

        Transform3D Axis = new Transform3D();
        Axis.rotZ(-Math.PI/2);
         if(a==0)
            objTrans.addChild(caballo.getObjetoMovil_Traslacion(new Vector3d(-0.33,0.2,0),null, toRadians(-90),0,0, 1000, Axis, .1f, .8f));
         else
            objTrans.addChild(caballo.getObjetoMovil_Traslacion(new Vector3d(-0.33,0.2,0),null, toRadians(-90),0,0, 1000, Axis, .8f, .1f));

       this.addChild(objTrans);
    }
    public void parar(){
        caballo.pararTraslacion();        
    }
     public void mover(){
        caballo.mueveTraslacion();
    }

  
}
