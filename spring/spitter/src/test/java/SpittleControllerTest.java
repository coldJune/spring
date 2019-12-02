import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import com.jun.spitter.model.Spittle;
import com.jun.spitter.repository.SpittleRepository;
import com.jun.spitter.web.SpittleController;

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
        when(mockRespository.findSpittles(1111, 50)).thenReturn(exceptedSpittles);

        SpittleController spittleController = new SpittleController(mockRespository);
        MockMvc mockMvc = standaloneSetup(spittleController).setSingleView(
                new InternalResourceView("/WEB-INF/views/spittles.jsp")
        ).build();//需要配置视图或者会因为请求路径和返回视图相同报循环访问的错误

        mockMvc.perform(get("/spittles?max=1111&count=50"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", hasItems(exceptedSpittles.toArray())));
    }

    @Test
    public void testSpittle() throws Exception{
        Spittle exceptedSpittle = new Spittle("hello", new Date());
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findOne(1l)).thenReturn(exceptedSpittle);

        SpittleController spittleController = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(spittleController).build();

        mockMvc.perform(get("/showSpittle/1"))
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", exceptedSpittle));
    }

    private List<Spittle> createSpitterList(int count){
        List<Spittle> spittles = new ArrayList<Spittle>(count);
        for(int i=0; i<count;i++){
            spittles.add((new Spittle("Spittle"+i, new Date())));
        }
        return spittles;
    }
}
