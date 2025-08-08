import { AuthTokens } from "@/types/auth";
import { create } from "zustand";

interface AuthState {
  authTokens: AuthTokens | null;
  setTokens: (authTokens: AuthTokens) => void;
  clearTokens: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
  authTokens: null,
  setTokens: (authTokens) => set({ authTokens }),
  clearTokens: () => set({ authTokens: null }),
}));
