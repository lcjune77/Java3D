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

package Figuras;

import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import Utiles.Apariencia;
import objetos3D.Objeto3D;

public class Figuras extends Objeto3D{

    public Figuras(){}
    
/**
 *
 * @param al float - altura del Box
 * @param an float - anchura del Box
 * @param p float - profundidad del Box
 * @param d Vector3d - valores para generar la traslación (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 *
 * @param r float - grado de color rojo para la apariencia
 * @param v float - grado de color verde para la apariencia
 * @param a float - grado de color azul para la apariencia
 * @param f String ruta del fichero que se va ausar para la textura
 *
 * @return TransformGroup contiene el figura -Cubo- al que se aplica las
 * transformaciones con los valores que se pasa a la función
 */
    public  TransformGroup getCubo(float al,float an,float p,Vector3d d,Vector3d e,double rx, double ry,double rz,float r,float v, float a,String f){
        Apariencia apr=new Apariencia();
        TransformGroup t = getTransformGroup( d,e,rx,ry,rz);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        t.addChild(new Box(al,an,p,Box.GENERATE_NORMALS|Box.GENERATE_TEXTURE_COORDS,apr.getApariencia(r,v,a,f)));
        return t;
    }

/**
 *
 * @param rad float - radio de la esfera
 * @param d Vector3d - valores para generar la traslación (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 *
 * @param r float - grado de color rojo para la apariencia
 * @param v float - grado de color verde para la apariencia
 * @param a float - grado de color azul para la apariencia
 * @param f String ruta del fichero que se va usar para la textura
 *
 * @return TransformGroup contiene el figura -Esfera- al que se aplica las
 * transformaciones con los valores que se pasa a la función
 */
     public TransformGroup getSphere(float rad,Vector3d d,Vector3d e,double rx, double ry,double rz,float r,float v, float a,String f){
        Apariencia apr=new Apariencia();
        TransformGroup t = getTransformGroup( d,e,rx,ry,rz);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        t.addChild(new Sphere(rad,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,apr.getApariencia(r,v,a,f)));

        return t;
    }

          /**
 *
 * @param rad float - radio del cono
 * @param al float altura del cono
 * @param d Vector3d - valores para generar la traslación (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 *
 * @param r float - grado de color rojo para la apariencia
 * @param v float - grado de color verde para la apariencia
 * @param a float - grado de color azul para la apariencia
 * @param f String ruta del fichero que se va usar para la textura
 *
 * @return TransformGroup contiene el figura -Cono- al que se aplica las
 * transformaciones con los valores que se pasa a la función
 */
    public  TransformGroup getCone(float rad,float al, Vector3d d,Vector3d e,double rx, double ry,double rz,float r,float v, float a,String f){
        Apariencia apr=new Apariencia();
        TransformGroup t = getTransformGroup( d,e,rx,ry,rz);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       t.addChild(new Cone(rad,al,Cone.GENERATE_NORMALS | Cone.GENERATE_TEXTURE_COORDS, apr.getApariencia(r,v,a,f)));

        return t;
    }
/**
 *
 * @param al float - altura del Cilindro
 * @param an float - anchura del Cilindro
 * @param d Vector3d - valores para generar la traslación (x,y,z)
 * @param e Vector3d - valores para generar el escalado (x,y,z)
 * @param rx double - valor con el que se va ha generar la rotacion en el eje X
 * @param ry double - valor con el que se va ha generar la rotacion en el eje Y
 * @param rz double - valor con el que se va ha generar la rotacion en el eje Z
 *
 * @param r float - grado de color rojo para la apariencia
 * @param v float - grado de color verde para la apariencia
 * @param a float - grado de color azul para la apariencia
 * @param f String ruta del fichero que se va ausar para la textura
 *
 * @return TransformGroup contiene el figura -Cilindro- al que se aplica las
 * transformaciones con los valores que se pasa a la función
 */
    public  TransformGroup getCylinder(float al,float an,Vector3d d,Vector3d e,double rx, double ry,double rz,float r,float v, float a,String f){
        Apariencia apr = new Apariencia();
        TransformGroup t =  getTransformGroup( d,e,rx,ry,rz);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        t.addChild(new Cylinder(al,an,Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS,apr.getApariencia(r,v,a,f)));

        return t;
    }
 
}
