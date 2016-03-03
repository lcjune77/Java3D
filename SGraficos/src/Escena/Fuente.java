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
package Escena;

import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public final class Fuente extends Objeto3D {

    private BranchGroup bg ;
    
    public Fuente(String file, int a){
        super(file,a);
        bg= new BranchGroup();       
        bg.addChild(movilAgua());
        bg.addChild(getObjeto(new Vector3d(0,0,0),new Vector3d(1,1,1),0,0,0));

    }

    public BranchGroup getObjeto(){
         return bg;
    }
    
    public BranchGroup movilAgua() {

        BranchGroup contentRoot = new BranchGroup();

        TransformGroup objSpin = new TransformGroup();
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        contentRoot.addChild(objSpin);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);

        Agua agua = new Agua();
        objSpin.addChild(agua);

        Behavior waterBehavior = agua.getWaterBehavior();
        waterBehavior.setSchedulingBounds(bounds);
        contentRoot.addChild(waterBehavior);

        Alpha rotationAlpha = new Alpha(-1, 16000);
        RotationInterpolator rotator =  new RotationInterpolator(rotationAlpha, objSpin);

        rotator.setSchedulingBounds(bounds);
        objSpin.addChild(rotator);

        contentRoot.compile();
        return contentRoot;
    }
}
