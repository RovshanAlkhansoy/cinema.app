package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewHallCreationRequest;
import demo.cinema.app.dto.request.UpdateHallRequest;
import demo.cinema.app.dto.response.HallResponse;
import demo.cinema.app.service.HallService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @PostMapping
    public ResponseEntity<HallResponse> createHall(@RequestBody NewHallCreationRequest newHallCreationRequest) {
        HallResponse createdHall = hallService.createHall(newHallCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHall);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallResponse> getHallById(@PathVariable Long id) {
        HallResponse hall = hallService.getHallById(id);
        return ResponseEntity.ok(hall);
    }

    @GetMapping
    public ResponseEntity<List<HallResponse>> getAllHalls() {
        List<HallResponse> halls = hallService.getAllHalls();
        return ResponseEntity.ok(halls);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HallResponse> updateHall(@PathVariable Long id, @RequestBody UpdateHallRequest updateHallRequest) {
        HallResponse updatedHall = hallService.updateHall(id, updateHallRequest);
        return ResponseEntity.ok(updatedHall);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        hallService.deleteHallById(id);
        return ResponseEntity.noContent().build();
    }

}
