package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        if (coffee.getId() != null)
            return null;
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        log.info("id : {}, coffee : {}", id, coffee.toString());
        // 타깃 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 잘못된 요청 조회
        if (target == null || id != coffee.getId()) {
            log.info("잘못된 요청 !: id: {}, coffee: {}", id, coffee.toString());
            return null;
        }
        // 업데이트 및 정상 응답
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);

        return updated;
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null){
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }
}
