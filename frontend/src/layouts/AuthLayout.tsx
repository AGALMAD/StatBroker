import { Outlet } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function AuthLayout() {
  return (
    <>
      <header className="px-10 py-2">
        <h1 className="text-2xl font-bold text-primaryText ">StatBroker</h1>
      </header>
      <main>
        <Outlet />
        <ToastContainer position="top-right" autoClose={3000} />
      </main>
    </>
  );
}
