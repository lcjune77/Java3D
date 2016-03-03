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

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public class C_Escena_Coches extends Objeto3D {

    C_EstructuraMovil movil ;
   
    C_EstructuraFija fija;
    
    public C_Escena_Coches(){
     //para usar en el pick
        this.setName("Coches");
        this.setPickable(true);
        this.setCapability(BranchGroup.ENABLE_PICK_REPORTING);
        // inicialización
        movil  = new C_EstructuraMovil();
     //   movil2 = new C_EstructuraMovil(0);
        fija   = new C_EstructuraFija();
        
        // llamada al método de crear la escena
        crearEscena();
        compile();
    }
    /**
     * crea la escena de los coches de choque
     */
    private void crearEscena(){

       
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0, 0.0),100);
           
         TransformGroup tg;

        tg = new TransformGroup();
        
        tg.addChild(movil);
        this.addChild(tg);

        
        C_Colisiones dC = new C_Colisiones(movil);
        dC.setSchedulingBounds(bounds);
        tg = new TransformGroup();
        tg.addChild(dC);
//        tg.addChild(movil2);
//
        this.addChild(tg);

        tg = getTransformGroup(new Vector3d(0,-0.01,0),new Vector3d(1.1,1.1,1.1),0,0,0);
        tg.addChild(fija);
        this.addChild(tg);
        
    }
    public void parar(){
        movil.parar();
       
    }
    public void mover(){
        movil.mover();
       
    }
    @Override
    public boolean enable(){
      
        return  movil.enable();
    }

}
