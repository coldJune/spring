import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;
import com.jun.spitter.web.HomeController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception{
        HomeController homeController = new HomeController();
        MockMvc mockMvc = standaloneSetup(homeController).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
        mockMvc.perform(get("/homepage")).andExpect(view().name("home"));

    }
}
