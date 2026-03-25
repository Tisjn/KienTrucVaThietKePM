import { useEffect, useState } from "react";
import axios from "axios";

const API = "http://localhost:3000/api";

function App() {
  const [users, setUsers] = useState([]);
  const [products, setProducts] = useState([]);
  const [orders, setOrders] = useState([]);

  const [userName, setUserName] = useState("");
  const [productName, setProductName] = useState("");
  const [productPrice, setProductPrice] = useState("");
  const [orderUserId, setOrderUserId] = useState("");
  const [orderProductId, setOrderProductId] = useState("");
  const [orderQty, setOrderQty] = useState(1);

  // USERS
  const loadUsers = async () => {
    const res = await axios.get(`${API}/users`);
    setUsers(res.data);
  };
  const createUser = async () => {
    await axios.post(`${API}/users`, { name: userName });
    setUserName("");
    loadUsers();
  };

  // PRODUCTS
  const loadProducts = async () => {
    const res = await axios.get(`${API}/products`);
    setProducts(res.data);
  };
  const createProduct = async () => {
    await axios.post(`${API}/products`, {
      name: productName,
      price: Number(productPrice),
    });
    setProductName("");
    setProductPrice("");
    loadProducts();
  };

  // ORDERS
  const loadOrders = async () => {
    const res = await axios.get(`${API}/orders`);
    setOrders(res.data);
  };
  const createOrder = async () => {
    if (!orderUserId || !orderProductId) return alert("Select user & product");
    await axios.post(`${API}/orders`, {
      userId: orderUserId,
      productId: orderProductId,
      quantity: Number(orderQty),
    });
    setOrderQty(1);
    loadOrders();
  };

  useEffect(() => {
    loadUsers();
    loadProducts();
    loadOrders();
  }, []);

  return (
    <div style={{ padding: 20 }}>
      <h1>🧩 User-Product-Order Demo</h1>

      {/* USERS */}
      <h2>Create User</h2>
      <input
        value={userName}
        onChange={(e) => setUserName(e.target.value)}
        placeholder="User Name"
      />
      <button onClick={createUser}>Add User</button>
      <ul>{users.map((u) => <li key={u._id}>{u.name}</li>)}</ul>

      {/* PRODUCTS */}
      <h2>Create Product</h2>
      <input
        value={productName}
        onChange={(e) => setProductName(e.target.value)}
        placeholder="Product Name"
      />
      <input
        type="number"
        value={productPrice}
        onChange={(e) => setProductPrice(e.target.value)}
        placeholder="Price"
      />
      <button onClick={createProduct}>Add Product</button>
      <ul>{products.map((p) => <li key={p._id}>{p.name} - ${p.price}</li>)}</ul>

      {/* ORDERS */}
      <h2>Create Order</h2>
      <select value={orderUserId} onChange={(e) => setOrderUserId(e.target.value)}>
        <option value="">Select User</option>
        {users.map((u) => <option key={u._id} value={u._id}>{u.name}</option>)}
      </select>
      <select value={orderProductId} onChange={(e) => setOrderProductId(e.target.value)}>
        <option value="">Select Product</option>
        {products.map((p) => <option key={p._id} value={p._id}>{p.name}</option>)}
      </select>
      <input type="number" value={orderQty} min={1} onChange={(e) => setOrderQty(e.target.value)} />
      <button onClick={createOrder}>Add Order</button>

      <h3>Orders</h3>
      <ul>
        {orders.map((o) => {
          const user = users.find((u) => u._id === o.userId);
          const product = products.find((p) => p._id === o.productId);
          return (
            <li key={o._id}>
              {user ? user.name : "Deleted User"} - {product ? product.name : "Deleted Product"} x {o.quantity}
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default App;