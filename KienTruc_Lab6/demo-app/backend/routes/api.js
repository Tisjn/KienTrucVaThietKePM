const express = require("express");
const router = express.Router();

const userService = require("../services/userService");
const productService = require("../services/productService");
const orderService = require("../services/orderService");

// USER
router.post("/users", async (req, res) => {
  const user = await userService.createUser(req.body.name);
  res.json(user);
});

router.get("/users", async (req, res) => {
  res.json(await userService.getUsers());
});

// PRODUCT
router.post("/products", async (req, res) => {
  res.json(await productService.createProduct(req.body));
});

router.get("/products", async (req, res) => {
  res.json(await productService.getProducts());
});

// ORDER
router.post("/orders", async (req, res) => {
  res.json(await orderService.createOrder(req.body));
});

router.get("/orders", async (req, res) => {
  res.json(await orderService.getOrders());
});

module.exports = router;