//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import GUI.ImageJFrame;
import lectura.Grafica;
import lectura.ImageManager;
import java.lang.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        // Leer imagen
        Image imagen = ImageManager.openImage();
        if (imagen != null) {
            System.out.println("Imagen cargada");
        }

        // Procesamiento
        BufferedImage aux = ImageManager.toBufferedImage(imagen);


        // Colores
        Color verde = new Color(41, 105, 55);
        Color blanco = new Color(255, 255, 255);
        Color rojo = new Color(255, 0, 0);
        Color cafe = new Color(130,93,0);

        int ancho = aux.getWidth();
        int alto = aux.getHeight();

        /*

        // Verde
        for (int i = 0; i < alto; i++){
            for (int j = 0; j < (ancho/3); j++){
                aux.setRGB(j, i, verde.getRGB());
            }
        }

        // Blanco
        for (int i = 0; i < alto; i++){
            for (int j = (ancho/3); j < 2*(ancho/3); j++){
                aux.setRGB(j, i, blanco.getRGB());
            }
        }

        // Rojo
        for (int i = 0; i < alto; i++){
            for (int j = 2*(ancho/3); j < ancho; j++){
                aux.setRGB(j, i, rojo.getRGB());
            }
        }

        // Cafe
        for (int i = 0; i < alto; i++){
            for (int j = 0; j < ancho; j++) {
                if (Math.pow(j - (ancho/2), 2) + Math.pow(i - (alto/2), 2) <= 4000) {
                    aux.setRGB(j,i,cafe.getRGB());
                }
            }
        }
        */

        // Puro rojo
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                int emp = aux.getRGB(j, i);
                Color aux_color = new Color(emp);

                int r = aux_color.getRed();
                int g = aux_color.getGreen();
                int b = aux_color.getBlue();

                if (r < 0) {
                    r = 0;
                }
                if (g < 0) {
                    g = 0;
                }
                if (b < 0) {
                    b = 0;
                }
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }

                aux_color = new Color(r,g,b);
                aux.setRGB(j, i, aux_color.getRGB());
            }
        }

        // Calcular histograma
        Color color_aux;
        int[] conteo_r= new int[256];
        int[] conteo_g = new int[256];
        int[] conteo_b = new int[256];

        for (int i=0; i<alto; i++){
            for (int j = 0; j < ancho; j++) {
                int rgb = aux.getRGB(j, i);
                color_aux = new Color(rgb);
                int r = color_aux.getRed();
                int g = color_aux.getGreen();
                int b = color_aux.getBlue();
                conteo_r[r] += 1;
                conteo_g[g] += 1;
                conteo_b[b] += 1;
            }
        }

        // Grafica

        Grafica.histograma(conteo_r, conteo_g, conteo_b);

        // Mostrar imagen
        imagen = ImageManager.toImage(aux);

        new ImageJFrame(imagen);

    }
}
