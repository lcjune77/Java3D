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

import Utiles.Apariencia;
import Figuras.Figuras;
import Utiles.PATH;
import static java.lang.Math.PI;
import static java.lang.Math.toRadians;
import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public final class N_EstructuraMovil extends Objeto3D {
    N_Torus wheel ;
    
    N_Asiento asiento;
    N_Asiento asiento2;
    N_Asiento asiento3;
    N_Asiento asiento4;
    
    Figuras fig;
    String file;
    String file2 ;
    Alpha transAlpha;
    RotationInterpolator rotator;

    public N_EstructuraMovil(){
        wheel = new N_Torus();       
        fig=new Figuras();      
       
        getEstructuraMovil();
    }
    private void getEstructuraMovil(){

         TransformGroup objTrans= new TransformGroup();
         objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      //añade el movimiento a la noria
        objTrans.addChild(getNoria());
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),1000);
        Transform3D y_Axis = new Transform3D(new Matrix4d(  0 , 1 , 0 , 0 ,
                                                              1 , 0 , 0 , 0 ,
                                                              0 , 0 , 1 , 0 ,
                                                              0 , 0 , 0 , 1 ));

	transAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE,
					0, 0,
					4000, 0, 0,
					0, 0, 0);
	rotator = new RotationInterpolator(transAlpha,  objTrans, y_Axis, 0f, -(float) PI*2);
	rotator.setSchedulingBounds(bounds);
	objTrans.addChild(rotator);
        this.addChild(objTrans);
    }
    
    private TransformGroup  getNoria(){

        TransformGroup tg = new TransformGroup();
        TransformGroup aux ;
//rueda 1
        tg.addChild(getRueda());
        
//rueda 2
        aux = getTransformGroup(new Vector3d(1,0,0),new Vector3d(1,1,1),0,0,0);   
        aux.addChild(getRueda());
        tg.addChild(aux);

//asientos
        tg.addChild(setAsientos());
        tg.addChild(getSoportes());
        return tg;
    }
    /**
     * crea la rueda de la noria con los radios
     * @return TransformGroup
     */
    private TransformGroup getRueda(){
        
        TransformGroup tg = new TransformGroup();
        TransformGroup aux = getTransformGroup(new Vector3d(0,0,0),new Vector3d(1,1,1),0,0,0);
        tg.addChild(aux);
        Apariencia app = new Apariencia();
     //torus
        aux.addChild(wheel.Torus(2.0f, 0.1f, 0.6f,app.getApariencia(.5f,.5f,.2f, null)));
    //RADIOS
        tg.addChild(fig.getCylinder(.07f,4f,new Vector3d(0,0,0), null, 0,0,0,             0.8f,0.3f,0.3f, null));
        tg.addChild(fig.getCylinder(.07f,4f,new Vector3d(0,0,0), null, toRadians(90),0,0, 0.3f,0.8f,0.3f, null));

        tg.addChild(fig.getCylinder(.07f,4f,new Vector3d(0,0,0), null, toRadians(45),0,0,  0.3f,0.3f,0.8f, null));
        tg.addChild(fig.getCylinder(.07f,4f,new Vector3d(0,0,0), null, toRadians(135),0,0, 0.7f,0.4f,0.9f, null));
        return tg;
    }
    /**
     * ubica los asientos en la rueda
     * @return  TransformGroup
     */
    private TransformGroup  setAsientos(){
        
        file  = PATH.TEXTURA + "techo_cono.jpg";
        file2 = PATH.TEXTURA + "asiento.jpg";

        TransformGroup aux ;
        TransformGroup tg = new TransformGroup();

        aux = getTransformGroup(new Vector3d(0.25, 1, 0),new Vector3d(2,2,2),0,0,0);  //^
        asiento =new N_Asiento(.8f,.8f,.8f,file,file2,rotator);
        aux.addChild(asiento);
        tg.addChild(aux);

        aux = getTransformGroup(new Vector3d(0.25, -1,0), new Vector3d(2,2,2),0,0,0);//<
        asiento2 = new N_Asiento(.2f,.8f,.8f,file,file2,rotator);
        aux.addChild(asiento2);
        tg.addChild(aux);

        aux = getTransformGroup(new Vector3d(0.25,0, 1),new Vector3d(2,2,2),0,0,0);//v
        asiento3 = new N_Asiento(.8f,.2f,.8f,null,file2,rotator);
        aux.addChild(asiento3);
        tg.addChild(aux);

        aux = getTransformGroup(new Vector3d(0.25,0,-1),new Vector3d(2,2,2),0,0,0);//>
        asiento4 = new N_Asiento(.8f,.8f,.2f,null,file2,rotator);
        aux.addChild(asiento4);
        tg.addChild(aux);
        getSoportes();
        return tg;
    }
    /**
     * ubica los soportes de los asientos en su sitio
     * @return TransformGroup 
     */
    private TransformGroup  getSoportes(){
        TransformGroup tg = new TransformGroup ();
       
        //soportes de los asientos
        tg.addChild(fig.getCylinder(1,1,new Vector3d(.5, 10,  0), new Vector3d(1, .2, .2), 0,0,toRadians(90), .7f,.7f,.7f, null));
        tg.addChild(fig.getCylinder(1,1,new Vector3d(.5,-10,  0), new Vector3d(1, .2, .2), 0,0,toRadians(90), .7f,.7f,.7f, null));
        tg.addChild(fig.getCylinder(1,1,new Vector3d(.5,  0, 10), new Vector3d(1, .2, .2), 0,0,toRadians(90), .7f,.7f,.7f, null));
        tg.addChild(fig.getCylinder(1,1,new Vector3d(.5,  0,-10), new Vector3d(1, .2, .2), 0,0,toRadians(90), .7f,.7f,.7f, null));

        tg.addChild(fig.getCylinder(1,1,new Vector3d(.35, 0, 0),  new Vector3d(1.45,.4,.4),0,0,toRadians(90), .7f,.7f,.7f, null));

        return tg;
    }
    /**
     * Para el movimiento de la Noria
     */
    public void parar(){
        asiento.parar();
        asiento2.parar();
        asiento3.parar();
        asiento4.parar();
        rotator.setEnable(false);
        transAlpha.pause();
    }
    /**
     * mueve la noria
     */
    public void mover(){
        asiento.mover();
        asiento2.mover();
        asiento3.mover();
        asiento4.mover();
        rotator.setEnable(true);
        transAlpha.resume();
    }
    /**
     * para saber si la noria esta en mvimiento o no
     * @return boolean
     */
    @Override
    public boolean enable(){
        boolean rs =false;
        if( asiento.enable() == asiento2.enable() && 
           asiento3.enable() == asiento4.enable() &&
           rotator.getEnable()==true)
            rs = true;                
        return  rs;
    }
}
