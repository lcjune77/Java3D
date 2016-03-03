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

import Figuras.Figuras;
import static java.lang.Math.PI;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class N_Asiento extends BranchGroup {
    
    private Figuras fig;
    private RotationInterpolator rotator ;
    private Alpha Alpha;
    private Alpha alpha;
    
    public N_Asiento(float r,float v , float a,String file,String file2,RotationInterpolator rot){
        rotator=rot;
        fig = new Figuras();
        getAsientoMovil(r,v,a,file,file2);
    }
    private  TransformGroup getAsiento(float r,float v ,float a,String file,String file2){

        TransformGroup t= new TransformGroup();
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        
        TransformGroup taux ;

        //techo
        taux= fig.getCone(1,1,new Vector3d(0,-.25,0),new Vector3d(0.3, 0.25, 0.3),0,0,0,r,v,a,file);
         t.addChild(taux);
        
        //barra
        taux= fig.getCylinder(1,1,new Vector3d(0,-.4,0),new Vector3d(0.1, 0.3, 0.1),0,0,0,r,v,a,file);
         t.addChild(taux);

         //bajo
        taux= fig.getCylinder(1,1,new Vector3d(0,-.6,0),new Vector3d(0.3, 0.25, 0.3),0,0,0,r,v,a,file2);
         t.addChild(taux);
      return t;

    }
    private void getAsientoMovil(float r,float v ,float a,String file,String file2){
        TransformGroup t = getAsiento(r,v,a,file,file2);
         BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),1000);

        Transform3D Axis = new Transform3D(new Matrix4d(  0 , -1 , 0 , 0 ,
                                                         -1 , 0 , 0 , 0 ,
                                                          0 , 0 , -1 , 0 ,
                                                          0 , 0 , 0 , -1 ));
	alpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE,
					0, 0,
					4000, 0, 0,
					0, 0, 0);

	rotator = new RotationInterpolator(alpha, t, Axis, 0, (float) PI*2);
	rotator.setSchedulingBounds(bounds);
	 t.addChild(rotator);
         this.addChild(t);

    }
    public void parar(){
        rotator.setEnable(false);
        alpha.pause();
    }
    public void mover(){
        rotator.setEnable(true);
        alpha.resume();
    }
    public boolean enable(){
        return rotator.getEnable();
    }


}
