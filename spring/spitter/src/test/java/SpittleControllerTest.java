import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import spitter.model.Spittle;
import spitter.repository.SpittleRepository;
import spitter.web.SpittleController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class SpittleControllerTest {
    @Test
    public void showRecentSpittle() throws  Exception{
        List<Spittle> exceptedSpittles= createSpitterList(20);

        SpittleRepository mockRespository = mock(SpittleRepository.class);
        when(mockRespository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(exceptedSpittles);

        SpittleController spittleController = new SpittleController(mockRespository);
        MockMvc mockMvc = standaloneSetup(spittleController).setSingleView(
                new InternalResourceView("/WEB-INF/views/spittles.jsp")
        ).build();//需要配置视图或者会因为请求路径和返回视图相同报循环访问的错误

        mockMvc.perform(get("/spittles"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", hasItems(exceptedSpittles.toArray())));
    }

    private List<Spittle> createSpitterList(int count){
        List<Spittle> spittles = new ArrayList<Spittle>(count);
        for(int i=0; i<count;i++){
            spittles.add((new Spittle("Spittle"+i, new Date())));
        }
        return spittles;
    }
}
