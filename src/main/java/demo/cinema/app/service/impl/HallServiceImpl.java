package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.NewHallCreationRequest;
import demo.cinema.app.dto.request.UpdateHallRequest;
import demo.cinema.app.dto.response.HallResponse;
import demo.cinema.app.exception.HallNotFound;
import demo.cinema.app.model.Hall;
import demo.cinema.app.repository.HallRepository;
import demo.cinema.app.service.HallService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {


    private final HallRepository hallRepository;

    @Override
    public HallResponse createHall(NewHallCreationRequest newHallCreationRequest) {
        Hall hall = Hall.builder()
                .hallName(newHallCreationRequest.getHallName())
                .capacity(newHallCreationRequest.getCapacity())
                .build();

        Hall savedHall = hallRepository.save(hall);

        return HallResponse.builder()
                .id(savedHall.getId())
                .hallName(savedHall.getHallName())
                .capacity(savedHall.getCapacity())
                .build();
    }

    @Override
    public HallResponse getHallById(Long id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(HallNotFound::new);

        return HallResponse.builder()
                .id(hall.getId())
                .hallName(hall.getHallName())
                .capacity(hall.getCapacity())
                .build();
    }

    @Override
    public List<HallResponse> getAllHalls() {
        List<Hall> halls = hallRepository.findAll();

        return halls.stream()
                .map(this::mapToHallResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HallResponse updateHall(Long id, UpdateHallRequest updateHallRequest) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(HallNotFound::new);

        hall.setHallName(updateHallRequest.getHallName());
        hall.setCapacity(updateHallRequest.getCapacity());

        Hall updatedHall = hallRepository.save(hall);

        return mapToHallResponse(updatedHall);
    }

    @Override
    public void deleteHallById(Long id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(HallNotFound::new);

        hallRepository.delete(hall);
    }

    private HallResponse mapToHallResponse(Hall hall) {
        return HallResponse.builder()
                .id(hall.getId())
                .hallName(hall.getHallName())
                .capacity(hall.getCapacity())
                .build();
    }

}

