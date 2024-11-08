package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.dto.RouteDTO;
import br.com.fiap.smarttraffic.model.Route;
import br.com.fiap.smarttraffic.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // CREATE ROUTE
    @PostMapping("/routes")
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        Route route = mapToRoute(routeDTO);
        Route createdRoute = routeService.createRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToRouteDTO(createdRoute));
    }

    // GET ROUTE BY ID
    @GetMapping("/routes/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable Long id) {
        Optional<Route> routeOptional = routeService.getRouteById(id);
        if (routeOptional.isPresent()) {
            return ResponseEntity.ok(mapToRouteDTO(routeOptional.get()));
        } else {
            return ResponseEntity.notFound().build(); // Handle not found case
        }
    }

    // GET ALL ROUTES
    @GetMapping("/routes")
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes.stream().map(this::mapToRouteDTO).collect(Collectors.toList()));
    }

    // GET ROUTES BY ORIGIN
    @GetMapping("/routes/origin/{origin}")
    public ResponseEntity<List<RouteDTO>> getRoutesByOrigin(@PathVariable String origin) {
        List<Route> routes = routeService.getRoutesByOrigin(origin);
        return ResponseEntity.ok(routes.stream().map(this::mapToRouteDTO).collect(Collectors.toList()));
    }

    // GET ROUTES BY DESTINATION
    @GetMapping("/routes/destination/{destination}")
    public ResponseEntity<List<RouteDTO>> getRoutesByDestination(@PathVariable String destination) {
        List<Route> routes = routeService.getRoutesByDestination(destination);
        return ResponseEntity.ok(routes.stream().map(this::mapToRouteDTO).collect(Collectors.toList()));
    }

    // Mapping methods
    private Route mapToRoute(RouteDTO routeDTO) {
        Route route = new Route();
        route.setOrigin(routeDTO.getOrigin());
        route.setDestination(routeDTO.getDestination());
        route.setEstimatedTime(routeDTO.getEstimatedTime());
        return route;
    }

    private RouteDTO mapToRouteDTO(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setId(route.getId());
        routeDTO.setOrigin(route.getOrigin());
        routeDTO.setDestination(route.getDestination());
        routeDTO.setEstimatedTime(route.getEstimatedTime());
        return routeDTO;
    }
}