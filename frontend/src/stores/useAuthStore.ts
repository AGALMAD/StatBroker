import { AuthTokens } from "@/types/auth";
import { create } from "zustand";

interface AuthState {
  authTokens: AuthTokens | null;
  setToken: (authTokens: AuthTokens) => void;
  clearToken: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
  authTokens: null,
  setToken: (authTokens) => set({ authTokens }),
  clearToken: () => set({ authTokens: null }),
}));
