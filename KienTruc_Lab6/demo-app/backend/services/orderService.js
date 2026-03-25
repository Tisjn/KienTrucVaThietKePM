const { mongoose } = require("../db");

const Order = mongoose.model("Order", {
  userId: String,
  productId: String,
});

exports.createOrder = async (data) => {
  return await Order.create(data);
};

exports.getOrders = async () => {
  return await Order.find();
};