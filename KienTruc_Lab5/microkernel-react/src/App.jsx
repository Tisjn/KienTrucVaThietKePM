import AppRoutes from "./core/routes";
import { pluginConfig } from "./core/pluginConfig";

if (pluginConfig.category) {
  import("./plugins/category/CategoryPlugin");
}

if (pluginConfig.post) {
  import("./plugins/post/PostPlugin");
}

function App() {
  return <AppRoutes />;
}

export default App;