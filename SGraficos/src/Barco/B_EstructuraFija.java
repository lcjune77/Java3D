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
import Utiles.PATH;
import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Font;
import static java.lang.Math.PI;
import javax.media.j3d.*;
import javax.vecmath.*;
import objetos3D.Objeto3D;

public final class B_EstructuraFija  extends Objeto3D{

    private String file;
    private Figuras fig;
    public B_EstructuraFija(){
        fig = new Figuras();

        file = PATH.TEXTURA + "textura1"+".jpg";
     
        this.addChild(getTexto());
        this.addChild(getSoporte1());
        this.addChild(getSoporte2());
        this.addChild(fig.getCubo(0.2f, 3f, 0.2f,new Vector3d(2, 0, -1.5),null, PI/6, 0, 0, 0.7f, 0.7f, 0.7f,file ));
        this.addChild(fig.getCubo(0.2f, 3f, 0.2f,new Vector3d(2, 0, 1.5),null, -PI/6, 0, 0, 0.7f, 0.7f, 0.7f,file ));
       
    }

/**
 *
 * @return TransformGroup
 */
    private TransformGroup getSoporte1(){

        return fig.getCubo( 0.2f,3f,0.2f,          //parametros del cubo
                        new Vector3d(0,0,-1.5),   //vector de desplazamiento
                        null,                       //vector de escalado
                        PI/6, 0, 0,                 //rotacion (x,y,z)
                        0.7f, 0.7f, 0.7f,file );      //Color y textura
    }
/**
 *
 * @return TransformGroup
 */
     private TransformGroup getSoporte2(){

        return fig.getCubo( 0.2f,3f,0.2f,           //parametros del cubo
                        new Vector3d(0,0,1.5),      //vector de desplazamiento
                        null,                       //vector de escalado
                        -PI/6, 0, 0,                //rotacion (x,y,z)
                        0.7f, 0.7f, 0.7f,file );    //Color y textura
    }

    private TransformGroup getTexto(){

        TransformGroup objTran = getTransformGroup(new Vector3d(1.15,1.1,0.5),//vector de deplazamiento
                                                        new Vector3d(2,2,2),        //vector escalad
                                                        0,PI/2,0);                  //rotación(x,yz)
      
	Shape3D textObject = new Text2D("Barca",
					new Color3f(0.5f,0.7f, 0.1f),
					"Serif",
					60,
					Font.BOLD);
	Appearance app = textObject.getAppearance();

	PolygonAttributes pa = app.getPolygonAttributes();
	if (pa == null)
	    pa = new PolygonAttributes();
	pa.setCullFace(PolygonAttributes.CULL_NONE);
	if (app.getPolygonAttributes() == null)
	    app.setPolygonAttributes(pa);
	objTran.addChild(textObject);

       return  objTran;
    }
}
