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

package Barco;

import Figuras.Figuras;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public class B_Asiento extends Objeto3D {
    private Figuras fig ;

 
/**
 *
 * @param r - float, grado de color rojo
 * @param v - float, grado de color verde
 * @param a - float, grado de color azul
 * @param file  - String, ruta del fichero de textura
 * @param file2 - String, ruta del fichero de textura
 *
 */
    public  B_Asiento(float r,float v,float a,String file,String file2){
        fig = new Figuras();
        crearAsiento(r,v,a,file,file2);
        compile();

    }
    /**
     * Crea el asiento con la textura y nivels de colores pasados como parámetros
     * @param r  float nivel de color rojo
     * @param v  float nivel de color verde
     * @param a  float nivel de color azul
     * @param file  String fichero de textura para los lados del asiento
     * @param file2  String fichero de textura para el respado y base del asiento
     */
    private void crearAsiento(float r,float v,float a,String file,String file2){
        TransformGroup taux ;
        //lado
        taux = fig.getCubo(1,1,1,new Vector3d(0,-0.32,-3),new Vector3d(0.4, 0.3, 0.1),0,0,0, r,v,a, file);
        this.addChild(taux);
        taux= fig.getCubo(1,1,1,new Vector3d(0,-0.32,3),new Vector3d(0.4, 0.3, 0.1),0,0,0,  r,v,a, file);
        this.addChild(taux);

        //bajo
        taux= fig.getCubo(1,1,1,new Vector3d(0,-0.5,0),new Vector3d(0.4, 0.25, 0.5),0,0,0,r,v,a,file2);
        this.addChild(taux);
        //respaldo
        taux= fig.getCubo(1, 1,1,new Vector3d(0,0,0),new Vector3d(0.1, 0.35, 0.5),0,0,0,r,v,a,file2);
        this.addChild(taux);
    }
}