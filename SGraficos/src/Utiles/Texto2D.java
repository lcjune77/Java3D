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

import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Font;
import javax.media.j3d.Appearance;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;

public class Texto2D {


    public static TransformGroup getTexto2D(String text, Color3f color,String tipo,int tam){
        
        TransformGroup objTrans = new TransformGroup();
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


	Shape3D textObject = new Text2D(text,
					color,
					tipo,
					tam,
					Font.BOLD);
	Appearance app = textObject.getAppearance();

	PolygonAttributes pa = app.getPolygonAttributes();
	if (pa == null)
	    pa = new PolygonAttributes();
	pa.setCullFace(PolygonAttributes.CULL_NONE);
	if (app.getPolygonAttributes() == null)
	    app.setPolygonAttributes(pa);
	objTrans.addChild(textObject);
        return objTrans;
    }
}
