import { Outlet } from "react-router-dom";

export default function AuthLayout() {
  return (
    <>
      <main className="mt-10 mx-auto max-w-6xl p-10 bg-white shadow">
        <Outlet />
      </main>
    </>
  );
}
