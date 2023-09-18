package demo.cinema.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import demo.cinema.app.dto.request.NewHallCreationRequest;
import demo.cinema.app.dto.request.UpdateHallRequest;
import demo.cinema.app.dto.response.HallResponse;
import demo.cinema.app.model.Hall;
import demo.cinema.app.repository.HallRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class HallServiceImplTest {

    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private HallServiceImpl hallService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHall() {

        NewHallCreationRequest newHallCreationRequest = new NewHallCreationRequest();
        newHallCreationRequest.setHallName("Hall 1");
        newHallCreationRequest.setCapacity(100);

        Hall hallToSave = Hall.builder()
                .hallName(newHallCreationRequest.getHallName())
                .capacity(newHallCreationRequest.getCapacity())
                .build();


        when(hallRepository.save(any(Hall.class))).thenReturn(hallToSave);

        HallResponse createdHall = hallService.createHall(newHallCreationRequest);

        assertEquals(hallToSave.getHallName(), createdHall.getHallName());
        assertEquals(hallToSave.getCapacity(), createdHall.getCapacity());
    }

    @Test
    void testGetHallById() {
        Long hallId = 1L;
        Hall hall = Hall.builder().hallName("Hall 1").capacity(100).build();
        when(hallRepository.findById(hallId)).thenReturn(Optional.of(hall));


        HallResponse retrievedHall = hallService.getHallById(hallId);

        assertEquals(hall.getId(), retrievedHall.getId());
        assertEquals(hall.getHallName(), retrievedHall.getHallName());
        assertEquals(hall.getCapacity(), retrievedHall.getCapacity());


    }

    @Test
    void testUpdateHall() {
        Long hallId = 1L;
        Hall hall = Hall.builder().hallName("Hall 1").capacity(100).build();
        when(hallRepository.findById(hallId)).thenReturn(Optional.of(hall));


        UpdateHallRequest updateRequest = new UpdateHallRequest();
        updateRequest.setHallName("Updated Hall");
        updateRequest.setCapacity(120);

        Hall updatedHallEntity = Hall.builder()
                .id(hallId)
                .hallName(updateRequest.getHallName())
                .capacity(updateRequest.getCapacity())
                .build();
        when(hallRepository.save(any(Hall.class))).thenReturn(updatedHallEntity);

        HallResponse updatedHall = hallService.updateHall(hallId, updateRequest);

        assertEquals(hallId, updatedHall.getId());
        assertEquals(updateRequest.getHallName(), updatedHall.getHallName());
        assertEquals(updateRequest.getCapacity(), updatedHall.getCapacity());
    }


    @Test
    void testDeleteHallById() {

        Long hallId = 1L;
        Hall hall = Hall.builder().hallName("Hall 1").capacity(100).build();
        when(hallRepository.findById(hallId)).thenReturn(Optional.of(hall));


        hallService.deleteHallById(hallId);

        verify(hallRepository, times(1)).delete(hall);

        assertNotNull(hall);

        assertNull(hall.getId());
    }

}
