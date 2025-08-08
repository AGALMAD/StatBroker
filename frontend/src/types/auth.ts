import { z } from "zod";

export const authTokensSchema = z.object({
  accessToken: z.string(),
  refreshToken: z.string(),
});

export type LoginRequest = {
  email: string;
  password: string;
  rememberMe: boolean;
};

export type RegisterRequest = {
  name: string;
  email: string;
  password: string;
  rememberMe: boolean;
};

export type AuthTokens = z.infer<typeof authTokensSchema>;
