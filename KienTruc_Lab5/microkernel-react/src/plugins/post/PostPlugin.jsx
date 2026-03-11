import { registerPlugin } from "../../core/pluginManager";

function PostPage() {
  const posts = [
    { id: 1, title: "React Basics" },
    { id: 2, title: "Understanding Vite" },
    { id: 3, title: "Microkernel Architecture" },
    { id: 4, title: "Software Design Patterns" },
    { id: 5, title: "Web Development Trends" },
  ];

  return (
    <div>
      <h2>Post List</h2>
      <ul>
        {posts.map((p) => (
          <li key={p.id}>
            {p.id} - {p.title}
          </li>
        ))}
      </ul>
    </div>
  );
}

registerPlugin({
  name: "post-plugin",
  route: {
    path: "/post",
    element: <PostPage />,
  },
});