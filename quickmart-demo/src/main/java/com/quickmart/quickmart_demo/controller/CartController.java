package com.quickmart.quickmart_demo.controller;

import com.quickmart.quickmart_demo.model.CartItem;
import com.quickmart.quickmart_demo.model.Product;
import com.quickmart.quickmart_demo.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductRepository productRepository;

    public CartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        model.addAttribute("cartItems", cart);
        return "cart";
    }

    // Add (GET for quick button; you can also POST from form)
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return "redirect:/products";

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(id)) { item.setQty(item.getQty() + 1); found = true; break; }
        }
        if (!found) cart.add(new CartItem(product, 1));

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    // Remove item entirely
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(it -> it.getProduct().getId().equals(id));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    // Decrease qty
    @GetMapping("/decrease/{id}")
    public String decreaseQty(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            for (CartItem item : cart) {
                if (item.getProduct().getId().equals(id)) {
                    int q = item.getQty() - 1;
                    if (q <= 0) cart.remove(item);
                    else item.setQty(q);
                    break;
                }
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    // Clear all
    @GetMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }
}
