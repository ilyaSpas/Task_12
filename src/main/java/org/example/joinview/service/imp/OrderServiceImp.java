package org.example.joinview.service.imp;

import org.example.joinview.entity.Order;
import org.example.joinview.exception.order.OrderNotFoundException;
import org.example.joinview.repository.OrderRepository;
import org.example.joinview.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order update(Long id, Order order) {
        Order orderFromDB = getById(id);
        orderFromDB.setTitle(order.getTitle());
        orderFromDB.setUser(order.getUser());
        orderFromDB.setDateOfUpdate(LocalDateTime.now());
        return orderRepository.save(orderFromDB);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
