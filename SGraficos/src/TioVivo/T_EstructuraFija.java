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
package TioVivo;

import Figuras.Figuras;
import Utiles.PATH;
import Utiles.Texto3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import objetos3D.Objeto3D;

public  class T_EstructuraFija extends Objeto3D {

    TransformGroup objTrans ;
    Figuras fig;
    
    public T_EstructuraFija()    {
        fig = new Figuras();
        objTrans= new TransformGroup();
        getEstructura_Fija();
        setTexto3D();
        compile();
    }

    public void getEstructura_Fija()    {
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         String tex_1 = PATH.TEXTURA + "textura1.jpg";
         //Base
         objTrans.addChild(fig.getCylinder(1,1, new Vector3d(0,0,  0), new Vector3d(2,.7,2),   0,0,0, 0.7f,0.7f,0.7f, tex_1));
        tex_1 = PATH.TEXTURA + "textura7.jpg";
        //Columna
         objTrans.addChild(fig.getCylinder(1,1, new Vector3d(0,1.1,0), new Vector3d(.8,2.1,.8),0,0,0, 0.7f,0.7f,0.7f, tex_1));

         tex_1 = PATH.TEXTURA + "textura3.jpg";
         //Techo
          objTrans.addChild(fig.getCone (1,1, new Vector3d(0,3.7, 0), new Vector3d(2,1.5,2), 0,0,0, 0.7f,0.7f,0.7f, tex_1));

       this.addChild(objTrans);
    }
    private void setTexto3D(){

        TransformGroup tg = getTransformGroup(  new Vector3d(0,6.8,0),      //traslada
                                                new Vector3d(1f,1f,1f),     //escala
                                                0,0,0);                     //rota
        Texto3D t3d = new Texto3D("Tiovivo",tg,new Color3f(.2f,0.5f,.2f));

        this.addChild(t3d.getText3D_Rotacion( 6000, new Transform3D(),(float)Math.toRadians(360),0));
    }
}
