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

import Figuras.Figuras;
import Utiles.PATH;
import static java.lang.Math.toRadians;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Vector3d;

public final class N_EstructuraFija extends BranchGroup{

    private Figuras fig;
    private String file;
    /**
     * Constructor de la clase
     * con la llamada a crear la estructura
     */
    public N_EstructuraFija(){
        fig = new Figuras();
        file = PATH.TEXTURA +"textura8.jpg";
        crearEstructura();
    }
    /**
     * crea la estructura fija
     */
    private void crearEstructura(){
        
      getSoportes();
      getUniones();
    }
    /**
     * Soportes para la rueda de la Noria
     * crea el soporte de los dos lados  lo traslada, no lo escalo ya que el cilindro se le crea con el tamaño deseado
     * lo inclino y le añado textura (file) y los colores de la apariencia
     */
    private void getSoportes(){
        //lado 1
        this.addChild(fig.getCylinder(.15f, 4f,new Vector3d(0.9, 0, -1.2),null, toRadians(35),0,0, .3f,.3f,.3f, file ));
        this.addChild(fig.getCylinder(.15f, 4f,new Vector3d(0.9, 0,  1.2),null,-toRadians(35),0,0, .4f,.4f,.4f, file ));
        //lado2
        this.addChild(fig.getCylinder(.15f, 4f,new Vector3d(-0.9, 0, -1.2),null,  toRadians(35),0,0, .4f,.4f,.4f, file ));
        this.addChild(fig.getCylinder(.15f, 4f,new Vector3d(-0.9, 0,  1.2),null, -toRadians(35),0,0, .4f,.4f,.4f, file ));
    }

    /**
     * esferas para ocultar las uniones de los soportes
     * se traslada, escala se añade textura y los colores de la apariencia
     */
    private void getUniones(){

        this.addChild(fig.getSphere(1f,new Vector3d(1.9, 3.7, 0),new Vector3d(.5,.5,.5), 0,0,0, .4f,.4f,.4f, file ));
        this.addChild(fig.getSphere(1f,new Vector3d(-1.9, 3.7,0),new Vector3d(.5,.5,.5), 0,0,0, .4f,.4f,.4f, file ));
    } 

}
