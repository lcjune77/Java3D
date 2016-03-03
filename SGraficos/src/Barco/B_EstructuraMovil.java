/*******************************************************************
 *
 * Asignatura:  Sistemas Gráficos -Prácticas
 * Tema:        Parque de atracciones (Noria, Tiovivo, Coches de choque,La Barca)
 * Curso :      2013 - 2014
 * Universidad: UGR
 * @author      Marlene Vásquez - Alicia Lara
 * @version     1.0
 *
 ********************************************************************/

package Barco;

import Figuras.Figuras;
import Utiles.PATH;
import static java.lang.Math.PI;
import javax.media.j3d.*;
import javax.vecmath.*;
import objetos3D.Objeto3D;

public class B_EstructuraMovil extends Objeto3D{

    private B_Casco cascoBarco;
    private TransformGroup objTrans ;

    private String tex_1;
    private String tex_2;
    private String tex_3;
    private Figuras fig;

    private RotationInterpolator rotator ;
    private Alpha transAlpha ;
    
    public B_EstructuraMovil(){
        
        fig = new Figuras();

        tex_1 = PATH.TEXTURA+ "textura1.jpg";
        tex_2 = PATH.TEXTURA+ "textura3.jpg";
        tex_3 = PATH.TEXTURA+ "textura4.jpg";
        
        String cas = PATH.OBJ + "casco.obj";
        cascoBarco = new B_Casco(cas,0);

        objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        objTrans.addChild(getAsientos());
        objTrans.addChild(getSoporte1());
        objTrans.addChild(getSoporte2());
        objTrans.addChild(getAsientos());
        objTrans.addChild(cascoBarco.getObjeto(new Vector3d(0.7,-3.3,0), new Vector3d(1.4,1,1.8),0, 0, 0));
        objTrans.addChild(fig.getCylinder(1,1,new Vector3d(0.7,0,0),new Vector3d(1.5,0.4,0.4),0,0, PI/2, 0.7f, 0.7f, 0.7f,tex_1));
        getTraslacion();

        this.addChild(objTrans);
        compile();
    }

    private TransformGroup  getSoporte1()    {
        return fig.getCubo(0.1f,1.8f,0.1f,
                        new Vector3d(1, -1.8, -0.7),
                        null,
                        PI/8, 0, 0,
                        0.7f, 0.7f, 0.7f,
                        tex_1);
    }

     private TransformGroup  getSoporte2()     {
       return fig.getCubo(0.1f,1.8f,0.1f,
               new Vector3d(1, -1.8, 0.7),
               null, -PI/8, 0, 0, 0.7f, 0.7f, 0.7f,tex_1);
     }

     private void getTraslacion(){
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);
        Transform3D y_Axis = new Transform3D(new Matrix4d(    0 , -1 , 0 ,  0 ,
                                                             -1 ,  0 , 0 ,  0 ,
                                                              0 ,  0 , -1 , 0 ,
                                                              0 ,  0 , 0 , -1 ));
	transAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE,
					0, 0,
					4000, 0, 0,
					4000, 0, 0);
	rotator = new RotationInterpolator(transAlpha, objTrans, y_Axis, (float)PI/3, -(float) PI/3);
	rotator.setSchedulingBounds(bounds);
	 objTrans.addChild(rotator);
     }

     private TransformGroup getAsientos(){

        TransformGroup bg_ = new TransformGroup();
        double  y = -2.3;

        TransformGroup aux ;
        //asiento 1
        aux = getTransformGroup(new Vector3d(0.8,y,0),new Vector3d(1.3,1.3,1.3),0,PI/2,0);
        aux.addChild(new B_Asiento(0.7f, 0.7f, 0.7f,tex_2,tex_3));
        bg_.addChild(aux);
        //asiento 2
        aux = getTransformGroup(new Vector3d(0.8,y,0.6),new Vector3d(1.3,1.3,1.3),0,PI/2,0);
        aux.addChild(new B_Asiento(0.7f, 0.7f, 0.7f,tex_2,tex_3));
        bg_.addChild(aux);
        //asiento 3
        aux = getTransformGroup(new Vector3d(0.8,y,-0.6),new Vector3d(1.3,1.3,1.3),0,PI/2,0);
        aux.addChild(new B_Asiento(0.7f, 0.7f, 0.7f,tex_2,tex_3));
        bg_.addChild(aux);
        //asiento 3
        aux = getTransformGroup(new Vector3d(0.8,y,1.7),new Vector3d(1.3,1.3,1.3),0,PI/2,0);
        aux.addChild(new B_Asiento(0.7f, 0.7f, 0.7f,tex_2,tex_3));
        bg_.addChild(aux);
        //asiento 4
        aux = getTransformGroup(new Vector3d(0.8,y,-1.7),new Vector3d(1.3,1.3,1.3),0,PI/2,0);
        aux.addChild(new B_Asiento(0.7f, 0.7f, 0.7f,tex_2,tex_3));
        bg_.addChild(aux);

        return bg_;
     }
     public void parar(){
         rotator.setEnable(false);
         transAlpha.pause();
     }
     public void mover(){
         rotator.setEnable(true);
         transAlpha.resume();
     }
    @Override
     public boolean enable(){
         return rotator.getEnable();
     }
}
