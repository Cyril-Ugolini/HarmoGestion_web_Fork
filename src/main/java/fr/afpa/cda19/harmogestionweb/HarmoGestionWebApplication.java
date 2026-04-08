package fr.afpa.cda19.harmogestionweb;

import fr.afpa.cda19.harmogestionweb.controllers.ControllerIndex;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarmoGestionWebApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HarmoGestionWebApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        ControllerIndex.pageIndex();
    }
}
