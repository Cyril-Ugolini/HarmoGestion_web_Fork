package fr.afpa.cda19.harmogestionweb;

import fr.afpa.cda19.harmogestionweb.controllers.RepresentationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class RepresentationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRepresentationPage() throws Exception {
        mockMvc.perform(get("/representation"))
                .andExpect(status().isOk())
                .andExpect(view().name("representation.html"))
                .andExpect(content().string(containsString("5ème Symphonie")))
                .andExpect(content().string(containsString("Flûte")))
                .andExpect(content().string(containsString("Rodolphe BRUCKER")));
    }
}
