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
package Escena;

import Fuegos.FuegosArtificiales;
import Barco.B_Escena_Barco;
import CochesDeChoque.C_Escena_Coches;
import Noria.N_Escena_Noria;
import TioVivo.T_Escena_Tiovivo;
import Utiles.*;
import static Utiles.Luces.getLuzAmbiental;
import static Utiles.Luces.getLuzDireccional;
import static Utiles.Luces.getLuzPuntual;
import static Utiles.Luces.getLuzSpot;
import Utiles.PATH;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Component;
import static java.lang.Math.PI;
import javax.media.j3d.*;
import static javax.media.j3d.Background.*;
import javax.vecmath.*;
import objetos3D.Objeto3D;

public final class Escena extends Objeto3D {
    
    //acceso a las atracciones
    /**
     * Escena Tiovivo
     */
    public T_Escena_Tiovivo carrusel;
    /**
     * Escena de Coches de choque
     */
    public C_Escena_Coches coches;
    /**
     * Escena de la Noria
     */
    public N_Escena_Noria noria;
    /**
     * Escena de la Barca
     */
    public B_Escena_Barco barco;
    /**
     * Objeto Tardis
     */
    public Objeto3D Tardis;
    
    Component com;

    FuegosArtificiales fg;
    public Objeto3D helicoptero;
    /**
     * Escena contiene todos los objetos del parque de atracciones
     * Noria, Tiovivo, La Barca, Coches de choque y otro elementos
     * (árboles, tardis, helicóptero).
     * @param s Component - Frame
     */
    public Escena(Component s){

        this.setName("Parque");
        this.setPickable(true);
        this.setCapability(BranchGroup.ENABLE_PICK_REPORTING);
        com = s;
        
        this.carrusel = new T_Escena_Tiovivo();
        this.coches   = new C_Escena_Coches();
        this.barco    = new B_Escena_Barco();
        this.noria    = new N_Escena_Noria();
        this.Tardis   = new Objeto3D(PATH.OBJ + "TARDIS.obj",0){} ;
        this.helicoptero = new Objeto3D(PATH.OBJ + "heli.obj",0){} ;
        fg = new FuegosArtificiales();
        crearEscena();     
    }
    /**
     * crea la escena con todos sus componentes
     */
    public void crearEscena(){
       
        setBackground();
        setSuelo();
        setLuces();
        setNoria();
        setTiovivo();
        setLaBarca();
        setCochesDeChoque();
        setFuente();
        setFuegosArtificiales();
        this.addChild(getHelicoptero());
        this.addChild(getTardis());
        this.addChild(getTree());
       
    }
    /**
     * Añade fondo a la escena
     */
    public void setBackground(){
         // Set up the background
        TextureLoader myLoader = new TextureLoader(PATH.TEXTURA + "nubes.jpg", com);
        ImageComponent2D myImage = myLoader.getImage( );

        Background myBack = new Background( );
        myBack.setImage( myImage );
        myBack.setImageScaleMode(SCALE_FIT_ALL );
        myBack.setApplicationBounds(new BoundingSphere(new Point3d( ), 1000.0 ) );

        this.addChild(myBack);     
    }
    /**
     * Añade el suelo a la escena
     */
    public void setSuelo(){

        Apariencia apr=new Apariencia();
        Transform3D d = new Transform3D();
        d.setTranslation(new Vector3d(0,-0.4,0));
        TransformGroup t = new TransformGroup(d);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        t.addChild( new Box(30,0.5f,30, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,
                    apr.getApariencia(.8f,.8f,.8f,PATH.TEXTURA +"suelo_c.jpg")));
        this.addChild(t);
    }
    /**
     * Añade la Noria a la Escena
     */

    private void setNoria(){
        TransformGroup tg ;
        tg = getTransformGroup(new Vector3d(-12,2,9), new Vector3d(1.3,1.3,1.3), 0,Math.toRadians(45),0);
        tg.addChild(noria);
        this.addChild(tg);
    }
    /**
     * Añade el Tiovivo a la escena
     */

