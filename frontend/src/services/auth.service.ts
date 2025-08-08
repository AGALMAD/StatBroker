import axios from "axios";
import api from "@/lib/axios";
import { AuthTokens, authTokensSchema, LoginRequest } from "@/types/auth";
import { useAuthStore } from "@/stores/useAuthStore";

export async function loginUser(request: LoginRequest): Promise<AuthTokens> {
  const { setTokens, clearTokens } = useAuthStore.getState();

  try {
    const { data } = await api.post<AuthTokens>("/auth/login", {
      email: request.email,
      password: request.password,
    });

    const response = authTokensSchema.safeParse(data);
    if (!response.success) {
      throw new Error("Invalid response format");
    }

    if (request.rememberMe) setTokens(response.data);
    else clearTokens();

    return response.data;
  } catch (err) {
    if (axios.isAxiosError(err)) {
      throw new Error(err.response?.data?.message || "Connection error");
    }

    throw new Error(err instanceof Error ? err.message : "Unexpected error");
  }
}
