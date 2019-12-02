import com.jun.spitter.model.Spitter;
import com.jun.spitter.repository.SpitterRepository;
import com.jun.spitter.web.SpitterController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpitterControllerTest {

    @Test
    public void testShowRegister() throws  Exception{
        SpitterController spitterController = new SpitterController();
        MockMvc mockMvc = standaloneSetup(spitterController).build();
        mockMvc.perform(get("/spitter/showRegister"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void testRegister() throws  Exception{
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("username", "password", "firstName", "lastName", "email");
        Spitter saved = new Spitter(1l,"username", "password", "firstName", "lastName", "email");

        when(mockRepository.save(unsaved)).thenReturn(saved);
        SpitterController spitterController = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(spitterController).build();
        mockMvc.perform(post("/spitter/register")
                .param("firstName","firstName")
                .param("username","username")
                .param("password","password")
                .param("lastName","lastName")
                .param("email","email"))
                .andExpect(redirectedUrl("/spitter/username"));
        verify(mockRepository, atLeastOnce()).save(unsaved);
    }

    @Test
    public void testRegisterWithValid() throws Exception{
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        SpitterController spitterController = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(spitterController).build();
        mockMvc.perform(post("/spitter/registerWithValid"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerForm"))
                .andExpect(model().errorCount(5))
                .andExpect(model().attributeHasFieldErrors("spitter","firstName","lastName","username","password","email"));
    }
}
