const { mongoose } = require("../db");

const User = mongoose.model("User", {
  name: String,
});

exports.createUser = async (name) => {
  return await User.create({ name });
};

exports.getUsers = async () => {
  return await User.find();
};