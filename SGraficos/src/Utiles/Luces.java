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

import javax.media.j3d.*;
import javax.vecmath.*;

public class Luces {

    public void Luces(){}
    /**
    *
    * @param a
     * @param color
    * @return
    */
    public static TransformGroup getLuzAmbiental(int a ,Color3f color){
       TransformGroup l =  new TransformGroup();
       AmbientLight al = new AmbientLight(color);//new Color3f(0.8f, 0.8f, 0.8f
       BoundingSphere bounds = new BoundingSphere(new Point3d(0,2, 0), Double.MAX_VALUE);
       al.setInfluencingBounds( bounds );
       al.setCapability(Light.ALLOW_STATE_WRITE);
       al.setEnable(true);
       l.addChild(al);

      

       return l;
    }
    public static TransformGroup getLuzDireccional(int a,Color3f color, Vector3f dis){
        
        TransformGroup l =  new TransformGroup();
        DirectionalLight dl = new DirectionalLight(true,color,dis);//new Color3f(0.5f,0.5f,0.5f
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0, 0), a);
        dl.setCapability(Light.ALLOW_STATE_WRITE);
        dl.setInfluencingBounds(bounds);
        l.addChild(dl);
       return l;
    }

    public static TransformGroup getLuzPuntual(float a,Color3f color, Point3f posicion,Point3f atenuacion){
        TransformGroup l =  new TransformGroup();
        
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0, 0.0), a);
        PointLight pl = new PointLight(color,posicion,atenuacion);//new Color3f(.5f,.5f,1)
        pl.setEnable(true);
        pl.setCapability(Light.ALLOW_STATE_WRITE);
        pl.setInfluencingBounds(bounds);
        l.addChild(pl);
        return l;
    }
     public static TransformGroup getLuzSpot(Color3f color,Point3f pos,  Point3f atten, Vector3f dir, float spread, float concen){
        TransformGroup l =  new TransformGroup();
        SpotLight light = new SpotLight( );
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0, 0.0), 10);
        light.setCapability(Light.ALLOW_STATE_WRITE);
        light.setEnable( true );
        light.setColor( color);
        light.setPosition( pos );
        light.setAttenuation( atten );
        light.setDirection( dir );
        light.setSpreadAngle( spread );
        light.setConcentration( concen );
        light.setInfluencingBounds(bounds);
        l.addChild(light);
        return l;
     }


}
