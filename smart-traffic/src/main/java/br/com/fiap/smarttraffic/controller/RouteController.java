package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.model.Route;
import br.com.fiap.smarttraffic.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.createRoute(route);
        return ResponseEntity.ok(createdRoute);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        Optional<Route> route = routeService.getRouteById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/by-origin/{origin}")
    public ResponseEntity<List<Route>> getRoutesByOrigin(@PathVariable String origin) {
        List<Route> routes = routeService.getRoutesByOrigin(origin);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/by-destination/{destination}")
    public ResponseEntity<List<Route>> getRoutesByDestination(@PathVariable String destination) {
        List<Route> routes = routeService.getRoutesByDestination(destination);
        return ResponseEntity.ok(routes);
    }

}