package demo.cinema.app.service;

import demo.cinema.app.dto.request.NewHallCreationRequest;
import demo.cinema.app.dto.request.UpdateHallRequest;
import demo.cinema.app.dto.response.HallResponse;
import demo.cinema.app.model.Hall;
import java.util.List;

public interface HallService {

    HallResponse createHall(NewHallCreationRequest newHallCreationRequest);

    HallResponse getHallById(Long id);

    List<HallResponse> getAllHalls();

    HallResponse updateHall(Long id, UpdateHallRequest updateHallRequest);

    void deleteHallById(Long id);
}
