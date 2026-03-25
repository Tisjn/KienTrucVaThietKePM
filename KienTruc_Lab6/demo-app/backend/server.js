const express = require("express");
const cors = require("cors");
const { connectDB } = require("./db");

const app = express();
app.use(cors());
app.use(express.json());

const apiRoutes = require("./routes/api");
app.use("/api", apiRoutes);

const start = async () => {
  try {
    await connectDB();
    app.listen(3000, () => {
      console.log("Server running at http://localhost:3000");
    });
  } catch (err) {
    console.error(err);
  }
};

start();