   private void setTiovivo(){
        TransformGroup tg ;
        tg = getTransformGroup(new Vector3d(8,0.5,-10), new Vector3d(1.1,1.1,1.1), 0 , 0, 0);
        tg.addChild(carrusel);
        this.addChild(tg);
    }
   /**
    * Añade los coches de choque a la escena.
    */
    private void setCochesDeChoque(){
        TransformGroup tg ;
        tg= getTransformGroup(new Vector3d(9,0.3,6), new Vector3d(1.65,1.65,1.65), 0 , 0, 0);
        tg.addChild(coches);
        this.addChild(tg);
    }
    /**
     * Añade la barca a la escena.
     */
     private void setLaBarca(){
        TransformGroup tg ;
        tg = getTransformGroup(new Vector3d(-10,3,-7), new Vector3d(1.3,1.3,1.3), 0 , Math.toRadians(-45), 0);
        tg.addChild(barco);
        this.addChild(tg);
    }
     /**
      * Añade luces a la escena
      * dos luces direccionales
      * y la ambiental
      */
     private void setLuces(){

        this.addChild(getLuzDireccional(100,new Color3f(.8f,.8f,.8f), new Vector3f(1,0,-1)));
     //   this.addChild(getLuzDireccional(100,new Color3f(.8f,.8f,.8f), new Vector3f(-1,0,1)));

      //  this.addChild(getLuzDireccional(100,new Color3f(.8f,.8f,.8f), new Vector3f(-1,0,1)));
        this.addChild(getLuzDireccional(10,new Color3f(.7f,.7f,.7f), new Vector3f( 10,0,-10)));

        this.addChild(getLuzAmbiental  (1000,new Color3f(.8f,.8f,.8f)));
        
     }
     /**
      * Añade una fuente a la escena
      */
      private void setFuente(){
        TransformGroup tg ;
        Fuente fuente = new Fuente(PATH.OBJ +"fuente.obj",0);

        tg = getTransformGroup(new Vector3d(0,0.6,0), new Vector3d(1.2,1.2,1.2), 0 , 0, 0);
        tg.addChild(fuente.getObjeto());
        this.addChild(tg);
      }
      /**
       * Se crea un objeto en este caso es un helicóptero
       * @return TransformGroup
       * que luego se añadirá a la escena
       */
      private TransformGroup getHelicoptero(){

        helicoptero.setPickable(true);
        helicoptero.setName("helicoptero");
        helicoptero.setCapability(BranchGroup.ENABLE_PICK_REPORTING);
        TransformGroup tg_ = helicoptero.getObjetoMovil_Rotacion(  new Vector3d(7,9,7),  //traslada
                                                new Vector3d(1.6,1.6,1.6),          //escala
                                                -PI/2, 0, 0,                        //rota
                                                10000, new Transform3D(),(float)Math.toRadians(360),0);
        //luces del objeto
        helicoptero.addChild(getLuzPuntual(5f,new Color3f(.8f,.2f,.2f),new Point3f(0,3f,0),new Point3f(0,.1f,0)));
        helicoptero.addChild(getLuzSpot(new Color3f(.8f,.2f,.2f),new Point3f(-3,0,0),new Point3f(0,1f,0),new Vector3f(0,0,0),45f,100));
      
        return tg_;
    }
      /**
       * Se crea un objeto en este caso es un Tardis
       * @return TransformGroup
       * que luego se añadirá a la escena
       */
    private TransformGroup getTardis(){
       TransformGroup t = getTransformGroup(new Vector3d(-3,5,-16),null,0,0,0);
        
        Tardis.setPickable(true);
        Tardis.setName("Tardis");
        Tardis.setCapability(BranchGroup.ENABLE_PICK_REPORTING);        
        //axis.rotY(-Math.PI/2);
        TransformGroup tg_ = Tardis.getObjetoMovil_Rotacion(new Vector3d(0.5,0,.5),    //traslada
                                                new Vector3d(2,2,2),                     //escala
                                                0,0,0,
                                                7000, new Transform3D(),(float)Math.toRadians(360),0);
        Tardis.pararRotacion();
        //luces del objeto
       // Tardis.addChild(getLuzPuntual(2f,new Color3f(0f,0f,8f),new Point3f(0,1f,0),new Point3f(0,.1f,0)));
       // Tardis.addChild(getLuzSpot(new Color3f(.8f,.2f,.2f),new Point3f(2,1,0),new Point3f(0,4f,0),new Vector3f(0,3,0),45f,50));
        
        t.addChild(tg_);
        return t ;
    }
    /**
     * Se crea objetos en este caso un par de árboles
     * @return TransformGroup
     * que luego se añadirá a la escena
     */
    
    private TransformGroup getTree(){

        TransformGroup t = new TransformGroup();
        Objeto3D tree = new Objeto3D(PATH.OBJ + "tree.obj",0){} ;
        Objeto3D tree2 = new Objeto3D(PATH.OBJ + "tree.obj",0){} ;
        Transform3D axis= new Transform3D();
        TransformGroup tg_ = tree.getObjeto(  new Vector3d(8,2.2,-8),    //traslada
                                                new Vector3d(2,2,2),    //escala
                                                0,0,0);
        tg_.addChild(getLuzPuntual(.1f,new Color3f(.1f,.3f,.1f),new Point3f(0,.5f,0),new Point3f(0,0.1f,0)));
        t.addChild(tg_);

        tg_= tree2.getObjeto(  new Vector3d(-8,2.2,-8),                 //traslada
                                                new Vector3d(2,2,2),   //escala
                                                0,0,0);
        t.addChild(tg_);
        return t;
    }
    /**
     *Añade los fuegos artificiales a la escena
     */
     private void setFuegosArtificiales(){
      
      TransformGroup tg ;
        tg = getTransformGroup(new Vector3d(0,2,0), new Vector3d(1.2,1.2,1.2), (float)Math.toRadians(360),0,0);
        tg.addChild(fg);
        this.addChild(tg);
    }
}
