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

package Noria;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public class N_Escena_Noria extends Objeto3D {
    
    private N_EstructuraFija patas;
    private N_EstructuraMovil rueda;

    public N_Escena_Noria(){
        this.setName("Noria");
        this.setPickable(true);
        this.setCapability(BranchGroup.ENABLE_PICK_REPORTING);

         rueda = new N_EstructuraMovil();
         patas = new N_EstructuraFija();
         crearEscena();
         compile();
    }

   private void crearEscena(){
       
       TransformGroup tg = new TransformGroup();
       TransformGroup aux;

       aux = getTransformGroup(new Vector3d(0,1.8,0),new Vector3d(1,1,1),0,0,0);
       aux.addChild(rueda);
       tg.addChild(aux);

       aux = getTransformGroup(new Vector3d(.5,0,0),new Vector3d(1,1,1),0,0,0);
       aux.addChild(patas);
       tg.addChild(aux);

       this.addChild(tg);
   }
   /**
    * para el movimiento  de la noria
    */
   public void parar(){
       rueda.parar();
   }
   public void mover(){
       rueda.mover();
   }
    @Override
   public boolean enable(){
     return  rueda.enable();
   }

}
