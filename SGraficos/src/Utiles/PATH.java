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

public enum PATH {
	TEXTURA(1),
	OBJ(2),
        L3DS(3),
	SONIDO(4);

	private int value;

	private PATH(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		switch(this) {
			case TEXTURA:
				return  System.getProperty("user.dir") +
                                        System.getProperty("file.separator")+"src"+
                                        System.getProperty("file.separator")+"texturas"+
                                        System.getProperty("file.separator");
			case OBJ:
				return  System.getProperty("user.dir") +
                                        System.getProperty("file.separator")+"src"+
                                        System.getProperty("file.separator")+"obj"+
                                        System.getProperty("file.separator");
                        case L3DS:
                            return  System.getProperty("user.dir") +
                                    System.getProperty("file.separator")+"src"+
                                    System.getProperty("file.separator")+"l3ds"+
                                    System.getProperty("file.separator");
                        case SONIDO:
                            return  System.getProperty("user.dir") +
                                    System.getProperty("file.separator")+"src"+
                                    System.getProperty("file.separator")+"Sonido"+
                                    System.getProperty("file.separator");
			
			default:
				return null;
		}
	}
}



