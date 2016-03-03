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

import static Utiles.Luces.getLuzPuntual;
import static Utiles.Luces.getLuzSpot;
import Figuras.Figuras;
import Utiles.PATH;
import Utiles.Texto3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import objetos3D.Objeto3D;

public class C_EstructuraFija extends Objeto3D{
    private Figuras fig;

    public C_EstructuraFija(){
        fig = new Figuras();
        crearEstructuraFija();
    }
/**
 * Crea la estructura 
 */
    private void crearEstructuraFija(){
          
        getTecho();
        getBase();
        getBordes();
        getColumnas();
        getTexto3D();
        getLuz();
        
    }
/**
 * Añade el techo de la estructura
 */
      private void getTecho(){
      
        String file = PATH.TEXTURA +"textura.jpg";
       this.addChild(fig.getCubo(1f,1f,1f, new Vector3d(0,6.5,0), new Vector3d(1.5 ,0.3,1.5),  0,0,0,  0.7f, 0.7f, 0.7f,    file));
      }
/**
 * Añade la base de la estructura
 */
      private void getBase(){
     
        String file = PATH.TEXTURA +"textura6.jpg";
        this.addChild(fig.getCubo(1f,1f,1f, new Vector3d(0,0,0),    new Vector3d(1.5 ,0.2,1.5),  0,0,0,  0.7f, 0.7f, 0.7f,    file));
      }
/**
 * Añade las 4 columnas de la estructura
 */
      private void getColumnas(){
     
        String file = PATH.TEXTURA +"textura9.jpg";
        Vector3d v3d_esc = new Vector3d(.3, 1.4, 0.3);

        this.addChild(fig.getCylinder(1f,1f,  new Vector3d(6,  0.7, 6), v3d_esc, 0,0,0,  0.7f, 0.7f, 0.7f, file));
        this.addChild(fig.getCylinder(1f,1f,  new Vector3d(6,  0.7,-6), v3d_esc, 0,0,0,  0.7f, 0.7f, 0.7f, file));
        this.addChild(fig.getCylinder(1f,1f,  new Vector3d(-6, 0.7,-6), v3d_esc, 0,0,0,  0.7f, 0.7f, 0.7f, file));
        this.addChild(fig.getCylinder(1f,1f,  new Vector3d(-6, 0.7, 6), v3d_esc, 0,0,0,  0.7f, 0.7f, 0.7f, file));
      }
/**
* Añade los bordes a la estructura
*/
    private void getBordes(){
           
        String file = PATH.TEXTURA +"textura5.jpg";
        
        this.addChild(fig.getCubo(0.1f,0.1f,0.1f, new Vector3d(0,   0.1, 1.8), new Vector3d(4.2,0.7, 1),  0,0,0,  0.7f, 0.7f, 0.7f, file));
        this.addChild(fig.getCubo(0.1f,0.1f,0.1f, new Vector3d(0,   0.1,-1.8), new Vector3d(4.2,.7, 1),  0,0,0,  0.7f, 0.7f, 0.7f, file));
        this.addChild(fig.getCubo(0.1f,0.1f,0.1f, new Vector3d(-1.8,0.1,   0), new Vector3d(1, .7, 4.2), 0,0,0,  0.7f, 0.7f, 0.7f, file));
        this.addChild(fig.getCubo(0.1f,0.1f,0.1f, new Vector3d(1.8, 0.1,   0), new Vector3d(1, .7, 4.2), 0,0,0,  0.7f, 0.7f, 0.7f, file));

      }
/**
* Añade texto a la estructura
*/

    private void getTexto3D(){

          TransformGroup tg = getTransformGroup(new Vector3d(0,2.7,0),new Vector3d(.8f,.8f,.8f),0,0,0);
          Texto3D t3d = new Texto3D("Coches",tg,new Color3f(.2f,.2f,.9f));

          this.addChild(t3d.getText3D_Rotacion( 4000, new Transform3D(),(float)Math.toRadians(360),0));
      }
/**
* Añade Luz a la estructura
*/
      private void getLuz(){

        TransformGroup aux = fig.getSphere(1f, new Vector3d(0,5,0),    new Vector3d(0.4 ,0.4,0.4),  0,0,0, .1f, 0.1f,1f,   null);
        aux.addChild(getLuzSpot(new Color3f(.8f,.2f,.2f),new Point3f(0,-10,0),new Point3f(0,0.5f,0),new Vector3f(0,10,0),45f,50));
        this.addChild(aux);
        this.addChild(getLuzPuntual(2,new Color3f(.8f,.8f,.8f),new Point3f(0,2f,0),new Point3f(0,0.1f,0)));
      }
      
}
