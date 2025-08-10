import { useForm, useFormState } from "react-hook-form";
import { toast } from "react-toastify";
import { loginUser } from "@/services/auth.service";
import { LoginRequest } from "@/types/auth";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { Input, Checkbox, Button } from "@headlessui/react";
import OAuthForm from "./OAuthForm";

export default function Login() {
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from || "/";

  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { errors, isSubmitting },
  } = useForm<LoginRequest>({
    defaultValues: {
      rememberMe: false,
    },
  });

  const rememberMe = watch("rememberMe");

  const onSubmit = async (data: LoginRequest) => {
    try {
      const response = await loginUser(data);
      if (response) {
        toast.success("Login successful");
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
          Log in to StatBroker
        </h1>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
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
              {...register("email", { required: "Email is required" })}
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
                required: "Password is required",
              })}
              className="w-full px-4 py-2 rounded bg-background text-primaryText border border-gray-700 focus:outline-none focus:ring-2 focus:ring-primary"
            />
            {errors.password && (
              <p className="text-error text-sm mt-1">
                {errors.password.message}
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
            disabled={isSubmitting}
            type="submit"
            className="w-full bg-primary text-white font-semibold py-2 rounded hover:bg-green-600 transition-colors cursor-pointer disabled:opacity-50 disabled:cursor-not-allowed"
          >
            Log In
          </Button>
        </form>

        <p className="text-center text-sm text-secondaryText mt-10">
          Don't have an account?{" "}
          <Link to="/auth/register" className="text-primary hover:underline">
            Sign up here
          </Link>
        </p>

        <OAuthForm />
      </div>
    </div>
  );
}
