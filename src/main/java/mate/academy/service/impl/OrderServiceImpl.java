package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setTickets(new ArrayList<>(shoppingCart.getTickets()));
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrdersHistory(user);
    }

    @Override
    public Order getByUser(User user) {
        return orderDao.getByUser(user).get();
    }
}