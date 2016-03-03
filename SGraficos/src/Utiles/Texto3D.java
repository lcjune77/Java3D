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
package Utiles;

import java.awt.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Texto3D {
    private static String texto;
    private static Color3f color;
    private static TransformGroup tG ;
    private static double sc;
    public Texto3D(String text, TransformGroup tg,Color3f colr){
        texto =text;
       color=colr;
        tG =tg ;
    
    }

    public static TransformGroup getTexto3D(){

        Font3D f3d = new Font3D(new Font("TestFont", Font.PLAIN, 1),new FontExtrusion());
        Text3D text3d = new Text3D(f3d,texto , new Point3f());

        text3d.setString(texto);
        text3d.setAlignment(0);
        Color3f white = new Color3f(.8f, .8f, .8f);
        //Color3f blue = color;//new Color3f(.2f, 0.2f, 0.6f);
        Appearance a = new Appearance();
        Material m = new Material(color, color,color, white, 80.0f);
        m.setLightingEnable(true);
        a.setMaterial(m);

        Shape3D sh = new Shape3D();
        sh.setGeometry(text3d);
        sh.setAppearance(a);
        TransformGroup tg = tG;

        tg.addChild(sh);
        return tg;
    }

    public TransformGroup getText3D_Traslacion(int i,Transform3D Axis ,float ini,float fin)
    {
        // Transform3D Axis = new Transform3D();
        TransformGroup obTrans = new TransformGroup();
        obTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        obTrans.addChild( getTexto3D());
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);
	Alpha rotationAlpha = new Alpha(-1,
                                            Alpha.INCREASING_ENABLE |
                                            Alpha.DECREASING_ENABLE,
                                            0, 0,
                                            i, 0, 0,
                                            i, 0, 0);
       // Axis.rotZ(-Math.PI/2);//
	Interpolator translator =   new PositionInterpolator(rotationAlpha,
                                                                obTrans,
                                                                Axis,
                                                                ini,fin);
	translator.setSchedulingBounds(bounds);
        obTrans.addChild(translator);

        return obTrans;
    }

    /**
 *
  * @param i    int
 * @param Axis  Transform3D eje de giro
 * @param ini   float posición inicial
 * @param fin   float posicion final
 *
 * @return TransformGroup
     */
    public TransformGroup getText3D_Rotacion(int i,Transform3D Axis,float ini,float fin) {
        TransformGroup objTrans= new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        objTrans.addChild(getTexto3D());
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),1000);


	Alpha transAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE,
					0, 0,
					i, 0, 0,
					0, 0, 0);
	RotationInterpolator rotator = new RotationInterpolator(transAlpha,  objTrans, Axis, ini,fin);
	rotator.setSchedulingBounds(bounds);
	objTrans.addChild(rotator);

        return objTrans;
    }

}
