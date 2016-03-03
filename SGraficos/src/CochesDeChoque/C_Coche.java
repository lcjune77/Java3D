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

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;
import org.odejava.Body;

public class C_Coche extends Objeto3D{
    private TransformGroup moveTG, rotTG;
    private Transform3D t3d;
    private BoundingSphere bounds;
     Body sphereBody;
     private static final float MASS_FACTOR = 5.0f;
    private RotPosPathInterpolator rotPosPath;
    private Alpha alpha;
     private Point3f[] _positions = new Point3f[9];
    private Quat4f[] _quats = new Quat4f[9];

    public C_Coche(String file,int tipo){
        super(file,tipo);
        this.setCollidable(true);
        bounds = new BoundingSphere(new Point3d(0.0, 0.0,0.0), 2.0);
       bounds.setRadius(2);
        this.setCollisionBounds(bounds);
    }
     public TransformGroup getObjeto(){
        return this.getObjeto(new Vector3d(0,0,0),new Vector3d(0.55,0.55,0.55),Math.toRadians(90),0,0);
    }
    public BoundingSphere Bounds(){
       
        return bounds;
    }
    public BranchGroup movil( Point3f[] positions ,   Quat4f[] quats){
        _positions = positions;
        _quats = quats;
        BranchGroup objRoot = new BranchGroup();
        alpha = new Alpha(-1, 10000);
        TransformGroup target = new TransformGroup();
        Transform3D axisOfRotPos = new Transform3D();
        float[] knots = {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.6f, 0.8f, 0.9f, 1.0f};

        target.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        AxisAngle4f axis = new AxisAngle4f(1.0f,0.0f,0.0f,0.0f);
        axisOfRotPos.set(axis);

       rotPosPath = new RotPosPathInterpolator(  alpha, target,
                                                     axisOfRotPos, knots,
                                                         quats, positions);
     //  System.out.println(alpha.getPhaseDelayDuration());
        rotPosPath.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0),1000));
        rotPosPath.setCollidable(true);

        objRoot.addChild(target);
        objRoot.addChild(rotPosPath);
        target.addChild(getObjeto());

        return objRoot;
    }
    public void redraw(Point3f[] positions ,   Quat4f[] quats){
        rotPosPath.getPositions(_positions);
        _positions = positions;
        rotPosPath.getQuats(_quats);
        _quats = quats;

    }
   
    public void parar(){
        rotPosPath.setEnable(false);
        alpha.pause();
    }
    public void mover(){
        rotPosPath.setEnable(true);
        alpha.resume();
    }

    @Override
    public boolean enable(){

        return rotPosPath.getEnable();

    }
   
   
}
