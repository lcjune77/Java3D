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

import static java.lang.Math.PI;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public final class T_EstructuraMovil extends Objeto3D{

    TransformGroup tg;
    T_Caballo_Barra caballo ;
    T_Caballo_Barra caballo2 ;
    T_Caballo_Barra caballo3 ;
    T_Caballo_Barra caballo4 ;
    RotationInterpolator rotator ;
    Alpha transAlpha;
    /**
     * Constructor de la clase la cual inicializa sus atributos
     * y realiza la llamada a crear Estructura     
     */
    public T_EstructuraMovil(){
        tg = new TransformGroup();
        caballo  = new T_Caballo_Barra(0);
        caballo2 = new T_Caballo_Barra(0);
        caballo3 = new T_Caballo_Barra(1);
        caballo4 = new T_Caballo_Barra(1);
        crearEstructuraMovil();
    }
/**
 * crea la estructura Movil con los
 * 4 caballos_barra añadiendo la rotación a esta estructura
 */
    public void crearEstructuraMovil(){
        
        TransformGroup aux;
        double dis = 1.5;
        aux = getTransformGroup(new Vector3d(dis,0,0),null,0,Math.toRadians(60),0);
        aux.addChild(caballo);
        tg.addChild(aux);

        aux = getTransformGroup(new Vector3d(-dis,0,0),null,0,Math.toRadians(-60),0);
        aux.addChild(caballo2);
        tg.addChild(aux);

        aux = getTransformGroup(new Vector3d(0,0,-dis),null,0,Math.toRadians(155),0);
        aux.addChild(caballo3);
        tg.addChild(aux);

        aux = getTransformGroup(new Vector3d(0,0,dis),null,0,Math.toRadians(15),0);
        aux.addChild(caballo4);
        tg.addChild(aux);
        rotacion_Estructura();
    }
/**
 * rota la estructura
 */
    public void rotacion_Estructura(){
        TransformGroup objTrans= new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        objTrans.addChild(tg);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);

        Transform3D t3d = new Transform3D();
	transAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE,
					0, 0,
					4000, 0, 0,
					0, 0, 0);
	rotator = new RotationInterpolator(transAlpha,  objTrans, t3d,  -(float) PI*2,0f);
	rotator.setSchedulingBounds(bounds);
	objTrans.addChild(rotator);

        this.addChild(objTrans);
    }
    /**
     * para el movimiento de la estructura (caballo y rotación)
     */
    public void parar(){
        caballo.parar();
        caballo2.parar();
        caballo3.parar();
        caballo4.parar();
        rotator.setEnable(false);
    }
    /**
     * mueve la estructura (caballo y rotación)
     */
    public void mover(){
        caballo.mover();
        caballo2.mover();
        caballo3.mover();
        caballo4.mover();
        rotator.setEnable(true);
    }
    public boolean enable(){
       return rotator.getEnable();
    }

}
