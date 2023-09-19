package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewHallCreationRequest;
import demo.cinema.app.dto.request.UpdateHallRequest;
import demo.cinema.app.dto.response.HallResponse;
import demo.cinema.app.service.HallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(tags = "Halls") // Add a tag for the controller
public class HallController {

    private final HallService hallService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Create a new hall")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Hall created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
    })
    public ResponseEntity<HallResponse> createHall(@RequestBody NewHallCreationRequest newHallCreationRequest) {
        HallResponse createdHall = hallService.createHall(newHallCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHall);
    }

    @GetMapping("/getHallById/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Get a hall by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Hall retrieved successfully"),
            @ApiResponse(code = 404, message = "Hall not found"),
    })
    public ResponseEntity<HallResponse> getHallById(@PathVariable Long id) {
        HallResponse hall = hallService.getHallById(id);
        return ResponseEntity.ok(hall);
    }


    @GetMapping("/getAllHalls")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Get all halls")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Halls retrieved successfully"),
    })
    public ResponseEntity<List<HallResponse>> getAllHalls() {
        List<HallResponse> halls = hallService.getAllHalls();
        return ResponseEntity.ok(halls);
    }


    @PutMapping("/updateHall/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Update a hall by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Hall updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Hall not found"),
    })
    public ResponseEntity<HallResponse> updateHall(@PathVariable Long id, @RequestBody UpdateHallRequest updateHallRequest) {
        HallResponse updatedHall = hallService.updateHall(id, updateHallRequest);
        return ResponseEntity.ok(updatedHall);
    }


    @DeleteMapping("/deleteHallById/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Delete a hall by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Hall deleted successfully"),
            @ApiResponse(code = 404, message = "Hall not found"),
    })
    public ResponseEntity<Void> deleteHallById(@PathVariable Long id) {
        hallService.deleteHallById(id);
        return ResponseEntity.noContent().build();
    }

}
