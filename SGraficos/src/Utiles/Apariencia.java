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

import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;

public class Apariencia {
        //Create the appearance

    Color3f ambientColour = new Color3f(1.0f, 0.0f, 0.0f);
    Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
    Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f diffuseColour = new Color3f(1.0f, 0.0f, 0.0f);
    float shininess = 20.0f;

    public Apariencia(){
//        Appearance app = new Appearance();
//        app.setMaterial(new Material(ambientColour, emissiveColour,
//        diffuseColour, specularColour, shininess));
    }
    /**
 *
 * @param r
 * @param v
 * @param a
 * @param file
 * 
 * @return
 */
    public Appearance getApariencia(float r,float v, float a,String file){
        
        Appearance app = new Appearance();
        Material m= new Material();
        m.setAmbientColor(r,v,a);
        m.setDiffuseColor(0.6f,0.6f,0.6f);
        m.setSpecularColor(0.6f,.6f,.6f);
        m.setEmissiveColor(0f, 0f, 0f);
        m.setLightingEnable(true);
        m.setShininess(80);
        
        LineAttributes lineAttributes = new LineAttributes();
        lineAttributes.setLineAntialiasingEnable(true);
        app.setLineAttributes(lineAttributes);

        ColoringAttributes ca = new ColoringAttributes();
        ca.setShadeModel(ColoringAttributes.ALLOW_SHADE_MODEL_WRITE);
        app.setColoringAttributes(ca);

        PolygonAttributes pa = new PolygonAttributes();
        pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        app.setPolygonAttributes(pa);
        
        if(file!=null){
            TextureLoader loader = new TextureLoader(file, new Container());
            Texture texture = loader.getTexture();
            texture.setBoundaryModeS(Texture.WRAP);
            texture.setBoundaryColor(new Color4f(.0f, 1.0f, .0f, 0.0f));
            TextureAttributes texAttr = new TextureAttributes();
            texAttr.setTextureMode(TextureAttributes.MODULATE);
            app.setTexture(texture);
            app.setTextureAttributes(texAttr);
        }

        app.setMaterial(m);
       
        return app;

    }

}
