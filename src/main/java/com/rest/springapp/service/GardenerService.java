package com.rest.springapp.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Gardener;
import com.rest.springapp.repository.GardenerRepo;

@Service
public class GardenerService {

    @Autowired
    private GardenerRepo gardenerRepo;

    public Gardener addGardener(Gardener gardener) {
        return gardenerRepo.save(gardener);
    }

    public List<Gardener> getAllGardeners() {
        return gardenerRepo.findAll();
    }

    public Optional<Gardener> getGardenerById(Integer id) {
        return gardenerRepo.findById(id);
    }

    public Gardener updateGardener(Integer id, Gardener newGardener) {
        return gardenerRepo.findById(id).map(existingGardener -> {
            existingGardener.setName(newGardener.getName());
            existingGardener.setSpecialization(newGardener.getSpecialization());
            existingGardener.setOrderDetails(newGardener.getOrderDetails());
            return gardenerRepo.save(existingGardener);
        }).orElseThrow(() -> new RuntimeException("Gardener not found with id " + id));
    }

    public void deleteGardener(Integer id) {
        if (gardenerRepo.existsById(id)) {
            gardenerRepo.deleteById(id);
        } else {
            throw new RuntimeException("Gardener not found with id " + id);
        }
    }
    // Sorting
    public List<Gardener> getAllGardenersSorted(String field) {
        return gardenerRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Pagination
    public List<Gardener> getPaginatedGardeners(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return gardenerRepo.findAll(page).getContent();
    }

    // Pagination & Sorting
    public List<Gardener> getPaginatedAndSortedGardeners(int pageNumber, int pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, field));
        return gardenerRepo.findAll(pageable).getContent();
    }
}
