import { LoginRequest } from "@/types/auth";
import { useForm } from "react-hook-form";

type LoginForm = LoginRequest & {
  rememberMe: boolean;
};

export default function Login() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginForm>();

  const onSubmit = (data: LoginForm) => {
    console.log("Datos enviados:", data);
  };

  return (
    <>
      <h1>Login in to StatBroker</h1>

      <form onSubmit={handleSubmit(onSubmit)}>
        <label htmlFor="email">Email</label>
        <input
          id="email"
          type="text"
          {...register("email", { required: "El email es obligatorio" })}
        />
        {errors.email && <p>{errors.email.message}</p>}

        <label htmlFor="password">Contraseña</label>
        <input
          id="password"
          type="password"
          {...register("password", {
            required: "La contraseña es obligatoria",
          })}
        />
        {errors.password && <p>{errors.password.message}</p>}

        <label htmlFor="rememberMe">
          <input id="rememberMe" type="checkbox" {...register("rememberMe")} />
          Recordarme
        </label>
        <input type="submit" value="Login" />
      </form>
    </>
  );
}
