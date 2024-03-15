package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    //GET
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffee/{id}")
    public Coffee show(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }

    //PATCH
    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CoffeeDto dto) {
        //dto -> 엔티티 변환
        Coffee coffee = dto.toEntity();
        log.info("id : {}, coffee : {}", id, coffee.toString());
        // 타깃 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 잘못된 요청 조회
        if (target == null || id != coffee.getId()) {
            log.info("잘못된 요청 !: id: {}, coffee: {}", id, coffee.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 업데이트 및 정상 응답
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("api/coffee/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
