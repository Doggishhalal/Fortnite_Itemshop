import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.itemshop.controller.ItemController;
import com.example.itemshop.dto.ItemDto;
import com.example.itemshop.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void testGetAllItems() throws Exception {
        when(itemService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetItemById() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("Test Item");
        itemDto.setPrice(10.0);
        itemDto.setDescription("Test Description");

        when(itemService.findById(1L)).thenReturn(itemDto);

        mockMvc.perform(get("/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Item"));
    }

    @Test
    public void testCreateItem() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("New Item");
        itemDto.setPrice(15.0);
        itemDto.setDescription("New Description");

        when(itemService.save(any())).thenReturn(itemDto);

        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Item\",\"price\":15.0,\"description\":\"New Description\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateItem() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("Updated Item");
        itemDto.setPrice(20.0);
        itemDto.setDescription("Updated Description");

        when(itemService.update(eq(1L), any())).thenReturn(itemDto);

        mockMvc.perform(put("/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Item\",\"price\":20.0,\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteItem() throws Exception {
        doNothing().when(itemService).delete(1L);

        mockMvc.perform(delete("/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}