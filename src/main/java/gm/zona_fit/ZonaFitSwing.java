package gm.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import gm.zona_fit.gui.ZonaFitForma;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        // Modo oscuro
        FlatDarculaLaf.setup();

        // Instanciamos la fabrica de spring

        // Aqui creamos las configuraciones de spring boot como que va ser una aplicacion escritorio
        ConfigurableApplicationContext contextoSpring = new SpringApplicationBuilder
                (ZonaFitSwing.class).headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        // Crear un objeto de swing
        SwingUtilities.invokeLater(()->{
           ZonaFitForma zonaFitForma=contextoSpring.getBean(ZonaFitForma.class);
           zonaFitForma.setVisible(true);

        });
    }
}
