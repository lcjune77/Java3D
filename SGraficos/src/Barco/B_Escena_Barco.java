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
package Barco;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public class B_Escena_Barco extends Objeto3D {
    
    B_EstructuraFija soportes;    
    TransformGroup objTrans;
    B_EstructuraMovil casco;

    public B_Escena_Barco(){
      //para usar en el pick
        this.setName("Barca");
        this.setPickable(true);
        this.setCapability(BranchGroup.ENABLE_PICK_REPORTING);

        soportes = new B_EstructuraFija();
        casco = new B_EstructuraMovil();
        
        objTrans = getTransformGroup(  new Vector3d(0,2.7,0),//traslada
                                       null,//no se escala
                                       0,0,0); //rota(x,y,z)
        objTrans.addChild( casco);
        this.addChild(objTrans);
        this.addChild(soportes);

        compile();
    }

   public void mover(){
       casco.mover();
   }
   public void parar(){
       casco.parar();
   }
    @Override
   public boolean enable(){
       return casco.enable();
   }
}
