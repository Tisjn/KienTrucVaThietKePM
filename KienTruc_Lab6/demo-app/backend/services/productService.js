const { mongoose } = require("../db");

const productSchema = new mongoose.Schema({
  name: String,
  price: Number,
});

const Product = mongoose.model("Product", productSchema);

exports.createProduct = async (data) => {
  return await Product.create(data);
};

exports.getProducts = async () => {
  return await Product.find();
};