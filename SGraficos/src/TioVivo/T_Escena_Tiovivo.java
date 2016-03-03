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

import static Utiles.Luces.getLuzPuntual;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public final class T_Escena_Tiovivo extends Objeto3D{
    T_EstructuraFija fija;
    T_EstructuraMovil movil;
    TransformGroup objTrans;
    
    public T_Escena_Tiovivo(){
        
        this.setName("Tiovivo");
        this.setPickable(true);
        this.setCapability(BranchGroup.ENABLE_PICK_REPORTING);

        fija = new T_EstructuraFija();
        movil = new T_EstructuraMovil();
        crearEscena();

        compile();
    }

    public void crearEscena(){
        
        setEstructuraFija();
        setEstructuraMovil();      
        setLuces();
        compile();
    }
       
    private void setEstructuraFija(){

        objTrans= getTransformGroup(new Vector3d(0,0,0),//traslada
                                    null,               //escala
                                    0,0,0);             // rota
        objTrans.addChild(fija);
        this.addChild(objTrans);
    }
     private void setEstructuraMovil(){

        objTrans= getTransformGroup(new Vector3d(0, 1.8,0),new Vector3d(1.3,1.3,1.3),0,0,0);
        objTrans.addChild(movil);
         this.addChild(objTrans);

    }
    private void setLuces(){

        objTrans = getTransformGroup(new Vector3d(0, 1.8,0),new Vector3d(1.3,1.3,1.3),0,0,0);
        objTrans.addChild(getLuzPuntual(2,new Color3f(.6f,.6f,.6f),new Point3f(0,2f,0),new Point3f(0,0.1f,0)));
        this.addChild(objTrans);

    }
    /**
    * para el movimiento  de la noria
    */
   public void parar(){
       movil.parar();
   }
   /**
    * mueve
    */
   public void mover(){
       movil.mover();
   }
   public boolean enable(){
     return  movil.enable();
   }
}
