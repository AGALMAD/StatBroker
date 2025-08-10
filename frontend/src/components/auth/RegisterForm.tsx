import { useForm } from "react-hook-form";
import { toast } from "react-toastify";
import { RegisterRequest } from "@/types/auth";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { Input, Checkbox, Button } from "@headlessui/react";
import { registerUser } from "@/services/auth.service";
import OAuthForm from "./OAuthForm";

type RegisterFormType = {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
  rememberMe: boolean;
};

export default function Register() {
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from || "/";

  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { errors },
  } = useForm<RegisterFormType>({
    defaultValues: {
      rememberMe: false,
    },
  });

  const rememberMe = watch("rememberMe");

  const onSubmit = async (data: RegisterFormType) => {
    const request: RegisterRequest = {
      name: data.name,
      email: data.email,
      password: data.password,
      rememberMe: data.rememberMe,
    };

    try {
      const response = await registerUser(request);
      if (response) {
        toast.success(
          <div>
            <strong>Cuenta creada con éxito</strong>
            <div>Revisa tu correo para verificar tu cuenta</div>
          </div>
        );
        navigate(from);
      }
    } catch (error) {
      toast.error((error as Error).message);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-background text-primaryText px-4">
      <div className="w-full max-w-md bg-surface rounded-xl shadow-lg p-8">
        <h1 className="text-2xl font-bold mb-6 text-center">
          Create an account on StatBroker
        </h1>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
          {/* Name */}
          <div>
            <label
              htmlFor="name"
              className="block text-sm text-secondaryText mb-1"
            >
              Name
            </label>
            <Input
              id="name"
              type="name"
              {...register("name", { required: "Name is required" })}
              className="w-full px-4 py-2 rounded bg-background text-primaryText border border-gray-700 focus:outline-none focus:ring-2 focus:ring-primary"
            />
            {errors.name && (
              <p className="text-error text-sm mt-1">{errors.name.message}</p>
            )}
          </div>

          {/* Email */}
          <div>
            <label
              htmlFor="email"
              className="block text-sm text-secondaryText mb-1"
            >
              Email
            </label>
            <Input
              id="email"
              type="email"
              {...register("email", {
                required: "Email is required",
                pattern: {
                  value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                  message: "Email format not valid",
                },
              })}
              className="w-full px-4 py-2 rounded bg-background text-primaryText border border-gray-700 focus:outline-none focus:ring-2 focus:ring-primary"
            />
            {errors.email && (
              <p className="text-error text-sm mt-1">{errors.email.message}</p>
            )}
          </div>

          {/* Password */}
          <div>
            <label
              htmlFor="password"
              className="block text-sm text-secondaryText mb-1"
            >
              Password
            </label>
            <Input
              id="password"
              type="password"
              {...register("password", {
                required: "La contraseña es obligatoria",
                minLength: {
                  value: 6,
                  message: "La contraseña debe tener al menos 6 caracteres",
                },
              })}
              className="w-full px-4 py-2 rounded bg-background text-primaryText border border-gray-700 focus:outline-none focus:ring-2 focus:ring-primary"
            />
            {errors.password && (
              <p className="text-error text-sm mt-1">
                {errors.password.message}
              </p>
            )}
          </div>

          {/* Confirm Password */}
          <div>
            <label
              htmlFor="confirmPassword"
              className="block text-sm text-secondaryText mb-1"
            >
              Confirm Password
            </label>
            <Input
              id="confirmPassword"
              type="password"
              {...register("confirmPassword", {
                required: "Confirmar contraseña es obligatorio",
                validate: (value) =>
                  value === watch("password") || "Passwords do not match",
              })}
              className="w-full px-4 py-2 rounded bg-background text-primaryText border border-gray-700 focus:outline-none focus:ring-2 focus:ring-primary"
            />
            {errors.confirmPassword && (
              <p className="text-error text-sm mt-1">
                {errors.confirmPassword.message}
              </p>
            )}
          </div>

          {/* Remember Me Checkbox */}
          <Checkbox
            checked={rememberMe}
            onChange={(val: boolean) => setValue("rememberMe", val)}
            className="flex items-center cursor-pointer select-none text-secondaryText"
          >
            {({ checked }: { checked: boolean }) => (
              <>
                <span
                  className={`w-5 h-5 mr-2 rounded border flex items-center justify-center ${
                    checked
                      ? "bg-primary border-primary"
                      : "bg-background border-gray-700"
                  }`}
                >
                  {checked && (
                    <svg
                      className="w-4 h-4 text-white"
                      fill="none"
                      stroke="currentColor"
                      strokeWidth="3"
                      viewBox="0 0 24 24"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        d="M5 13l4 4L19 7"
                      />
                    </svg>
                  )}
                </span>
                Remember Me
              </>
            )}
          </Checkbox>

          {/* Submit Button */}
          <Button
            type="submit"
            className="w-full bg-primary text-white font-semibold py-2 rounded hover:bg-green-600 transition-colors cursor-pointer"
          >
            Sing Up
          </Button>
        </form>

        <p className="text-center text-sm text-secondaryText mt-10">
          Already have an account?{" "}
          <Link to="/auth/login" className="text-primary hover:underline">
            Log in
          </Link>
        </p>

        <OAuthForm />
      </div>
    </div>
  );
}
