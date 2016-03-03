/*******************************************************************************
 *
 * Asignatura:  Sistemas Gráficos - Prácticas
 * Tema:        Parque de atracciones (Noria, Tiovivo, Coches de choque,La Barca)
 * Curso :      2013 - 2014
 * Universidad: UGR
 * @author      Alicia Lara
 * @author      Marlene Vásquez
 * @version     1.0
 *
 ******************************************************************************/
package park;

import Escena.Escena;
import Utiles.PATH;
import Utiles.Pick;
import com.sun.j3d.audioengines.javasound.JavaSoundMixer;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.universe.*;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Park  extends JFrame {
    private SimpleUniverse universo;
    
    Escena  escena;
    PickCanvas pickCanvas;
    public Park(){
        
        initComponents();
       
        setTitle("Sistemas Gráficos - Alicia and Marlene");
        escena = new Escena(this);
        boolean spin=false;
        
        setLayout(new BorderLayout());
        setSize(1900, 1200);
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        universo= new SimpleUniverse(canvas3D);
        getContentPane().add("Center",canvas3D);
    /**
     *  frustrum
     */
        universo.getViewer().getView().setBackClipDistance(10000);

    /**
     * add mouse behaviors to the ViewingPlatform
     */
	ViewingPlatform viewingPlatform = universo.getViewingPlatform();
        universo.getViewingPlatform().setNominalViewingTransform();

        TransformGroup vtg = viewingPlatform.getMultiTransformGroup().getTransformGroup(0);
        Transform3D View_Transform3D = new Transform3D();
        vtg.getTransform(View_Transform3D);
        View_Transform3D.setTranslation(new Vector3f(0.0f, 5f, 55.0f));
        vtg.setTransform(View_Transform3D);

        if (!spin) {
            OrbitBehavior orbit   = new OrbitBehavior(canvas3D,OrbitBehavior.REVERSE_ALL);
            BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
            orbit.setSchedulingBounds(bounds);
            viewingPlatform.setViewPlatformBehavior(orbit);
	}
        /**
         * Pick
         */
        pickCanvas = new PickCanvas (canvas3D, escena);
        pickCanvas.setMode (PickCanvas.GEOMETRY);
        pickCanvas.setShapeCylinderRay(new Point3d(0,0,0), new Vector3d(0,0,0), 1);

        Pick pick = new Pick(escena,pickCanvas);
        canvas3D.addMouseListener ((MouseListener) pick);

        universo.addBranchGraph(escena);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Park.class.getResource("/texturas/Favorite.png")));
     
        setVisible(true);
         pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Park park = new Park();
        }
//    }

   private void initComponents()
  {
    addWindowListener (new WindowAdapter () {
    @Override
    public void windowClosing (WindowEvent evt) {exitForm (evt);}

        private void exitForm(WindowEvent evt) {
            System.exit (0);
        }
    } );
  }
}