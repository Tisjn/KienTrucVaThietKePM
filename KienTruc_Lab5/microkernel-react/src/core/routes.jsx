import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import UserPage from "../pages/UserPage";
import { getPluginRoutes } from "./pluginManager";

export default function AppRoutes() {
  const pluginRoutes = getPluginRoutes();

  return (
    <BrowserRouter>
      <nav>
        <Link to="/user">User</Link> |{" "}
        <Link to="/category">Category</Link> |{" "}
        <Link to="/post">Post</Link>
      </nav>

      <Routes>
        <Route path="/user" element={<UserPage />} />

        {pluginRoutes.map((route, index) => (
          <Route key={index} path={route.path} element={route.element} />
        ))}
      </Routes>
    </BrowserRouter>
  );
}