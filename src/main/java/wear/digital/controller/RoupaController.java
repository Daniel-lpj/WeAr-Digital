package wear.digital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.server.ResponseStatusException;

import wear.digital.model.Roupa;
import wear.digital.repository.RoupaRepository;

@RestController
@RequestMapping("/api/roupas")
public class RoupaController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RoupaRepository repository;

    @GetMapping()
    public List<Roupa> index() {
        return repository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Roupa> create(@RequestBody Roupa roupa) {
        log.info("cadastrando nova roupa: " + roupa);
        repository.save(roupa);
        return ResponseEntity.status(HttpStatus.CREATED).body(roupa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roupa> show(@PathVariable Long id) {
        log.info("buscando roupa com id " + id);
        return ResponseEntity.ok(getRoupa(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Roupa> destroy(@PathVariable Long id) {
        log.info("apagando roupa com id " + id);
        repository.delete(getRoupa(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roupa> update(@PathVariable Long id, @RequestBody Roupa roupa) {
        log.info("alterando roupa com id " + id);
        getRoupa(id);
        roupa.setRoupa_id(id);
        repository.save(roupa);
        return ResponseEntity.ok(roupa);
    }

    private Roupa getRoupa(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roupa não encontrada"));
    }

}
