package org.example.joinview.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.example.joinview.config.Views;
import org.example.joinview.entity.Order;
import org.example.joinview.exception.CustomException;
import org.example.joinview.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderServiceImp orderService;

    @Autowired
    public OrderController(OrderServiceImp orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new")
    public ResponseEntity<Order> saveUser(@RequestBody @Valid Order order, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                sb.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new CustomException(sb.toString());
        }
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @JsonView(Views.Internal.class)
    @GetMapping()
    public ResponseEntity<List<Order>> findAll(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Order> saveUser(@PathVariable("id") Long id,
                                         @RequestBody @Valid Order order, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                sb.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new CustomException(sb.toString());
        }
        return new ResponseEntity<>(orderService.update(id, order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public HttpStatus deleteById(@PathVariable("id") Long id){
        orderService.deleteById(id);
        return HttpStatus.OK;
    }
}
