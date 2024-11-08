package br.com.fiap.smarttraffic.service;

import br.com.fiap.smarttraffic.model.Route;
import br.com.fiap.smarttraffic.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Route> getRoutesByOrigin(String origin) {
        return routeRepository.findByOrigin(origin);
    }

    public List<Route> getRoutesByDestination(String destination) {
        return routeRepository.findByDestination(destination);
    }
}