import { registerPlugin } from "../../core/pluginManager";

function CategoryPage() {
  const categories = [
    { id: 1, name: "Technology" },
    { id: 2, name: "Travel" },
    { id: 3, name: "Food" },
    { id: 4, name: "Education" },
    { id: 5, name: "Sport" },
  ];

  return (
    <div>
      <h2>Category List</h2>
      <ul>
        {categories.map((c) => (
          <li key={c.id}>
            {c.id} - {c.name}
          </li>
        ))}
      </ul>
    </div>
  );
}

registerPlugin({
  name: "category-plugin",
  route: {
    path: "/category",
    element: <CategoryPage />,
  },
});