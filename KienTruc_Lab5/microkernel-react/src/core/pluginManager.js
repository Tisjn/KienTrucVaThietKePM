const plugins = [];

export function registerPlugin(plugin) {
  plugins.push(plugin);
}

export function getPluginRoutes() {
  return plugins.map((p) => p.route);
}