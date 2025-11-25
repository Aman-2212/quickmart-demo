package com.quickmart.quickmart_demo.controller;

import com.quickmart.quickmart_demo.model.BillingInfo;
import com.quickmart.quickmart_demo.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String checkoutPage(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Your cart is empty.");
            return "cart";
        }
        model.addAttribute("cartItems", cart);
        model.addAttribute("billingInfo", new BillingInfo());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String submitCheckout(@ModelAttribute BillingInfo billingInfo, HttpSession session, Model model) {
        // simple demo: save billing info to session, clear cart and show order confirmation
        session.setAttribute("billingInfo", billingInfo);

        // in real app: create Order entity, persist items, charge payment, etc.
        session.removeAttribute("cart");

        model.addAttribute("billing", billingInfo);
        return "order-confirmation";
    }
}
