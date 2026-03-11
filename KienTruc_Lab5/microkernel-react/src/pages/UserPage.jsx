export default function UserPage() {
  const users = [
    { id: 1, name: "Nguyen Van A" },
    { id: 2, name: "Tran Thi B" },
    { id: 3, name: "Le Van C" },
    { id: 4, name: "Pham Thi D" },
    { id: 5, name: "Hoang Van E" },
  ];

  return (
    <div>
      <h2>User List</h2>
      <ul>
        {users.map((u) => (
          <li key={u.id}>
            {u.id} - {u.name}
          </li>
        ))}
      </ul>
    </div>
  );
}