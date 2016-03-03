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

import Escena.Escena;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import javax.media.j3d.BranchGroup;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Pick extends MouseAdapter {
    Escena escena ;
    PickCanvas pickCanvas;
    private Clip clip;
    
    public Pick (Escena es ,  PickCanvas pickCanv){
        escena = es;
        pickCanvas =  pickCanv;
    }

    @Override
    public void mouseClicked (MouseEvent e)
   {
        pickCanvas.setShapeLocation (e);
        PickResult result=pickCanvas.pickClosest();
      //  PickResult result = pickCanvas.pickAny();

         if (result == null)
         {
             System.out.println ("Nothing picked");
         }else
         {
             BranchGroup s =(BranchGroup )result.getNode(PickResult.BRANCH_GROUP);

             if(s!= null){                 
             String name = s.getName();
             System.out.println (name);
             boolean rs ;
             switch (name) {
                 case "Noria":
                     {
                         rs= escena.noria.enable();
                          getSound("./Sonido/magic_bells.wav");
                         if(rs){
                             escena.noria.parar();                         
                         }else  {
                                escena.noria.mover();                              
                         }        System.out.println (escena.noria.getName());
                         break;
                     }
                 case "Barca":
                     {
                         rs = escena.barco.enable();
                         if(rs){
                             escena.barco.parar();
                         }else  escena.barco.mover();
                         break;
                     }
                 case "Coches":
                     {
                         rs = escena.coches.enable();
                         if(rs){
                             escena.coches.parar();
                         }else  escena.coches.mover();
                         break;
                     }
                 case "Tiovivo":
                     {
                         rs = escena.carrusel.enable();

                         if(rs){
                             escena.carrusel.parar();
                             
                             //escena.carrusel.movil.rotator.setEnable(false);

                         }else  escena.carrusel.mover();
                            // escena.carrusel.movil.rotator.setEnable(true);
                         break;
                     }
                 case "Tardis":
                     {
                         rs = escena.Tardis.enable();
                         if(rs){
                             escena.Tardis.pararRotacion();

                             //escena.carrusel.movil.rotator.setEnable(false);

                         }else  escena.Tardis.mueveRotacion();
                            // escena.carrusel.movil.rotator.setEnable(true);
                         break;
                     }
                 case "Parque":
                     {
                         getSound("./Sonido/loop1.wav");
                         break;

                     }
                 case "helicoptero":
                     {
                          
                          getSound("./Sonido/loop1.wav");
                         break;

                     }
             }
         }
    }
   }
     public void getSound(String fname) {
       clip = null;

        try {
         URL url = this.getClass().getClassLoader().getResource(fname);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         clip =  AudioSystem.getClip();
         clip.open(audioIn);
         clip.setFramePosition(0);
         clip.start();
      //   clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
        }

     }
      public void stop(){
            clip.stop();
        }
}